package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtUserAlbumFile;
import com.sanain.photo.pojo.PtUserAlbumFileExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PtUserAlbumFileMapper {
    long countByExample(PtUserAlbumFileExample example);

    int deleteByExample(PtUserAlbumFileExample example);

    int deleteByPrimaryKey(Integer fileId);

    int insert(PtUserAlbumFile record);

    int insertSelective(PtUserAlbumFile record);

    List<PtUserAlbumFile> selectByExample(PtUserAlbumFileExample example);

    PtUserAlbumFile selectByPrimaryKey(Integer fileId);

    int updateByExampleSelective(@Param("record") PtUserAlbumFile record, @Param("example") PtUserAlbumFileExample example);

    int updateByExample(@Param("record") PtUserAlbumFile record, @Param("example") PtUserAlbumFileExample example);

    int updateByPrimaryKeySelective(PtUserAlbumFile record);

    int updateByPrimaryKey(PtUserAlbumFile record);

    int batchInsert(List<PtUserAlbumFile> fileList);
}