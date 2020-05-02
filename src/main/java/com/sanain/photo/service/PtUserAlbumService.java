package com.sanain.photo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanain.photo.mapper.PtUserAlbumFileMapper;
import com.sanain.photo.mapper.PtUserAlbumMapper;
import com.sanain.photo.pojo.*;
import com.sanain.photo.util.ConstantUtil;
import com.sanain.photo.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 用户动态相册的service
 * @Author sanain
 */
@Service
public class PtUserAlbumService {
    @Autowired
    private PtUserAlbumMapper ptUserAlbumMapper;
    @Autowired
    private PtUserAlbumFileService ptUserAlbumFileService;
    /**
     * 根据模板id查询
     * @param tempId
     * @return
     */
    public List<PtUserAlbum> selectByTempId(Integer tempId){
        if(tempId == null){
            return null;
        }

        PtUserAlbumExample example = new PtUserAlbumExample();
        example.setDistinct(true);
        example.setOrderByClause("create_time");
        PtUserAlbumExample.Criteria criteria = example.createCriteria();
        criteria.andTempIdEqualTo(tempId);

        return ptUserAlbumMapper.selectByExample(example);
    }


    /**
     * 根据条件分页查询
     * @param
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<PtUserAlbum> getListByExample(PtUserAlbum album, Integer pageNum , Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);

        PtUserAlbumExample example = new PtUserAlbumExample();
        example.setOrderByClause("create_time");
        example.setDistinct(true);
        PtUserAlbumExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(album.getName())){
            criteria.andNameEqualTo(album.getName());
        }
        if(album.getUserId() != null){
            criteria.andUserIdEqualTo(album.getUserId());
        }
        if(album.getTempId() != null){
            criteria.andTempIdEqualTo(album.getTempId());
        }

        List<PtUserAlbum> ptUserAlbums = ptUserAlbumMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(ptUserAlbums)){
            for(PtUserAlbum a : ptUserAlbums){
                a.setCoverPaths(ConstantUtil.PRO_PATH+ConstantUtil.PATH_TEMP_IMG+a.getCoverPaths());
            }
        }

        return new PageInfo<>(ptUserAlbums);

    }


    /**
     * 新增用户动态相册
     * @param
     * @return
     */
    @Transactional
    public boolean insertAlbum(PtUserAlbum album,String fileNames){
        album.setCreateTime(new Date());

        List<String> fileList = Arrays.asList(fileNames.split(";"));
//        设置封面
        if(!CollectionUtils.isEmpty(fileList)){
            album.setCoverPaths(fileList.get(0));
        }

        int i = ptUserAlbumMapper.insertSelective(album);
        if(i == 0){
            return false;
        }


//        插入图片
        ptUserAlbumFileService.batchInsert(fileList,album);

        return true ;
    }


    /**
     * 删除模板
     * @param id
     * @return
     */
    @Transactional
    public Map<String,Object> deleteAlbum(Integer id){
        Map<String,Object> map = new HashMap<>();

        if(id == null){
            map.put("result",false);
            map.put("msg","id不能为空");
            return map;
        }

        ptUserAlbumFileService.deleteByUserAlbumId(id);
        int i = ptUserAlbumMapper.deleteByPrimaryKey(id);

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
    public PtUserAlbum selectById(Integer id){
        if(id == null){
            return null;
        }

        PtUserAlbum ptUserAlbum = ptUserAlbumMapper.selectUnionByPrimaryKey(id);

        if(!CollectionUtils.isEmpty(ptUserAlbum.getFiles())){
            for(PtUserAlbumFile f : ptUserAlbum.getFiles()){
                f.setFilePath(ConstantUtil.PRO_PATH+ConstantUtil.PATH_TEMP_IMG+f.getFilePath());
            }
        }
        PtAlbumTemp temp = ptUserAlbum.getTemp();

        //        拼凑演示图片路径
        if(!StringUtils.isEmpty(temp.getPhotoPaths())){
            StringBuffer strBuf = new StringBuffer();
            for(String str : temp.getPhotoPaths().split(";")){
                if(StringUtils.isEmpty(str)){
                    continue;
                }
                strBuf.append(ConstantUtil.PRO_PATH+ConstantUtil.PATH_TEMP_IMG+str+";");
            }
            temp.setPhotoPaths(strBuf.toString());
        }
        // 拼凑js路径
        if(!StringUtils.isEmpty(temp.getJsPaths())){
            StringBuffer strBuf = new StringBuffer();
            for(String str : temp.getJsPaths().split(";")){
                if(StringUtils.isEmpty(str)){
                    continue;
                }
                strBuf.append(ConstantUtil.PRO_PATH+"/js/"+str+";");
            }
            temp.setJsPaths(strBuf.toString());
        }
        // 拼凑css路径
        if(!StringUtils.isEmpty(temp.getCssPaths())){
            StringBuffer strBuf = new StringBuffer();
            for(String str : temp.getCssPaths().split(";")){
                if(StringUtils.isEmpty(str)){
                    continue;
                }
                strBuf.append(ConstantUtil.PRO_PATH+"/css/"+str+";");
            }
            temp.setCssPaths(strBuf.toString());
        }

        return ptUserAlbum;
    }

    /**
     * 更新用户动态相册
     * @param ptUserAlbum
     * @return
     */
    public boolean updateAlbum(PtUserAlbum ptUserAlbum){
        if(ptUserAlbum.getId() == null){
            return false;
        }

        int i = ptUserAlbumMapper.updateByPrimaryKeySelective(ptUserAlbum);

        return i > 0;
    }
}
