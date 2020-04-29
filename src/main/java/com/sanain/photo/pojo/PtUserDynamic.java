package com.sanain.photo.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PtUserDynamic {
    private Integer id;
    //评论的内容
    private String content;
    //动态图片的路径
    private String photoPaths;
    //点赞数量
    private Integer starCount;
    //评论数量
    private Integer commentCount;
    //创建时间
    private Date createTime;
    //    发动态的用户id
    private Integer userId;

    //    发动态的用户
    private PtUser user;

    //    评论列表
    private List<PtUserDynamicComment> commentList;

//    动态图片路径
    private List<String> photoList;
}
