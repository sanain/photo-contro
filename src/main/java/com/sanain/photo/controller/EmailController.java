package com.sanain.photo.controller;

import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.service.IMailService;
import com.sanain.photo.service.IMailServiceImpl;
import com.sanain.photo.service.PtUserService;
import com.sanain.photo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮箱的controller
 * @Author sanain
 */
@RequestMapping("/email")
@Controller
public class EmailController {

    @Autowired
    private IMailService iMailService;
    @Autowired
    private PtUserService ptUserService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     *  发送注册时邮箱验证码
     * @param email
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendRegisterCode")
    public Map<String,Object> sendRegisterCode(String email, HttpServletRequest request){
        if(StringUtils.isEmpty(email)){
            return ResponseUtils.packaging("01","邮箱不能为空！",null);
        }

        String code = CodeUtils.getCode();
        String msg = "您的验证码为：\n\n\n"+code+"\n\n\n"+"您的验证码10分钟内有效，为了保证您的帐户安全，请勿向任何人提供此验证码。";

        iMailService.sendSimpleMail(email,"注册验证码",msg);

        String sessionId = request.getSession().getId();
        //把验证码放入redis
        redisUtil.set(ConstantUtil.EMAIL_CODE+sessionId,code,10*60);
        return ResponseUtils.packaging("00","发送成功",null);
    }

    /**
     *  发送修改个人信息邮箱验证码
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendUpdateUserInfoCode")
    public Map<String,Object> sendUpdateUserInfoCode(HttpServletRequest request){
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        if(o == null){
            return ResponseUtils.packaging("01","登录信息错误！",null);
        }

        String code = CodeUtils.getCode();
        String msg = "您的验证码为：\n\n\n"+code+"\n\n\n"+"您的验证码10分钟内有效，为了保证您的帐户安全，请勿向任何人提供此验证码。";

        PtUser ptUser = JsonUtils.toObject(o.toString(),PtUser.class);
        iMailService.sendSimpleMail(ptUser.getUserEmail(),"修改密码验证码",msg);

        //把验证码放入redis
        redisUtil.set(ConstantUtil.UPDATE_USER_INFO_EMAIL_CODE+token,code,10*60);
        return ResponseUtils.packaging("00","发送验证码成功",null);
    }


    /**
     * 查询是否已经存在这个邮箱
     * @param email
     * @return
     */
    @ResponseBody
    @RequestMapping("/hasSameEmail")
    public Map<String,Object> hasSameEmail(String email){

        if(StringUtils.isEmpty(email)){
            return ResponseUtils.packaging("01","邮箱为空！",null);
        }

        //调用service方法查询是否已经存在这样邮箱
        //true 为已经存在 false 未存在
        boolean b = ptUserService.hasSameEmail(email);
        Map<String,Boolean> map = new HashMap<>();
        map.put("result",b);

        return ResponseUtils.packaging("00","查询成功",map);
    }
}
