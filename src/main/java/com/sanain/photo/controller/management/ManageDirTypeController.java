package com.sanain.photo.controller.management;

import com.github.pagehelper.PageInfo;
import com.sanain.photo.pojo.PtDir;
import com.sanain.photo.pojo.PtDirType;
import com.sanain.photo.service.PtDirTypeService;
import com.sanain.photo.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员相册分类controller
 * @Author sanain
 */
@Controller
@RequestMapping("/management/dirType")
public class ManageDirTypeController {
    @Autowired
    private PtDirTypeService ptDirTypeService;


    /**
     * 根据条件分页查找
     * @return
     */
    @PostMapping("/getListByExample")
    @ResponseBody
    public Map<String,Object> getListByExample(PtDirType ptDirType,
                                               @RequestParam(defaultValue = "1") Integer pageNum ,
                                               @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<PtDirType> pageInfo = ptDirTypeService.getListByExample(ptDirType,pageNum,pageSize);
        Map<String,Object> map = new HashMap<>();
        map.put("pageInfo",pageInfo);
        return ResponseUtils.packaging("00","查询成功！",map);
    }


    /**
     * 新增相册分类
     * @return
     */
    @PostMapping("/insertDitType")
    @ResponseBody
    public Map<String,Object> insertDitType(PtDirType ptDirType){
        ptDirType = ptDirTypeService.insertType(ptDirType);

        String status = ptDirType == null ? "01":"00";
        String msg = ptDirType == null ? "插入失败":"插入成功";
        Map<String,Object> map = new HashMap<>();
        map.put("typeInfo",ptDirType);
        return ResponseUtils.packaging(status,msg,map);
    }


    /**
     * 修改相册分类
     * @return
     */
    @PostMapping("/updateDitType")
    @ResponseBody
    public Map<String,Object> updateDitType(PtDirType ptDirType){

        ptDirType = ptDirTypeService.updateType(ptDirType);

        String status = ptDirType == null ? "01":"00";
        String msg = ptDirType == null ? "修改失败":"修改成功";
        Map<String,Object> map = new HashMap<>();
        map.put("typeInfo",ptDirType);
        return ResponseUtils.packaging(status,msg,map);
    }

    /**
     * 删除相册分类
     * @return
     */
    @GetMapping("/deleteDitType")
    @ResponseBody
    public Map<String,Object> deleteDitType(Integer typeId){

        Map<String,Object> map = ptDirTypeService.deleteType(typeId);

        String status = (boolean)map.get("result") ? "00":"01";
        String msg = (String)map.get("msg");

        return ResponseUtils.packaging(status,msg,null);
    }
}

