package com.sanain.photo.controller;

import com.sanain.photo.pojo.PtFriendApply;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.service.PtFriendApplyService;
import com.sanain.photo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 好友申请的controller
 * @Author sanain
 */
@Controller
@RequestMapping("/friendApply")
public class PtFriendApplyController {
    @Autowired
    private PtFriendApplyService ptFriendApplyService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取所有的申请
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/getAllApply")
    public Map<String,Object> getAllApply(HttpServletRequest request){
        //获取当前的用户
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        PtUser ptUser = JsonUtils.toObject(o.toString(), PtUser.class);

        List<PtFriendApply> allApply = ptFriendApplyService.getAllApply(ptUser.getUserId());

        if(!CollectionUtils.isEmpty(allApply)){
            for(PtFriendApply apply : allApply){
                apply.setFromPhoto(ConstantUtil.PRO_PATH+ ConstantUtil.PATH_HEAD_PORTRAIT+apply.getFromPhoto());
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("applyList",allApply);

        return ResponseUtils.packaging("00","查询成功",map);
    }


    /**
     * 处理申请
     * @return
     */
    @ResponseBody
    @PostMapping("/dealApply")
    public Map<String , Object> dealApply(PtFriendApply apply){
        Map<String,Object> map = new HashMap<>();

        if(apply.getId() == null){
            map.put("result",false);
            map.put("msg","好友申请的id不能为空");
            return ResponseUtils.packaging("00","好友申请的id不能为空",map);
        }

        map = ptFriendApplyService.dealApply(apply);

        return ResponseUtils.packaging("00",map.get("msg").toString(),map);
    }


    /**
     * 添加好友申请
     * @param apply
     * @return
     */
    @ResponseBody
    @PostMapping("/insertApply")
    public Map<String , Object> insertApply(PtFriendApply apply,HttpServletRequest request){
        /*从cookie中取出token*/
        String token = TokenUtils.getInstance().getToken(request);
        /*取出当前用户*/
        Object o = redisUtil.get(token);
        /*把json转成对象*/
        PtUser ptUser  = JsonUtils.toObject(o.toString(), PtUser.class);

        /*插入数据库*/
        Map<String, Object> map = ptFriendApplyService.insert(apply,ptUser);

        return ResponseUtils.packaging("00",map.get("msg").toString(),map);
    }


    /**
     * 删除好友申请
     * @param applyId
     * @return
     */
    @ResponseBody
    @GetMapping("/deleteApply")
    public Map<String , Object> deleteApply(Integer applyId){
        if(applyId == null){
            return ResponseUtils.packaging("00","申请id不能为空",null);
        }

        boolean b = ptFriendApplyService.deleteApply(applyId);

        Map<String , Object> map = new HashMap<>();
        map.put("result",b);

        return ResponseUtils.packaging("00","删除成功",map);
    }
}
