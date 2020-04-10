package com.sanain.photo.service;

import com.sanain.photo.mapper.PtUserFileMapper;
import com.sanain.photo.pojo.PtDir;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.pojo.PtUserFile;
import com.sanain.photo.pojo.PtUserFileExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 用户上传文件的service
 * @Author sanain
 */
@Service
public class PtUserFileService {
    @Autowired
    private PtUserFileMapper ptUserFileMapper;

    /**
     * 批量插入
     * @param
     * @return
     */
    @Transactional
    public boolean insertBatch(List<String> fileNames, PtDir ptDir, PtUser ptUser){
        List<PtUserFile> list = new ArrayList<>();

        for(String str : fileNames){
            PtUserFile ptUserFile = new PtUserFile();
            ptUserFile.setCreateTime(new Date());
            ptUserFile.setDirId(ptDir.getDirId());
            ptUserFile.setFilePath(ptUser.getUserId()+"/"+ptDir.getDirId()+"/");
            ptUserFile.setFileName(str);

            list.add(ptUserFile);
        }

        int i = ptUserFileMapper.insertBatch(list);

        return i == 0 ? false:true;
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
        PtUserFileExample.Criteria criteria = example.createCriteria();
        criteria.andDirIdEqualTo(dirId);
        return ptUserFileMapper.selectByExample(example);
    }

}
