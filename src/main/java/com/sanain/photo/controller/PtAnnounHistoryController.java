package com.sanain.photo.controller;

import com.alibaba.fastjson.JSON;
import com.sanain.photo.pojo.PtAnnounHistory;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.service.PtAnnounHistoryService;
import com.sanain.photo.util.JsonUtils;
import com.sanain.photo.util.RedisUtil;
import com.sanain.photo.util.ResponseUtils;
import com.sanain.photo.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 公告记录的controller
 * @Author sanain
 */
@Controller
@RequestMapping("/announHistory")
public class PtAnnounHistoryController {
    @Autowired
    private PtAnnounHistoryService ptAnnounHistoryService;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 检测一个用户对一个公告是否已读
     * @param
     * @return
     */
    @RequestMapping("/isHasRead")
    @ResponseBody
    public Map<String,Object> isHasRead(Integer announId , HttpServletRequest request){
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        PtUser ptUser = JsonUtils.toObject(o.toString(),PtUser.class);

        if(announId == null){
            return ResponseUtils.packaging("01","公告id不能为空",null);
        }

        PtAnnounHistory history = ptAnnounHistoryService.selectByAnnounIdAndUserId(announId,ptUser.getUserId());


        Boolean b = history == null ? false : true;
        Map<String,Object> map = new HashMap<>();
        map.put("isHasRead",b);
        return ResponseUtils.packaging("00","查询成功",map);
    }

    /**
     * 插入记录
     * @param
     * @return
     */
    @RequestMapping("/insertHistory")
    @ResponseBody
    public Map<String,Object> isHasRead(PtAnnounHistory history, HttpServletRequest request){
//        获取所有的用户
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        PtUser ptUser = JsonUtils.toObject(o.toString(), PtUser.class);

//        插入记录
        history.setUserId(ptUser.getUserId());
        boolean b = ptAnnounHistoryService.insertHistory(history);


        String msg = b?"插入成功":"插入失败";
        String status = b?"00":"01";
        return ResponseUtils.packaging(status,msg,null);
    }
}
