package com.sanain.photo.controller;

import ch.qos.logback.core.util.ContextUtil;
import com.sanain.photo.pojo.PtDir;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.pojo.PtUserFile;
import com.sanain.photo.service.PtDirService;
import com.sanain.photo.service.PtUserFileService;
import com.sanain.photo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件夹controller
 * @Author sanain
 */
@RequestMapping("/dir")
@Controller
public class PtDirController {
    @Autowired
    private PtDirService ptDirService;
    @Autowired
    private PtUserFileService ptUserFileService;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 添加文件夹
     */
    @ResponseBody
    @RequestMapping("insertDir")
    public Map<String,Object> insertDir(HttpServletRequest request , PtDir ptDir){
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        PtUser ptUser = JsonUtils.toObject(o.toString(), PtUser.class);

        PtDir dir = ptDirService.insertDir(ptDir, ptUser);
        String status="00";
        String msg = "新建相册成功！";
        if(dir == null){
            msg = "新建相册失败";
            status = "01";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("dirInfo",dir);
        return ResponseUtils.packaging(status,msg,map);
    }

    /**
     * 修改文件夹
     */
    @ResponseBody
    @RequestMapping("updateDir")
    public Map<String,Object> updateDir(PtDir ptDir){
        PtDir dir = ptDirService.updateDir(ptDir);
        String status="00";
        String msg = "修改相册成功！";
        if(dir == null){
            msg = "修改相册失败";
            status = "01";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("dirInfo",dir);
        return ResponseUtils.packaging(status,msg,map);
    }

    /**
     * 根据条件查询所有文件夹
     */
    @ResponseBody
    @RequestMapping("/selectListByExample")
    public Map<String,Object> selectListByExample(HttpServletRequest request,PtDir ptDir){
        if(ptDir.getDirUserId() == null){
            String token = TokenUtils.getInstance().getToken(request);
            Object o = redisUtil.get(token);
            PtUser ptUser = JsonUtils.toObject(o.toString(), PtUser.class);
            ptDir.setDirUserId(ptUser.getUserId());
        }

        List<PtDir> ptDirs = ptDirService.selectList(ptDir);
        Map<String,Object> map = new HashMap<>();
        map.put("dirList",ptDirs);
        return ResponseUtils.packaging("00","查询成功",map);
    }


    /**
     * 根据id查询文件夹
     */
    @ResponseBody
    @RequestMapping("/selectById")
    public Map<String,Object> selectById(Integer dirId){

        PtDir ptDir = ptDirService.selectById(dirId);
        Map<String,Object> map = new HashMap<>();
        map.put("dirInfo",ptDir);
        return ResponseUtils.packaging("00","查询成功",map);
    }

    /**
     * 根据id删除
     */
    @ResponseBody
    @RequestMapping("/deleteDirById")
    public Map<String,Object> deleteDirById(Integer dirId){

        Map<String,String> map = ptDirService.deleteDir(dirId);

        return ResponseUtils.packaging("00",map.get("msg"),map);
    }

    /**
     * 查询是否已经存在相同的相册名
     * @param request
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/hasSameName")
    public Map<String,Object> hasSameName(HttpServletRequest request ,PtDir ptDir){
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);

        PtUser ptUser = JsonUtils.toObject(o.toString(), PtUser.class);
        boolean b = ptDirService.hasSameName(ptDir,ptUser.getUserId());

        String msg = b ? "该相册名已经被使用":"该相册名可以使用";
        Map<String,Object> map = new HashMap<>();
        map.put("result",b);

        return ResponseUtils.packaging("00",msg,map);
    }

    /**
     * 下载相册
     * @param dirId
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/downloadDir")
    public Map<String , Object> downloadDir(Integer dirId , HttpServletResponse response){
        if(dirId == null){
            return ResponseUtils.packaging("01","相册id不能为空",null);
        }

        PtDir ptDir = ptDirService.selectById(dirId);
        List<PtUserFile> ptUserFiles = ptUserFileService.selectAllByDirId(dirId);
        if(CollectionUtils.isEmpty(ptUserFiles)){
            return ResponseUtils.packaging("00","相册中没有图片",null);
        }

        //取出所有图片的路径
        List<String> list = new ArrayList<>();
        for(PtUserFile file : ptUserFiles){
            list.add(ConstantUtil.IMAGES+file.getFilePath()+file.getFileName());
        }
        FileUtils.downloadZipFiles(response,list,ptDir.getDirName());
        return ResponseUtils.packaging("00","下载成功",null);
    }
}
