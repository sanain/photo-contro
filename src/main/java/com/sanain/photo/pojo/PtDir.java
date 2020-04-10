package com.sanain.photo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户文件夹的pojo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PtDir {
    /*文件夹id*/
    private Integer dirId;
    /*文件夹名*/
    private String dirName;
    /*文件夹所属的用户id*/
    private Integer dirUserId;
    /*文件夹所属分类id*/
    private Integer dirTypeId;
    /*创建时间*/
    private Date createTime;
    /*文件夹封面*/
    private String dirImg;
    /*文件夹备注*/
    private String remark;

}