package com.sanain.photo.mapper;

import com.sanain.photo.pojo.PtUserDynamicComment;
import com.sanain.photo.pojo.PtUserDynamicCommentExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PtUserDynamicCommentMapper {
    long countByExample(PtUserDynamicCommentExample example);

    int deleteByExample(PtUserDynamicCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PtUserDynamicComment record);

    int insertSelective(PtUserDynamicComment record);

    List<PtUserDynamicComment> selectByExample(PtUserDynamicCommentExample example);

    PtUserDynamicComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PtUserDynamicComment record, @Param("example") PtUserDynamicCommentExample example);

    int updateByExample(@Param("record") PtUserDynamicComment record, @Param("example") PtUserDynamicCommentExample example);

    int updateByPrimaryKeySelective(PtUserDynamicComment record);

    int updateByPrimaryKey(PtUserDynamicComment record);

    List<PtUserDynamicComment> selectAllCommentByDynamicId(Integer dynamicId);

    PtUserDynamicComment selectAllInfoById(Integer id);
}