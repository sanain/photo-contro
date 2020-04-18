package com.sanain.photo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 好友关系的pojo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PtFriend {
    /**关系id**/
    private Integer id;
    /*自己的id*/
    private Integer selfId;
    /*好友的id*/
    private Integer friendId;
    /*创建时间*/
    private Date createTime;
    /*昵称的备注*/
    private String remark;
    /*好友头像*/
    private String friendPhoto;

}