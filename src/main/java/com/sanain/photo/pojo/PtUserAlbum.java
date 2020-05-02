package com.sanain.photo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PtUserAlbum {
    private Integer id;

    private String name;

    private String coverPaths;

    private Date createTime;

    private Integer tempId;

    private Integer userId;

    private String albumRemark;

    private List<PtUserAlbumFile> files;

    private PtUser ptUser;

    private PtAlbumTemp temp;
}