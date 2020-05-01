package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtAnnoun;
import com.sanain.photo.pojo.PtAnnounExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PtAnnounMapper {
    long countByExample(PtAnnounExample example);

    int deleteByExample(PtAnnounExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PtAnnoun record);

    int insertSelective(PtAnnoun record);

    List<PtAnnoun> selectByExample(PtAnnounExample example);

    PtAnnoun selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PtAnnoun record, @Param("example") PtAnnounExample example);

    int updateByExample(@Param("record") PtAnnoun record, @Param("example") PtAnnounExample example);

    int updateByPrimaryKeySelective(PtAnnoun record);

    int updateByPrimaryKey(PtAnnoun record);
}