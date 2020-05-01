package com.sanain.photo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 公告表的poj0
 */
@Data
public class PtAnnoun {
    private Integer id;

    private String title;

    private String content;

    private Date createTime;

}