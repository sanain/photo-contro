package com.sanain.photo.service;

import com.sanain.photo.mapper.PtUserFileMapper;
import com.sanain.photo.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 用户上传的文件service
 * @Author sanain
 */
@Service
public class PtUserFileService {
    @Autowired
    private PtUserFileMapper ptUserFileMapper;

    /**
     * 插入
     * @param
     * @return
     */
    @Transactional
    public PtUserFile insert(String fileName, PtDir ptDir, PtUser ptUser){

        PtUserFile ptUserFile = new PtUserFile();
        ptUserFile.setCreateTime(new Date());
        ptUserFile.setDirId(ptDir.getDirId());
        ptUserFile.setFilePath(ptUser.getUserId()+"/"+ptDir.getDirId()+"/");
        ptUserFile.setFileName(fileName);

        //插入数据库
        ptUserFileMapper.insert(ptUserFile);

        //查询出刚才插入的图片
        PtUserFileExample example = new PtUserFileExample();
        PtUserFileExample.Criteria criteria = example.createCriteria();
        criteria.andFileNameEqualTo(ptUserFile.getFileName());
        List<PtUserFile> ptUserFiles = ptUserFileMapper.selectByExample(example);

        return returnFrist(ptUserFiles);
    }

    /**
     * 批量删除
     * @param list
     * @return
     */
    @Transactional
    public boolean deleteBatch(List<Integer> list){
        int i = ptUserFileMapper.deleteBatch(list);
        return i == 0 ? false:true;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteById(Integer id){
        int i = ptUserFileMapper.deleteByPrimaryKey(id);
        return i == 0 ? false:true;
    }

    /**
     * 根据id删除
     * @param
     * @return
     */
    public boolean deleteByExample(PtUserFile ptUserFile){
        PtUserFileExample example = new PtUserFileExample();
        PtUserFileExample.Criteria criteria = example.createCriteria();
        if(ptUserFile.getFileId() != null){
            criteria.andFileIdEqualTo(ptUserFile.getFileId());
        }

        if(ptUserFile.getDirId() != null){
            criteria.andDirIdEqualTo(ptUserFile.getDirId());
        }

        int i = ptUserFileMapper.deleteByExample(example);
        return i == 0 ? false:true;
    }

    /**
     * 查询相册中所有文件
     * @param
     * @return
     */
    public List<PtUserFile> selectAllByDirId(Integer dirId){
        PtUserFileExample example = new PtUserFileExample();
        example.setOrderByClause("dir_id");
        PtUserFileExample.Criteria criteria = example.createCriteria();
        criteria.andDirIdEqualTo(dirId);
        return ptUserFileMapper.selectByExample(example);
    }

    /**
     * 根据id查询
     * @param
     * @return
     */
    public PtUserFile selectByUserFileId(Integer userFileId){
        if(userFileId == null){
            return null;
        }
        return ptUserFileMapper.selectByPrimaryKey(userFileId);
    }

    /**
     * 当列表不为空，返回第一个元素，为空返回null
     * @param
     * @return
     */
    private PtUserFile returnFrist(List<PtUserFile> list){
        if(CollectionUtils.isEmpty(list)){
            return null;
        }

        return list.get(0);
    }

}
