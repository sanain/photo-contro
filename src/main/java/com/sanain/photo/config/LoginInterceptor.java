package com.sanain.photo.config;

import com.sanain.photo.util.ConstantUtil;
import com.sanain.photo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

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
        String token = request.getHeader("Authorization");

        if(StringUtils.isEmpty(token)){
            response.setStatus(401);
            return false;
        }

        //从redis查看令牌是否存在
        Object o = redisUtil.get(ConstantUtil.TOKEN + token);
        if(o == null){
            return false;
        }

        return true;
    }
}
