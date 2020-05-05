package com.sanain.photo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanain.photo.mapper.PtAlbumTempMapper;
import com.sanain.photo.pojo.PtAlbumTemp;
import com.sanain.photo.pojo.PtAlbumTempExample;
import com.sanain.photo.pojo.PtUserAlbum;
import com.sanain.photo.util.ConstantUtil;
import com.sanain.photo.util.FileUtils;
import com.sanain.photo.util.JsonUtils;
import com.sanain.photo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态相册模板的service
 * @Author sanain
 */
@Service
public class PtAlbumTempService {
    @Autowired
    private PtAlbumTempMapper ptAlbumTempMapper;
    @Autowired
    private PtUserAlbumService ptUserAlbumService;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 根据条件分页查询
     * @param temp
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<PtAlbumTemp> getListByExmaple(PtAlbumTemp temp,Integer pageNum , Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);

        PtAlbumTempExample example = new PtAlbumTempExample();
        example.setOrderByClause("create_time");
        example.setDistinct(true);
        PtAlbumTempExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(temp.getName())){
            criteria.andNameLike("%"+temp.getName()+"%");

        }

        List<PtAlbumTemp> list = ptAlbumTempMapper.selectByExample(example);

        if(!CollectionUtils.isEmpty(list)){
            for(PtAlbumTemp t : list){
                t.setCoverPaths(ConstantUtil.PRO_PATH+ConstantUtil.PATH_TEMP_IMG+t.getCoverPaths());
            }
        }
        return new PageInfo<>(list);
    }

    /**
     * 查询所有的模板
     * @return
     */
    public List<PtAlbumTemp> getAllAlbum(){
        Object o = redisUtil.get(ConstantUtil.ALL_TEMP);
        if(o != null){
            List<PtAlbumTemp> list = JsonUtils.jsonToList(o.toString(), PtAlbumTemp.class);
            return list;
        }

        PtAlbumTempExample example = new PtAlbumTempExample();
        example.setOrderByClause("create_time");
        example.setDistinct(true);

        List<PtAlbumTemp> list = ptAlbumTempMapper.selectByExample(example);

        if(!CollectionUtils.isEmpty(list)){
            for(PtAlbumTemp t : list){
                t.setCoverPaths(ConstantUtil.PRO_PATH+ConstantUtil.PATH_TEMP_IMG+t.getCoverPaths());
            }
        }

        redisUtil.set(ConstantUtil.ALL_TEMP,JsonUtils.listToJson(list));
        return list;
    }


    /**
     * 新增模板
     * @param temp
     * @return
     */
    @Transactional
    public boolean insertTemp(PtAlbumTemp temp){
        temp.setCreateTime(new Date());
        if(!StringUtils.isEmpty(temp.getPhotoPaths())){
            String str = temp.getPhotoPaths().split(";")[0];
            temp.setCoverPaths(str);
        }

        int i = ptAlbumTempMapper.insertSelective(temp);

        return i > 0 ;
    }


    /**
     * 删除模板
     * @param id
     * @return
     */
    @Transactional
    public Map<String,Object> deleteTemp(Integer id){
        Map<String,Object> map = new HashMap<>();

        if(id == null){
            map.put("result",false);
            map.put("msg","id不能为空");
            return map;
        }

        List<PtUserAlbum> ptUserAlbums = ptUserAlbumService.selectByTempId(id);
        if(!CollectionUtils.isEmpty(ptUserAlbums)){
            map.put("result",false);
            map.put("msg","有动态相册正在使用当前的模板，不能删除");
            return map;
        }

//        查出将要被删除的对象
        PtAlbumTemp temp = ptAlbumTempMapper.selectByPrimaryKey(id);
//        删除图片
        if(!StringUtils.isEmpty(temp.getPhotoPaths())){
            FileUtils.deleteFiles(temp.getPhotoPaths(),";",ConstantUtil.TEMP_IMG);
        }
//        删除js文件
        if(!StringUtils.isEmpty(temp.getJsPaths())){
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/js/";
            FileUtils.deleteFiles(temp.getJsPaths(),";",path);
        }
        //        删除css文件
        if(!StringUtils.isEmpty(temp.getCssPaths())){
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/css/";
            FileUtils.deleteFiles(temp.getCssPaths(),";",path);
        }
        int i = ptAlbumTempMapper.deleteByPrimaryKey(id);

        boolean result = i > 0 ? true : false;
        String msg = i > 0 ? "删除成功":"删除失败";

        map.put("msg" , msg);
        map.put("result" , result);

        return map;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public PtAlbumTemp selectById(Integer id){
        if(id == null){
            return null;
        }

        return ptAlbumTempMapper.selectByPrimaryKey(id);
    }
}
