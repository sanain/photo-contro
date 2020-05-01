package com.sanain.photo.controller.management;

import com.github.pagehelper.PageInfo;
import com.sanain.photo.pojo.PtDirType;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.pojo.PtUserDynamic;
import com.sanain.photo.service.IMailService;
import com.sanain.photo.service.PtDirTypeService;
import com.sanain.photo.service.PtUserDynamicService;
import com.sanain.photo.service.PtUserService;
import com.sanain.photo.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员动态controller
 * @Author sanain
 */
@Controller
@RequestMapping("/management/dynamic")
public class ManageDynamicController {
    @Autowired
    private PtUserDynamicService ptUserDynamicService;
    @Autowired
    private IMailService iMailService;
    @Autowired
    private PtUserService ptUserService;

    /**
     * 根据条件分页查找
     * @return
     */
    @PostMapping("/getListByExample")
    @ResponseBody
    public Map<String,Object> getListByExample(String userName ,String createTime,
                                               @RequestParam(defaultValue = "1") Integer pageNum ,
                                               @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<PtUserDynamic> pageInfo = ptUserDynamicService.getListByExample(userName,createTime,pageNum,pageSize);
        Map<String,Object> map = new HashMap<>();
        map.put("pageInfo",pageInfo);
        return ResponseUtils.packaging("00","查询成功！",map);
    }


    /**
     * 删除相册分类
     * @return
     */
    @PostMapping("/deleteDynamic")
    @ResponseBody
    public Map<String,Object> deleteDitType(Integer dynamicId,String reason){
        if(dynamicId == null){
            return ResponseUtils.packaging("01","动态id不能为空！",null);
        }

//        查出要被删除那条动态
        PtUserDynamic dynamic = ptUserDynamicService.getById(dynamicId);
//        查出动态对应的用户
        PtUser ptUser = ptUserService.selectById(dynamic.getUserId());

        boolean b = ptUserDynamicService.deleteDynamic(dynamicId);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = format.format(dynamic.getCreateTime());
        String content = "你于"+time+"发的动态，已被管理员删除，删除理由为"+reason+",如有疑问请联系管理员";
        String title = "动态删除";

//       发送通知
        iMailService.sendSimpleMail(ptUser.getUserEmail(),title,content);

        String status = b ? "00":"01";
        String msg = b ? "删除成功":"删除失败";

        return ResponseUtils.packaging(status,msg,null);
    }
}

