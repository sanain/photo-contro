package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtUserAlbum;
import com.sanain.photo.pojo.PtUserAlbumExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户动态相册的mapper
 */
@Mapper
public interface PtUserAlbumMapper {
    long countByExample(PtUserAlbumExample example);

    int deleteByExample(PtUserAlbumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PtUserAlbum record);

    int insertSelective(PtUserAlbum record);

    List<PtUserAlbum> selectByExample(PtUserAlbumExample example);

    PtUserAlbum selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PtUserAlbum record, @Param("example") PtUserAlbumExample example);

    int updateByExample(@Param("record") PtUserAlbum record, @Param("example") PtUserAlbumExample example);

    int updateByPrimaryKeySelective(PtUserAlbum record);

    int updateByPrimaryKey(PtUserAlbum record);

    PtUserAlbum selectUnionByPrimaryKey(Integer id);


}