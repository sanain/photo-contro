package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtRole;
import com.sanain.photo.pojo.PtRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PtRoleMapper {
    long countByExample(PtRoleExample example);

    int deleteByExample(PtRoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(PtRole record);

    int insertSelective(PtRole record);

    List<PtRole> selectByExample(PtRoleExample example);

    PtRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") PtRole record, @Param("example") PtRoleExample example);

    int updateByExample(@Param("record") PtRole record, @Param("example") PtRoleExample example);

    int updateByPrimaryKeySelective(PtRole record);

    int updateByPrimaryKey(PtRole record);
}