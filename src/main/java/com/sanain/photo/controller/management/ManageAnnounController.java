package com.sanain.photo.controller.management;

import com.github.pagehelper.PageInfo;
import com.sanain.photo.controller.EmailController;
import com.sanain.photo.pojo.PtAnnoun;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.service.IMailService;
import com.sanain.photo.service.PtAnnounService;
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
 * 管理员公告的controller
 * @Author snain
 */
@RequestMapping("/management/announ")
@Controller()
public class ManageAnnounController{
    @Autowired
    private PtAnnounService ptAnnounService;

    /**
     * 根据条件分页查询
     * @param
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("getListByExample")
    @ResponseBody
    public Map<String,Object> getListByExample(PtAnnoun ptAnnoun ,
                                               @RequestParam(defaultValue = "1") Integer pageNum ,
                                               @RequestParam(defaultValue = "10") Integer pageSize){
//        查询数据
        PageInfo<PtAnnoun> pageInfo = ptAnnounService.getListByExample(ptAnnoun, pageNum, pageSize);

        Map<String,Object> map = new HashMap<>();
        map.put("pageInfo",pageInfo);

        return ResponseUtils.packaging("00","查询成功",map);
    }

    /**
     * 删除广告
     * @param
     * @return
     */
    @RequestMapping("/deleteAnnoun")
    @ResponseBody
    public Map<String,Object> deleteAnnoun(Integer id){
        if(id == null){
            return ResponseUtils.packaging("01","id不能为空",null);
        }

        boolean b = ptAnnounService.deleteAnnoun(id);

        String msg = b ? "删除成功":"删除失败";
        String status = b ? "00":"01";

        return ResponseUtils.packaging(status,msg,null);
    }

    /**
     * 新增广告
     * @param
     * @return
     */
    @RequestMapping("/insertAnnoun")
    @ResponseBody
    public Map<String,Object> insertAnnoun(PtAnnoun ptAnnoun){

        boolean b = ptAnnounService.insertAnnoun(ptAnnoun);

        String msg = b ? "删除成功":"删除失败";
        String status = b ? "00":"01";

        return ResponseUtils.packaging(status,msg,null);
    }
}
