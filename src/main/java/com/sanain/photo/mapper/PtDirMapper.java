package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtDir;
import com.sanain.photo.pojo.PtDirExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户文件夹的mapper
 */
@Mapper
public interface PtDirMapper {
    long countByExample(PtDirExample example);

    int deleteByExample(PtDirExample example);

    int deleteByPrimaryKey(Integer dirId);

    int insert(PtDir record);

    int insertSelective(PtDir record);

    List<PtDir> selectByExample(PtDirExample example);

    PtDir selectByPrimaryKey(Integer dirId);

    int updateByExampleSelective(@Param("record") PtDir record, @Param("example") PtDirExample example);

    int updateByExample(@Param("record") PtDir record, @Param("example") PtDirExample example);

    int updateByPrimaryKeySelective(PtDir record);

    int updateByPrimaryKey(PtDir record);
}