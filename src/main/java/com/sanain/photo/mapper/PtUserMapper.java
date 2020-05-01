package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.pojo.PtUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PtUserMapper {
    long countByExample(PtUserExample example);

    int deleteByExample(PtUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(PtUser record);

    int insertSelective(PtUser record);

    List<PtUser> selectByExample(PtUserExample example);

    PtUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") PtUser record, @Param("example") PtUserExample example);

    int updateByExample(@Param("record") PtUser record, @Param("example") PtUserExample example);

    int updateByPrimaryKeySelective(PtUser record);

    int updateByPrimaryKey(PtUser record);

    List<PtUser> selectUnionByExample(PtUserExample example);

    PtUser selectUnionByPrimaryKey(Integer id);
}