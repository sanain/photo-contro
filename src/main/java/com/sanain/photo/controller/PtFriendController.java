package com.sanain.photo.controller;

import com.sanain.photo.pojo.PtFriend;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.service.PtFriendService;
import com.sanain.photo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @Author sanain
 */
@Controller
@RequestMapping("/friend")
public class PtFriendController {
    @Autowired
    private PtFriendService ptFriendService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取当前用户的所有好友
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/getAllFriend")
    public Map<String,Object> getAllFriend(HttpServletRequest request){
        /*获取当前用户*/
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        PtUser user = JsonUtils.toObject(o.toString(), PtUser.class);

        //从数据库中查询
        List<PtFriend> ptFriends = ptFriendService.getAllBySelfId(user.getUserId());

        //拼凑头像路径
        if(!CollectionUtils.isEmpty(ptFriends)){
            for (PtFriend ptFriend:ptFriends) {
                ptFriend.setFriendPhoto(ConstantUtil.PRO_PATH+ConstantUtil.PATH_HEAD_PORTRAIT+ptFriend.getFriendPhoto());
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("allFriend",ptFriends);

        return ResponseUtils.packaging("00","查询成功",map);
    }

    /**
     * 获取当前用户的所有好友
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/getFriendInfo")
    public Map<String,Object> getFriendInfo(HttpServletRequest request,Integer friendId){
        /*获取当前用户*/
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        PtUser user = JsonUtils.toObject(o.toString(), PtUser.class);

        //从数据库中查询
        PtFriend ptFriend = ptFriendService.getAllBySelfIdAndFriendId(user.getUserId(),friendId);
        //拼凑头像路径
        ptFriend.setFriendPhoto(ConstantUtil.PRO_PATH+ConstantUtil.PATH_HEAD_PORTRAIT+ptFriend.getFriendPhoto());

        /*查询出来好友在pt_user表中的信息*/
        PtUser friendUserInfo = ptFriendService.getFriendInfoById(ptFriend.getId());
        ptFriend.setFriendName(friendUserInfo.getUserName());
        Map<String,Object> map = new HashMap<>();
        map.put("friendInfo",ptFriend);

        return ResponseUtils.packaging("00","查询成功",map);
    }
}
