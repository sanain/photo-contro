package com.sanain.photo.controller.management;

import com.github.pagehelper.PageInfo;
import com.sanain.photo.controller.EmailController;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.service.IMailService;
import com.sanain.photo.service.PtUserService;
import com.sanain.photo.util.JsonUtils;
import com.sanain.photo.util.RedisUtil;
import com.sanain.photo.util.ResponseUtils;
import com.sanain.photo.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员用户的controller
 * @Author snain
 */
@RequestMapping("/management/user")
@Controller()
public class ManagementUserController {
    @Autowired
    private PtUserService ptUserService;
    @Autowired
    private IMailService iMailService;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 根据条件分页查询
     * @param ptUser
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("getListByExample")
    @ResponseBody
    public Map<String,Object> getListByExample(PtUser ptUser , HttpServletRequest request,
                                               @RequestParam(defaultValue = "1") Integer pageNum ,
                                               @RequestParam(defaultValue = "10") Integer pageSize){
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        PtUser currentUser = JsonUtils.toObject(o.toString(),ptUser.getClass());

//        查询数据
        PageInfo<PtUser> pageInfo = ptUserService.getListByExample(ptUser,currentUser, pageNum, pageSize);

        Map<String,Object> map = new HashMap<>();
        map.put("pageInfo",pageInfo);

        return ResponseUtils.packaging("00","查询成功",map);
    }

    /**
     * 禁用/解禁用户
     * @param ptUser
     * @return
     */
    @RequestMapping("/disableUser")
    @ResponseBody
    public Map<String,Object> disableUser(PtUser ptUser){
        if(ptUser.getUserId() == null){
            return ResponseUtils.packaging("01","用户id不能为空",null);
        }

        ptUser = ptUserService.updateUser(ptUser);

        String content = "你的电子相册系统账号已被管理员禁用,如有疑问请联系管理员";
        String title = "账号禁用通知";
        if(ptUser.getIsUse() == 0){
            content = "你的电子相册系统账号已被管理员解禁,如有疑问请联系管理员";
            title = "账号解禁通知";
        }
//       发送通知
        iMailService.sendSimpleMail(ptUser.getUserEmail(),title,content);

        Map<String,Object> map = new HashMap<>();
        map.put("userInfo",ptUser);

        return ResponseUtils.packaging("00","操作成功",map);
    }
}
