package com.sanain.photo.util;

import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 生成token(令牌)的工具类
 * @Author sanain
 */
public class TokenUtils {
    private TokenUtils(){};
    private static TokenUtils instance = new TokenUtils();

    public static TokenUtils getInstance() {
        if(instance == null){
            instance = new TokenUtils();
        }
        return instance;
    }

    /**
     * 生成Token
     * @return
     */
    public String makeToken(String info) {
        String token = System.currentTimeMillis() + info;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 从请求中获取token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length == 0){
            return "";
        }

        for(Cookie cookie : cookies){
            if(ConstantUtil.COOKIE_TOKEN.equals(cookie.getName())){
                return cookie.getValue();
            }
        }

        return "";
    }
}
