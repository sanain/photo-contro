package com.sanain.photo.pojo;

import java.util.Date;

public class PtFriendApply {
    private Integer id;

    private Integer fromId;

    private Integer toId;

    private String remark;

    private Date createDate;

    private Integer isDeal;

    private Integer result;

    private String fromPhoto;

    private String fromUserName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsDeal() {
        return isDeal;
    }

    public void setIsDeal(Integer isDeal) {
        this.isDeal = isDeal;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getFromPhoto() {
        return fromPhoto;
    }

    public void setFromPhoto(String fromPhoto) {
        this.fromPhoto = fromPhoto == null ? null : fromPhoto.trim();
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName == null ? null : fromUserName.trim();
    }
}