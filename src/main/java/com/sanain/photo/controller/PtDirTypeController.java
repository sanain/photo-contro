package com.sanain.photo.controller;

import com.sanain.photo.pojo.PtDir;
import com.sanain.photo.pojo.PtDirType;
import com.sanain.photo.service.PtDirTypeService;
import com.sanain.photo.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件夹分类controller
 * @Author sanain
 */
@Controller
@RequestMapping("/dirType")
public class PtDirTypeController {
    @Autowired
    private PtDirTypeService ptDirTypeService;

    /**
     * 查询所有
     * @return
     */
    @GetMapping("selectAll")
    @ResponseBody
    public Map<String,Object> selectAll(){
        List<PtDirType> ptDirTypes = ptDirTypeService.selectAllType();
        Map<String,Object> map = new HashMap<>();
        map.put("typeList",ptDirTypes);
        return ResponseUtils.packaging("00","查询成功！",map);
    }

}
