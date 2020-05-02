package com.sanain.photo.controller;

import com.github.pagehelper.PageInfo;
import com.sanain.photo.pojo.PtAlbumTemp;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.pojo.PtUserAlbum;
import com.sanain.photo.service.PtAlbumTempService;
import com.sanain.photo.service.PtUserAlbumService;
import com.sanain.photo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户动态相册的controller
 * @Author sanain
 */
@RestController
@RequestMapping("/userAlbum")
public class PtUserAlbumController {
    @Autowired
    private PtUserAlbumService ptUserAlbumService;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 分页条件查询
     * @param
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/selectListByExample")
    public Map<String,Object> selectListByExample(PtUserAlbum album,HttpServletRequest request,
                                                  @RequestParam(defaultValue = "1") Integer pageNum ,
                                                  @RequestParam(defaultValue = "10") Integer pageSize) {
//      获得当前用户id
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        PtUser ptUser = JsonUtils.toObject(o.toString(), PtUser.class);
        album.setUserId(ptUser.getUserId());

        PageInfo<PtUserAlbum> pageInfo = ptUserAlbumService.getListByExample(album, pageNum, pageSize);

        Map<String, Object> map = new HashMap<>();
        map.put("pageInfo", pageInfo);

        return ResponseUtils.packaging("00", "查询成功", map);
    }


    /**
     * 插入用户动态相册
     * @param ptUserAlbum
     * @param
     * @return
     */
    @PostMapping("/insertUserAlbum")
    public Map<String,Object> insertUserAlbum(PtUserAlbum ptUserAlbum , String fileNames, HttpServletRequest request){
//        获得当前用户id
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        PtUser ptUser = JsonUtils.toObject(o.toString(), PtUser.class);

        ptUserAlbum.setUserId(ptUser.getUserId());
        boolean b = ptUserAlbumService.insertAlbum(ptUserAlbum, fileNames);

        String msg = b ? "插入成功":"插入失败";
        String status = b ? "00":"01";

        return ResponseUtils.packaging(status,msg,null);
    }


    /**
     * 删除用户动态相册
     * @param id
     * @return
     */
    @GetMapping("/deleteUserAlbum")
    public Map<String,Object> deleteUserAlbum(Integer id){
        Map<String, Object> map = ptUserAlbumService.deleteAlbum(id);

        String status = (boolean) map.get("result") ? "00":"01";
        String msg = (String) map.get("msg");

        return ResponseUtils.packaging(status,msg,null);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("selectById")
    public Map<String,Object> selectById(Integer id){
        if(id == null){
            return ResponseUtils.packaging("01","id不能为空",null);
        }

        PtUserAlbum ptUserAlbum = ptUserAlbumService.selectById(id);


        Map<String,Object> map = new HashMap<>();
        map.put("albumInfo",ptUserAlbum);

        return ResponseUtils.packaging("00","查询成功",map);
    }
}
