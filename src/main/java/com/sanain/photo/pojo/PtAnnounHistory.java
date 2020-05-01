package com.sanain.photo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 公告记录的pojo
 */
@Data
public class PtAnnounHistory {
    private Integer id;

    private Integer userId;

    private Integer announId;

    private Date createTime;


}