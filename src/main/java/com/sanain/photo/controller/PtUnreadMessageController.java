package com.sanain.photo.controller;

import com.sanain.photo.pojo.PtUnreadMessage;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.service.PtUnreadMessageService;
import com.sanain.photo.util.JsonUtils;
import com.sanain.photo.util.RedisUtil;
import com.sanain.photo.util.ResponseUtils;
import com.sanain.photo.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户未读消息的controller
 * @Author sanain
 */
@Controller
@RequestMapping("/unreadMessage")
public class PtUnreadMessageController {
    @Autowired
    private PtUnreadMessageService ptUnreadMessageService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询当前用户所有的未读信息
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/getAllUnreadMessage")
    public Map<String,Object> getAllUnreadMessage(HttpServletRequest request){
        /*获取当前用户*/
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        PtUser user = JsonUtils.toObject(o.toString(), PtUser.class);

        /*查询所有的维度信息*/
        List<PtUnreadMessage> allMessages = ptUnreadMessageService.getAllByToId(user.getUserId());

        /*删除当前用户的所有未读信息*/
        ptUnreadMessageService.deleteByToId(user.getUserId());

        Map<String,Object> map = new HashMap<>();
        map.put("allMessages",allMessages);

        return ResponseUtils.packaging("00","查询成功",map);
    }
}
