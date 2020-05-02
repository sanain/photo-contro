package com.sanain.photo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class PtUserAlbumFile {
    private Integer fileId;

    private String filePath;

    private Date createTime;

    private Integer userAlbumId;


}