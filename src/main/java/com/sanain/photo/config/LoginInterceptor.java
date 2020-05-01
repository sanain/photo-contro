package com.sanain.photo.config;

import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.util.ConstantUtil;
import com.sanain.photo.util.JsonUtils;
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

        PtUser ptUser = JsonUtils.toObject(o.toString(),PtUser.class);
        //判断是否有跨越权限的请求
        if(request.getRequestURL().indexOf("management") >=0 ){
            // 普通用户请求管理员页面
            if("1".equals(ptUser.getRole())){
                response.setStatus(402);
                return false;
            }
        }
//        else{
//            // 管理员请求用户页面
//            if("2".equals(ptUser.getRole())){
//                response.setStatus(401);
//                return false;
//            }
//        }
        return true;
    }
}
