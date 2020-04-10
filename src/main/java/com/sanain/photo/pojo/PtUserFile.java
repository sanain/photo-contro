package com.sanain.photo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户上传的文件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PtUserFile {
    /*文件id*/
    private Integer fileId;
    /*文件名*/
    private String fileName;
    /*文件路路径*/
    private String filePath;
    /*创建时间*/
    private Date createTime;
    /*文件所属文件夹id*/
    private Integer dirId;
}