package com.sanain.photo.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.service.PtUserService;
import com.sanain.photo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户controller
 * @Author sanain
 */
@RequestMapping("/user")
@Controller
public class PtUserController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PtUserService ptUserService;

    /**
     * 登录
     * @param ptUser
     * @param code
     * @param sessionId
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public Map<String , Object> login( HttpServletRequest request, HttpServletResponse response ,PtUser ptUser, String code, String sessionId){
        sessionId = request.getSession().getId();
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        System.out.println("登录时的session="+sessionId);
        if(StringUtils.isEmpty(sessionId)){
            return ResponseUtils.packaging("01","sessionId 为空！",null);
        }
        if(StringUtils.isEmpty(code)){
            return ResponseUtils.packaging("01","验证码不能 为空！",null);
        }
        code = code.toUpperCase();

        //检查输入的验证码是否正确
        Object codeObject = redisUtil.get(ConstantUtil.LOGIN_CODE+sessionId);
        if(codeObject== null || !code.equals(codeObject.toString())){
            return ResponseUtils.packaging("01","验证码错误！",null);
        }

        //检查用户名和密码是否正确
        PtUser user = ptUserService.selectByEmailAndPassword(ptUser);
        if(user == null){
            return ResponseUtils.packaging("01","用户名或者密码错误！",null);
        }

        //获取token
        String token = getToken(user);

        //把令牌放在cookie里面
        Cookie cookie = new Cookie(ConstantUtil.COOKIE_TOKEN,token);
        response.addCookie(cookie);
        redisUtil.del(token);
        return ResponseUtils.packaging("00","登录成功！",null);
    }


    /**
     * 获取时的验证码图片
     * @param response
     * @param request
     * @return
     */

    @RequestMapping("/getLoginCode")
    @ResponseBody
    public String getSysManageLoginCode(HttpServletResponse response,
                                        HttpServletRequest request) {
        String sessionId = request.getSession().getId();

        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");

        response.setDateHeader("Expire", 0);

        VerifyUtil VerifyUtil = new VerifyUtil();
        try {
            String randCode = VerifyUtil.getRandcode(request, response);// 输出图片方法
            System.out.println("图片的session="+sessionId);
            //验证码放入缓存之中,并设置过期时间为五分钟
            redisUtil.set(ConstantUtil.LOGIN_CODE+sessionId,randCode.toUpperCase(),60*5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 注册
     * @param ptUser
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public Map<String,Object> register(PtUser ptUser,String code,HttpServletResponse response,HttpServletRequest request){
        if(StringUtils.isEmpty(ptUser.getUserEmail())){
            return ResponseUtils.packaging("01","邮箱不能为空！",null);
        }

        if(StringUtils.isEmpty(ptUser.getUserPassword())){
            return ResponseUtils.packaging("01","密码不能为空！",null);
        }

        if(StringUtils.isEmpty(code)){
            return ResponseUtils.packaging("01","验证码不能为空！",null);
        }

        String id = request.getSession().getId();
        Object o = redisUtil.get(ConstantUtil.EMAIL_CODE + id);
        if(o == null || !code.equals(o.toString())){
            return ResponseUtils.packaging("01","验证码错误！",null);
        }
        //调用service层插入数据
        PtUser user = ptUserService.insertUser(ptUser);

        //获取token
        String token = getToken(user);
        //把toke放入cookie
        Cookie cookie = new Cookie(ConstantUtil.COOKIE_TOKEN,token);
        response.addCookie(cookie);

        return ResponseUtils.packaging("00","注册成功！",null);
    }


    /**
     * 获取token并放入redis
     * @param ptUser
     * @return
     */
    private String getToken(PtUser ptUser){//获取令牌
        TokenUtils instance = TokenUtils.getInstance();
        String token = instance.makeToken(ptUser.getUserId().toString());

        try {
            //把token放入redis
            String userJson = new ObjectMapper().writeValueAsString(ptUser);
            //令牌有效时间为一天
            redisUtil.set(token,userJson,60*60*24);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return token;
    }
}
