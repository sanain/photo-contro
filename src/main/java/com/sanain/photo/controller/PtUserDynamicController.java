package com.sanain.photo.controller;

import com.github.pagehelper.PageInfo;
import com.sanain.photo.pojo.*;
import com.sanain.photo.service.PtFriendService;
import com.sanain.photo.service.PtUserDynamicService;
import com.sanain.photo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 好友动态的controller
 * @Author sanain
 */
@RequestMapping("/dynamic")
@Controller
public class PtUserDynamicController {
    @Autowired
    private PtUserDynamicService ptUserDynamicService;
    @Autowired
    private PtFriendService ptFriendService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取所有的好友动态
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllFriendDynamic")
    public Map<String,Object> getAllFriendDynamic(HttpServletRequest request,
                                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "5") Integer pageSize){
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        PtUser ptUser = JsonUtils.toObject(o.toString(), PtUser.class);

//        获取所有的好友
        List<PtFriend> friendList = ptFriendService.getAllBySelfId(ptUser.getUserId());
        PtFriend ptFriend = new PtFriend();
        ptFriend.setFriendId(ptUser.getUserId());
//        把自己加入到好友列表
        friendList.add(ptFriend);

        PageInfo<PtUserDynamic> pageInfo = ptUserDynamicService.getAllFriendDynamic(friendList, pageNum, pageSize);

        Map<String,Object> map = new HashMap<>();
        map.put("pageInfo",pageInfo);

        return ResponseUtils.packaging("00","查询成功",map);
    }

    /**
     * 上传好友动态图片
     * @param file
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadDynamicImg")
    public Map<String , Object> uploadDynamicImg(MultipartFile file ){
        if(file == null){
            return ResponseUtils.packaging("01","上传的文件错误",null);
        }

        //拼凑图片保存的路径
        String path = ConstantUtil.DYNAMIC_IMG;
        //把图片保存到本地磁盘
        String fileName = FileUtils.saveOneFile(file, path);

        Map<String,Object> map = new HashMap<>();
        map.put("fileName",fileName);
        return ResponseUtils.packaging("00","上传成功",map);
    }

    /**
     * 删除好友动态图片
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteDynamicImg")
    public Map<String , Object> deleteDynamicImg(String fileName){
        if(StringUtils.isEmpty(fileName)){
            return ResponseUtils.packaging("01","删除的图片名不能为空",null);
        }

        Map<String,Object> map = new HashMap<>();
        //拼凑图片保存的路径
        String path = ConstantUtil.DYNAMIC_IMG+fileName;
        File file = new File(path);
        if(file.exists()){
            file.delete();
            map.put("fileName",fileName);
            return ResponseUtils.packaging("00","删除成功",map);
        }


        map.put("fileName",fileName);
        return ResponseUtils.packaging("01","图片不存在",map);
    }


    /**
     * 新增好友动态
     * @param ptUserDynamic
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertDynamic")
    public Map<String,Object> insertDynamic(PtUserDynamic ptUserDynamic){
        boolean b = ptUserDynamicService.insertDynamic(ptUserDynamic);

        String status = b ? "00" : "01";
        String msg = b ? "插入成功" : "插入失败";

        return ResponseUtils.packaging(status,msg,null);
    }

    /**
     * 删除好友动态
     * @param dynamicId
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteDynamic")
    public Map<String,Object> deleteDynamic(Integer dynamicId){
        boolean b = ptUserDynamicService.deleteDynamic(dynamicId);

        String status = b ? "00" : "01";
        String msg = b ? "删除成功" : "删除失败";

        return ResponseUtils.packaging(status,msg,null);
    }
}
