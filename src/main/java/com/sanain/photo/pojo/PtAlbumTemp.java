package com.sanain.photo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 动态相册模板
 */
@Data
public class PtAlbumTemp {
    private Integer id;

    private String name;

    private String coverPaths;

    private String photoPaths;

    private String jsPaths;

    private String cssPaths;

    private String htmlStr;

    private Date createTime;

    private Integer photoCount;

    private String tempRemark;

}