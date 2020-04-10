package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtUserFile;
import com.sanain.photo.pojo.PtUserFileExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户上传的文件Mapper
 */
@Mapper
public interface PtUserFileMapper {
    long countByExample(PtUserFileExample example);

    int deleteByExample(PtUserFileExample example);

    int deleteByPrimaryKey(Integer fileId);

    int insert(PtUserFile record);

    int insertSelective(PtUserFile record);

    List<PtUserFile> selectByExample(PtUserFileExample example);

    PtUserFile selectByPrimaryKey(Integer fileId);

    int updateByExampleSelective(@Param("record") PtUserFile record, @Param("example") PtUserFileExample example);

    int updateByExample(@Param("record") PtUserFile record, @Param("example") PtUserFileExample example);

    int updateByPrimaryKeySelective(PtUserFile record);

    int updateByPrimaryKey(PtUserFile record);

    int insertBatch(@Param("list") List<PtUserFile> list);

    int deleteBatch(@Param("list") List<Integer> list);

    List<PtUserFile> selectAll();
}