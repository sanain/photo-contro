package com.sanain.photo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PtFriendApply {
    /*申请id*/
    private Integer id;
    /*申请者id*/
    private Integer fromId;
    /*添加的好友的id*/
    private Integer toId;
    /*申请备注*/
    private String remark;
    /*创建时间*/
    private Date createDate;
    /*是否已经处理申请的标志  0 未处理  1已处理*/
    private Integer isDeal;
    /*处理结果 0 已拒绝  1已同意*/
    private Integer result;

}