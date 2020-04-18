package com.sanain.photo.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.service.PtUserService;
import com.sanain.photo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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
    @RequestMapping("/login")
    public Map<String , Object> login( HttpServletRequest request, HttpServletResponse response ,PtUser ptUser, String code, String sessionId){
        sessionId = request.getSession().getId();
        if(StringUtils.isEmpty(sessionId)){
            return ResponseUtils.packaging("01","sessionId 为空！",null);
        }
        if(StringUtils.isEmpty(code)){
            return ResponseUtils.packaging("01","验证码不能 为空！",null);
        }
        /*把前端输入的验证码转成大写*/
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

        //生成cookie和放入redis、session
        makeToke(user,request,response);

//        redisUtil.del(token);
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
            System.out.println(randCode.toUpperCase());
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
        /*获取sessionId*/
        String id = request.getSession().getId();
        /*从redis中获取验证码*/
        Object o = redisUtil.get(ConstantUtil.EMAIL_CODE + id+ptUser.getUserEmail());
        /*比较验证码的正确性*/
        if(o == null || !code.equals(o.toString())){
            return ResponseUtils.packaging("01","验证码错误！",null);
        }
        //调用service层插入数据
        PtUser user = ptUserService.insertUser(ptUser);

        //获取token、并放入cookie、redis
        makeToke(user,request,response);

        return ResponseUtils.packaging("00","注册成功！",null);
    }


    /**
     * 生成token并放入redis、cookie
     * @param ptUser
     * @return
     */

    private String makeToke(PtUser ptUser,HttpServletRequest request , HttpServletResponse response){//获取令牌
        TokenUtils instance = TokenUtils.getInstance();
        String token = instance.makeToken(ptUser.getUserId().toString());

        //把token放入redis
        String userJson = JsonUtils.toJson(ptUser);
        //令牌有效时间为一天
        redisUtil.set(token,userJson,60*60*24);

        //把令牌放在cookie里面
        Cookie cookie = new Cookie(ConstantUtil.COOKIE_TOKEN,token);
        cookie.setPath("/");
        response.addCookie(cookie);

        return token;
    }

    /**
     * 获取当前用户的信息
     */
    @ResponseBody
    @RequestMapping("/getUserInfo")
    public Map<String , Object> getUserInfo(HttpServletRequest request){
        TokenUtils tokenUtils = TokenUtils.getInstance();
        String token = tokenUtils.getToken(request);

        Object o = redisUtil.get(token);

        Map<String,Object> map = new HashMap<>();
        if(o != null){
            PtUser user = JsonUtils.toObject(o.toString(), PtUser.class);
            map.put("isLogin",true);
            map.put("userPath",ConstantUtil.PRO_PATH+ConstantUtil.PATH_HEAD_PORTRAIT+user.getPhotoPath());
            map.put("userId", user.getUserId());
        }else{
            map.put("isLogin",false);
        }

        return ResponseUtils.packaging("00","查询成功",map);
    }

    /**
     * 获取当前用户所有的信息
     */
    @ResponseBody
    @RequestMapping("/getUserAllInfo")
    public Map<String , Object> getAllUserInfo(HttpServletRequest request){
        TokenUtils tokenUtils = TokenUtils.getInstance();
        String token = tokenUtils.getToken(request);

        Object o = redisUtil.get(token);

        PtUser user = JsonUtils.toObject(o.toString(), PtUser.class);
        user.setUserPassword("");
        user.setPhotoPath(ConstantUtil.PRO_PATH+ConstantUtil.PATH_HEAD_PORTRAIT+user.getPhotoPath());
        Map<String , Object> map = new HashMap<>();
        map.put("userInfo",user);
        return ResponseUtils.packaging("00","查询成功",map);
    }

    /**
     * 根据邮箱搜索，但是不包括自己
     */
    @ResponseBody
    @RequestMapping("/getUserByEmail")
    public Map<String , Object> getUserByEmail(String email,HttpServletRequest request){
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        PtUser ptUser = JsonUtils.toObject(o.toString(),PtUser.class);

        Map<String , Object> map = new HashMap<>();
        /*搜索的是自己的邮箱*/
        if(ptUser.getUserEmail().equals(email)){
            map.put("userInfo",new ArrayList<PtUser>());
            return ResponseUtils.packaging("00","邮箱为用户本身",map);
        }

        List<PtUser> ptUsers = ptUserService.selectByEmail(email);
        String msg = "查询成功";
        if(CollectionUtils.isEmpty(ptUsers)){
            msg="没有存在该用户！";
        }
        map.put("userInfo",ptUsers);
        return ResponseUtils.packaging("00",msg,map);
    }

    /**
     * 更新用户信息
     * @param user
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public Map<String , Object> updateUser(PtUser user ,HttpServletRequest request){
        Map<String , Object> map = new HashMap<>();
        /*从cookie中获取token*/
        String token = TokenUtils.getInstance().getToken(request);
        /*从redis中取出当前用户*/
        Object o = redisUtil.get(token);
        /*把取出当前用户的json转成PtUser对象*/
        PtUser ptUser = JsonUtils.toObject(o.toString(), PtUser.class);
        //删除缓存
        redisUtil.del(token);
        user.setUserId(ptUser.getUserId());
        /*根据数据库*/
        ptUser = ptUserService.updateUser(user);
        if(ptUser == null){
            return ResponseUtils.packaging("01","更新用户信息错误！",null);
        }
        /*再次删除缓存*/
        redisUtil.del(token);
        //更新信息之后写入缓存
        redisUtil.set(token,JsonUtils.toJson(ptUser),60*60*24);

        map.put("userInfo",ptUser);
        map.put("result",true);
        return ResponseUtils.packaging("00","更新成功！",map);

    }


    /**
     * 退出登录
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/loginOut")
    public Map<String,Object> loginOut(HttpServletRequest request){
        String token = TokenUtils.getInstance().getToken(request);
        redisUtil.del(token);

        Map<String,Object> map = new HashMap<>();
        map.put("result",true);

        return ResponseUtils.packaging("00","退出登录成功!",map);
    }
    @ResponseBody
    @RequestMapping("testCookie")
    public String testCookie(HttpServletResponse response){
        int i = new Random().nextInt(10);
        Cookie cookie = new Cookie("1",i+"");
        response.addCookie(cookie);

        return "";
    }


}
