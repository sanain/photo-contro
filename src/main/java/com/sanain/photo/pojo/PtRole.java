package com.sanain.photo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class PtRole {
    private Integer roleId;

    private String roleName;

    private Integer roleIsUse;

    private Date roleCreateTime;

}