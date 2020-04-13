package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtUnreadMessage;
import com.sanain.photo.pojo.PtUnreadMessageExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 未读消息mapper
 */
@Mapper
public interface PtUnreadMessageMapper {
    long countByExample(PtUnreadMessageExample example);

    int deleteByExample(PtUnreadMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PtUnreadMessage record);

    int insertSelective(PtUnreadMessage record);

    List<PtUnreadMessage> selectByExample(PtUnreadMessageExample example);

    PtUnreadMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PtUnreadMessage record, @Param("example") PtUnreadMessageExample example);

    int updateByExample(@Param("record") PtUnreadMessage record, @Param("example") PtUnreadMessageExample example);

    int updateByPrimaryKeySelective(PtUnreadMessage record);

    int updateByPrimaryKey(PtUnreadMessage record);
}