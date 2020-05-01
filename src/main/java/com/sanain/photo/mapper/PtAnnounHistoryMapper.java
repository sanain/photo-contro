package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtAnnounHistory;
import com.sanain.photo.pojo.PtAnnounHistoryExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PtAnnounHistoryMapper {
    long countByExample(PtAnnounHistoryExample example);

    int deleteByExample(PtAnnounHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PtAnnounHistory record);

    int insertSelective(PtAnnounHistory record);

    List<PtAnnounHistory> selectByExample(PtAnnounHistoryExample example);

    PtAnnounHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PtAnnounHistory record, @Param("example") PtAnnounHistoryExample example);

    int updateByExample(@Param("record") PtAnnounHistory record, @Param("example") PtAnnounHistoryExample example);

    int updateByPrimaryKeySelective(PtAnnounHistory record);

    int updateByPrimaryKey(PtAnnounHistory record);
}