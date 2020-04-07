package com.sanain.photo.config;

import com.sanain.photo.util.ConstantUtil;
import com.sanain.photo.util.RedisUtil;
import com.sanain.photo.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @Author sinain
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 验证token（令牌是否有效）
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length == 0){
            response.setStatus(403);
            return false;
        }

        int count = 0;
        String token = null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(ConstantUtil.COOKIE_TOKEN)){
                token = cookie.getValue();

                Object o = redisUtil.get(token);
                if(o != null){
                    break;
                }
            }
            count++;
        }

        if(count == cookies.length){
            response.setStatus(403);
            return false;
        }

        //从redis查看令牌是否存在
        Object o = redisUtil.get(token);
        if(o == null){
            response.setStatus(403);
            return false;
        }

        return true;
    }
}
