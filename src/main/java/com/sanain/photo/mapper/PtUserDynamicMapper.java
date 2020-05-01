package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtFriend;
import com.sanain.photo.pojo.PtUserDynamic;
import com.sanain.photo.pojo.PtUserDynamicExample;
import java.util.List;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PtUserDynamicMapper {
    long countByExample(PtUserDynamicExample example);

    int deleteByExample(PtUserDynamicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PtUserDynamic record);

    int insertSelective(PtUserDynamic record);

    List<PtUserDynamic> selectByExample(PtUserDynamicExample example);

    PtUserDynamic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PtUserDynamic record, @Param("example") PtUserDynamicExample example);

    int updateByExample(@Param("record") PtUserDynamic record, @Param("example") PtUserDynamicExample example);

    int updateByPrimaryKeySelective(PtUserDynamic record);

    int updateByPrimaryKey(PtUserDynamic record);

    List<PtUserDynamic> selectAllFriendDynamic(List<PtFriend> list);

    List<PtUserDynamic> selectAllDynamic(@Param("userName")String userName , @Param("createTime")String createTime);

}