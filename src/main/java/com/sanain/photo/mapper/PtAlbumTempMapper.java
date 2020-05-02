package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtAlbumTemp;
import com.sanain.photo.pojo.PtAlbumTempExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 动态相册的mapper
 */
@Mapper
public interface PtAlbumTempMapper {
    long countByExample(PtAlbumTempExample example);

    int deleteByExample(PtAlbumTempExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PtAlbumTemp record);

    int insertSelective(PtAlbumTemp record);

    List<PtAlbumTemp> selectByExample(PtAlbumTempExample example);

    PtAlbumTemp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PtAlbumTemp record, @Param("example") PtAlbumTempExample example);

    int updateByExample(@Param("record") PtAlbumTemp record, @Param("example") PtAlbumTempExample example);

    int updateByPrimaryKeySelective(PtAlbumTemp record);

    int updateByPrimaryKey(PtAlbumTemp record);
}