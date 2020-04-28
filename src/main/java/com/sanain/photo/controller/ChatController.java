package com.sanain.photo.controller;

import com.sanain.photo.config.SpringUtil;
import com.sanain.photo.pojo.PtFriend;
import com.sanain.photo.pojo.PtUnreadMessage;

import com.sanain.photo.service.PtFriendService;
import com.sanain.photo.service.PtUnreadMessageService;

import com.sanain.photo.util.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 在线聊天的controller
 * @Author sanain
 */
@ServerEndpoint(value = "/chat")
@Component
@Slf4j
public class ChatController {
    private static Map<String , Session> sessionMap = new HashMap<>();

    /**
     * 连接
     * @param session
     * @return
     */
    @OnOpen
    public void onOpen(Session session){
        Integer userId = Integer.parseInt(session.getRequestParameterMap().get("userId").get(0));

        sessionMap.put(userId+"",session);
        System.out.println("有新链接");
        System.out.println("目前有的链接为："+sessionMap.keySet().size());

    }


    /**
     * 断开连接
     * @param
     * @param
     * @return
     */
    @OnClose
    public void onClose(Session session){

        Integer userId = Integer.parseInt(session.getRequestParameterMap().get("userId").get(0));

        sessionMap.remove(userId+"");

        System.out.println("有链接断开");
        System.out.println("目前有的链接为："+sessionMap.keySet().size());

    }


    /**
     * 发送信息
     * @param unreadMessage
     * @param
     */
    @OnMessage
    public void onMessage(String unreadMessage){
        PtUnreadMessage ptUnreadMessage = JsonUtils.toObject(unreadMessage, PtUnreadMessage.class);
        PtFriendService ptFriendService = SpringUtil.getBean(PtFriendService.class);
        PtUnreadMessageService ptUnreadMessageService = SpringUtil.getBean(PtUnreadMessageService.class);

        Integer toId = ptUnreadMessage.getToId();

        Session session = sessionMap.get(toId+"");

        PtFriend ptFriend = ptFriendService.getAllBySelfIdAndFriendId(ptUnreadMessage.getToId(), ptUnreadMessage.getFromId());
        ptUnreadMessage.setFromRemark(ptFriend.getRemark());
        /*如果o为null，说明对方不在线，就需要存入到数据库中*/
        if(session == null){
            ptUnreadMessageService.insertUnreadMessage(ptUnreadMessage);
            return;
        }

        try {
            //向对方发送信息
            session.getBasicRemote().sendText(JsonUtils.toJson(ptUnreadMessage));
        } catch (IOException e) {
            ptUnreadMessageService.insertUnreadMessage(ptUnreadMessage);
            e.printStackTrace();
        }

    }

    /**
     * 发生错误
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.info("sessionId为"+session.getId()+"发生了错误"+error.getMessage());
    }
}



