package com.sanain.photo.controller;

import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.service.PtUserService;
import com.sanain.photo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传controller
 * @Author sanain
 */
@RequestMapping("/upload")
@Controller
public class UploadController {
    @Autowired
    private PtUserService ptUserService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 上传头像
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/headPortraitUpload")
    public Map<String,Object> headPortraitUpload(MultipartFile file ,String token, HttpServletRequest request){
        Map<String , Object> map = new HashMap<>();

        String filePath = FileUtils.saveOneFile(file, ConstantUtil.HEAD_PORTRAIT);

        if(StringUtils.isEmpty(file)){
            return ResponseUtils.packaging("01","保存文件错误！",null);
        }

        Object o = redisUtil.get(token);
        if(o == null){
            return ResponseUtils.packaging("01","登录信息过期！",null);
        }

        PtUser ptUser = JsonUtils.toObject(o.toString(), PtUser.class);

        //删除缓存
        redisUtil.del(token);
        ptUser.setPhotoPath(filePath);
        ptUser = ptUserService.updateUser(ptUser);
        if(ptUser == null){
            return ResponseUtils.packaging("01","更新用户信息错误！",null);
        }
        redisUtil.del(token);
        //更新信息之后写入缓存
        redisUtil.set(token,JsonUtils.toJson(ptUser),60*60*24);

        map.put("newPhonePath",ConstantUtil.PRO_PATH+ConstantUtil.PATH_HEAD_PORTRAIT+ptUser.getPhotoPath());

        return ResponseUtils.packaging("00","更新成功！",map);
    }
}
