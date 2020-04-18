package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtFriendApply;
import com.sanain.photo.pojo.PtFriendApplyExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PtFriendApplyMapper {
    long countByExample(PtFriendApplyExample example);

    int deleteByExample(PtFriendApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PtFriendApply record);

    int insertSelective(PtFriendApply record);

    List<PtFriendApply> selectByExample(PtFriendApplyExample example);

    PtFriendApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PtFriendApply record, @Param("example") PtFriendApplyExample example);

    int updateByExample(@Param("record") PtFriendApply record, @Param("example") PtFriendApplyExample example);

    int updateByPrimaryKeySelective(PtFriendApply record);

    int updateByPrimaryKey(PtFriendApply record);
}