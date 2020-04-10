package com.sanain.photo.service;

import ch.qos.logback.core.util.ContextUtil;
import com.sanain.photo.mapper.PtDirMapper;
import com.sanain.photo.pojo.*;
import com.sanain.photo.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户文件夹的service
 * @Author sanain
 */
@Service
public class PtDirService {
    @Autowired
    private PtDirMapper ptDirMapper;
    @Autowired
    private PtUserFileService ptUserFileService;

    /**
     * 插入文件夹
     * @param ptDir
     * @return
     */
    @Transactional
    public PtDir insertDir(PtDir ptDir,PtUser user){
        ptDir.setDirUserId(user.getUserId());
        ptDir.setCreateTime(new Date());
        ptDir.setDirImg(ConstantUtil.DEFAULT_IMG);
        int i = ptDirMapper.insert(ptDir);
        if(i == 0){
            return null;
        }

        //查出刚才插入的对象
        PtDirExample example = new PtDirExample();
        PtDirExample.Criteria criteria = example.createCriteria();
        criteria.andDirNameEqualTo(ptDir.getDirName());
        List<PtDir> ptDirs = ptDirMapper.selectByExample(example);

        return returnFrist(ptDirs);

    }


    /**
     * 更新文件夹信息
     * @param ptDir
     * @return
     */
    @Transactional
    public PtDir updateDir(PtDir ptDir){
        if(ptDir.getDirId() == null){
            return null;
        }

        int i = ptDirMapper.updateByPrimaryKeySelective(ptDir);

        if(i == 0){
            return null;
        }

        ptDir = ptDirMapper.selectByPrimaryKey(ptDir.getDirId());

        return ptDir;
    }

    /**
     * 删除文件夹
     * @param dirId
     * @return  result 0 成功 1 失败  msg 信息
     */
    @Transactional
    public Map<String , String> deleteDir(Integer dirId){
        Map<String , String> map = new HashMap<>();
        if( dirId == null){
            map.put("result","1");
            map.put("msg","相册id不能为空");
            return map;
        }

        List<PtUserFile> ptUserFiles = ptUserFileService.selectAllByDirId(dirId);
        if(!CollectionUtils.isEmpty(ptUserFiles)){
            map.put("result","1");
            map.put("msg","相册中还有图片不能删除");
            return map;
        }

        //删除所有文件夹中的图片(数据库)
        PtUserFile ptUserFile = new PtUserFile();
        ptUserFile.setDirId(ptUserFile.getDirId());
        ptUserFileService.deleteByExample(ptUserFile);
        //删除图片
        if(!CollectionUtils.isEmpty(ptUserFiles)){
            for(PtUserFile userFile : ptUserFiles){
                File file = new File(ConstantUtil.IMAGES+userFile.getFilePath()+userFile.getFileName());
                file.delete();
            }
        }


        ptDirMapper.deleteByPrimaryKey(dirId);

        map.put("result","0");
        map.put("msg","删除相册成功");
        return map;
    }

    /**
     * 根据id查询
     * @param dirId
     * @return
     */
    public PtDir selectById(Integer dirId){
        if( dirId == null){
            return null;
        }

        return ptDirMapper.selectByPrimaryKey(dirId);
    }

    /**
     * 根据条件查询
     * @param dir
     * @return
     */
    public List<PtDir> selectList(PtDir dir){

        PtDirExample example = new PtDirExample();
        PtDirExample.Criteria criteria = example.createCriteria();

        //文件夹名称不为空时，根据文件夹名查询
        if(!StringUtils.isEmpty(dir.getDirName())) {
            criteria.andDirNameLike("%"+dir.getDirName()+"%");
        }
        //文件夹分类不为空时，根据分类查询
        if(dir.getDirTypeId() != null) {
            criteria.andDirTypeIdEqualTo(dir.getDirTypeId());
        }
        //文件夹用户id不为空时，根据用户id名查询
        if(!StringUtils.isEmpty(dir.getDirUserId())) {
            criteria.andDirUserIdEqualTo(dir.getDirUserId());
        }
        List<PtDir> ptDirs = ptDirMapper.selectByExample(example);

        if(!CollectionUtils.isEmpty(ptDirs)){
            for(PtDir ptDir : ptDirs){
                ptDir.setDirImg(ConstantUtil.PRO_PATH+ConstantUtil.PATH_DIR_IMG+ptDir.getDirImg());
            }
        }

        return ptDirs;
    }

    /**
     * 查询是否已经存在相同的文件夹名
     * @param
     * @return 存在 true  不存在 false
     */
    public boolean hasSameName(PtDir ptDir,Integer userId) {
        if (StringUtils.isEmpty(ptDir.getDirName())) {
            return true;
        }

        PtDirExample example = new PtDirExample();
        PtDirExample.Criteria criteria = example.createCriteria();
        criteria.andDirNameEqualTo(ptDir.getDirName());
        criteria.andDirUserIdEqualTo(userId);
        if(ptDir.getDirId() != null){
            criteria.andDirIdNotEqualTo(ptDir.getDirId());
        }
        List<PtDir> ptDirTypes = ptDirMapper.selectByExample(example);

        return CollectionUtils.isEmpty(ptDirTypes)?false:true;
    }

    /**
     * 当列表不为空，返回第一个元素，为空返回null
     * @param dirs
     * @return
     */
    private PtDir returnFrist(List<PtDir> dirs){
        if(CollectionUtils.isEmpty(dirs)){
            return null;
        }

        return dirs.get(0);
    }
}
