package com.sanain.photo.service;

import com.sanain.photo.mapper.PtUserAlbumFileMapper;
import com.sanain.photo.pojo.PtUserAlbum;
import com.sanain.photo.pojo.PtUserAlbumFile;
import com.sanain.photo.pojo.PtUserAlbumFileExample;
import com.sanain.photo.util.ConstantUtil;
import com.sanain.photo.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户动态相册文件的service
 * @Author sanain
 */
@Service
public class PtUserAlbumFileService {
    @Autowired
    private PtUserAlbumFileMapper ptUserAlbumFileMapper;

    /**
     * 批量插入
     * @param fileList
     * @param album
     */
    @Transactional
    public void batchInsert(List<String> fileList, PtUserAlbum album){

        if(CollectionUtils.isEmpty(fileList)) {
            return;
        }
        List<PtUserAlbumFile> list = new ArrayList<>();

        for(String str : fileList){
            PtUserAlbumFile albumFile = new PtUserAlbumFile();
            albumFile.setFilePath(str);
            albumFile.setCreateTime(new Date());
            albumFile.setUserAlbumId(album.getId());

            list.add(albumFile);
        }
        ptUserAlbumFileMapper.batchInsert(list);
    }


    /**
     * 根据用户动态相册id删除
     * @param userAlbumId
     */
    @Transactional
    public void deleteByUserAlbumId(Integer userAlbumId){
        if(userAlbumId == null){
            return;
        }

//        删除文件
        List<PtUserAlbumFile> ptUserAlbumFiles = selectByUserAlbumId(userAlbumId);
        if(!CollectionUtils.isEmpty(ptUserAlbumFiles)){
            StringBuffer strBuf = new StringBuffer();
            for(PtUserAlbumFile file : ptUserAlbumFiles){
                strBuf.append(file.getFilePath()+";");
            }

            FileUtils.deleteFiles(strBuf.toString(),";", ConstantUtil.TEMP_IMG);
        }

//        删除数据库记录
        PtUserAlbumFileExample example = new PtUserAlbumFileExample();
        PtUserAlbumFileExample.Criteria criteria = example.createCriteria();
        criteria.andUserAlbumIdEqualTo(userAlbumId);

        ptUserAlbumFileMapper.deleteByExample(example);
    }

    /**
     * 根据用户动态相册id查询
     * @param userAlbumId
     */
    public List<PtUserAlbumFile> selectByUserAlbumId(Integer userAlbumId){
        if(userAlbumId == null){
            return null;
        }

        PtUserAlbumFileExample example = new PtUserAlbumFileExample();
        PtUserAlbumFileExample.Criteria criteria = example.createCriteria();
        criteria.andUserAlbumIdEqualTo(userAlbumId);

        List<PtUserAlbumFile> fileList = ptUserAlbumFileMapper.selectByExample(example);

        return fileList;
    }

    /**
     * 根据id查询
     * @param
     */
    public PtUserAlbumFile selectById(Integer id){
        if(id == null){
            return null;
        }

        return ptUserAlbumFileMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id删除
     * @param
     */
    @Transactional
    public boolean deleteById(Integer id){
        if(id == null){
            return false;
        }

        return ptUserAlbumFileMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 添加图片
     * @param
     * @return
     */
    @Transactional
    public PtUserAlbumFile insert(String fileName,PtUserAlbum album){
        PtUserAlbumFile file = new PtUserAlbumFile();

        file.setFilePath(fileName);
        file.setCreateTime(new Date());
        file.setUserAlbumId(album.getId());
        ptUserAlbumFileMapper.insertSelective(file);

        return file;
    }
}
