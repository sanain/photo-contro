package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtDirType;
import com.sanain.photo.pojo.PtDirTypeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户文件夹分类的mapper
 */
@Mapper
public interface PtDirTypeMapper {
    long countByExample(PtDirTypeExample example);

    int deleteByExample(PtDirTypeExample example);

    int deleteByPrimaryKey(Integer typeId);

    int insert(PtDirType record);

    int insertSelective(PtDirType record);

    List<PtDirType> selectByExample(PtDirTypeExample example);

    PtDirType selectByPrimaryKey(Integer typeId);

    int updateByExampleSelective(@Param("record") PtDirType record, @Param("example") PtDirTypeExample example);

    int updateByExample(@Param("record") PtDirType record, @Param("example") PtDirTypeExample example);

    int updateByPrimaryKeySelective(PtDirType record);

    int updateByPrimaryKey(PtDirType record);

    List<PtDirType> selectAll();
}