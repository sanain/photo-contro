package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtFriend;
import com.sanain.photo.pojo.PtFriendExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 好友关系表
 */
@Mapper
public interface PtFriendMapper {
    long countByExample(PtFriendExample example);

    int deleteByExample(PtFriendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PtFriend record);

    int insertSelective(PtFriend record);

    List<PtFriend> selectByExample(PtFriendExample example);

    PtFriend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PtFriend record, @Param("example") PtFriendExample example);

    int updateByExample(@Param("record") PtFriend record, @Param("example") PtFriendExample example);

    int updateByPrimaryKeySelective(PtFriend record);

    int updateByPrimaryKey(PtFriend record);
}