package com.sanain.photo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 未读消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PtUnreadMessage {
    /*消息id*/
    private Integer id;
    /*发送者id*/
    private Integer fromId;
    /*接受者id*/
    private Integer toId;
    /*消息内容*/
    private String message;
    /*创建时间*/
    private Date createDate;
}