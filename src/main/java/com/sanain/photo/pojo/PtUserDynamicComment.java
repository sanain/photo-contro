package com.sanain.photo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 评论表
 */
@Data
public class PtUserDynamicComment {
    private Integer id;

    private Integer userId;

    private PtUser user;

    private Integer commentUseId;

    private PtUser commentUser;

    private String message;

    private Date createTime;

    private Integer dynamicId;


}