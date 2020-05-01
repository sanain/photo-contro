package com.sanain.photo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户的pojo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PtUser {
    /*用户id*/
    private Integer userId;
    /*用户邮箱*/
    private String userEmail;
    /*用户名*/
    private String userName;
    /*用户密码*/
    private String userPassword;
    /*头像路劲*/
    private String photoPath;
    /*角色*/
    private String role;
    /*个性签名*/
    private String signature;
    /*省*/
    private String province;
    /*市*/
    private String city;
    /*详细地址*/
    private String address;
    /*用户手机号*/
    private String userPhone;
    /*是否禁用 0 未禁用 1已禁用*/
    private Integer isUse;
    /*是否禁用的字符串 不写入数据库*/
    private String isUseStr;
    /*禁用的理由*/
    private String disableReason;
    /*创建的时间*/
    private Date createTime;

    /*不插入数据库，用户角色对象*/
    private PtRole ptRole;
}