package com.sanain.photo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class PtUnreadMessage {
    private Integer id;

    private Integer fromId;

    private Integer toId;

    private String message;

    private Date createTime;

    private Integer userId;

    private String fromPhoto;

    private String fromRemark;

    private Integer isRead;

}