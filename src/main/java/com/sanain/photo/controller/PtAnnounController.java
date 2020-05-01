package com.sanain.photo.controller;

import com.github.pagehelper.PageInfo;
import com.sanain.photo.pojo.PtAnnoun;
import com.sanain.photo.service.PtAnnounService;
import com.sanain.photo.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 公告的controller
 * @Author sanain
 */
@RequestMapping("/announ")
@Controller()
public class PtAnnounController {
    @Autowired
    private PtAnnounService ptAnnounService;

    /**
     * 查询最新的十条公告
     * @return
     */
    @RequestMapping("/getFiftyLatest")
    @ResponseBody
    public Map<String,Object> getFiftyLatest(){
//        查询数据
        PageInfo<PtAnnoun> pageInfo = ptAnnounService.getFiftyLatest();

        Map<String,Object> map = new HashMap<>();
        map.put("pageInfo",pageInfo);

        return ResponseUtils.packaging("00","查询成功",map);
    }


}
