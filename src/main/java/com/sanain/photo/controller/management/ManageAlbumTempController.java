package com.sanain.photo.controller.management;

import com.github.pagehelper.PageInfo;
import com.sanain.photo.pojo.PtAlbumTemp;
import com.sanain.photo.service.PtAlbumTempService;
import com.sanain.photo.util.ConstantUtil;
import com.sanain.photo.util.FileUtils;
import com.sanain.photo.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *管理员动态相册模板的controller
 * @Author sanain
 */
@RestController
@RequestMapping("/management/albumTemp")
public class ManageAlbumTempController {
    @Autowired
    private PtAlbumTempService ptAlbumTempService;

    /**
     * 分页条件查询
     * @param temp
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/selectListByExample")
    public Map<String,Object> selectListByExample(PtAlbumTemp temp,
                                                  @RequestParam(defaultValue = "1") Integer pageNum ,
                                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<PtAlbumTemp> pageInfo = ptAlbumTempService.getListByExmaple(temp, pageNum, pageSize);

        Map<String, Object> map = new HashMap<>();
        map.put("pageInfo", pageInfo);

        return ResponseUtils.packaging("00", "查询成功", map);
    }

    /**
     * 上传演示图片
     * @param file
     * @param
     * @return
     */
    @RequestMapping("/uploadTempImg")
    public Map<String , Object> uploadTempImg(MultipartFile file ){
        if(file == null){
            return ResponseUtils.packaging("01","上传的文件错误",null);
        }

        //拼凑图片保存的路径
        String path = ConstantUtil.TEMP_IMG;
        //把图片保存到本地磁盘
        String fileName = FileUtils.saveOneFile(file, path);

        Map<String,Object> map = new HashMap<>();
        map.put("fileName",fileName);
        return ResponseUtils.packaging("00","上传成功",map);
    }


    /**
     * 删除演示图片
     * @return
     */
    @RequestMapping("/deleteTempImg")
    public Map<String , Object> deleteTempImg(String fileName){
        if(StringUtils.isEmpty(fileName)){
            return ResponseUtils.packaging("01","删除的图片名不能为空",null);
        }

        Map<String,Object> map = new HashMap<>();
        //拼凑图片保存的路径
        String path = ConstantUtil.TEMP_IMG+fileName;
        File file = new File(path);
        if(file.exists()){
            file.delete();
            map.put("fileName",fileName);
            return ResponseUtils.packaging("00","删除成功",map);
        }


        map.put("fileName",fileName);
        return ResponseUtils.packaging("01","图片不存在",map);
    }


    /**
     * 上传模板文件（css、js）
     * @param file
     * @param
     * @return
     */
    @RequestMapping("/uploadTempFile")
    public Map<String , Object> uploadTempFile(MultipartFile file , String type){
        if(file == null){
            return ResponseUtils.packaging("01","上传的文件错误",null);
        }

        //拼凑文件保存的路径
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/"+type+"/";

        //把图片保存到本地磁盘
        String fileName = FileUtils.saveOneFile(file, path);

        Map<String,Object> map = new HashMap<>();
        map.put("fileName",fileName);
        return ResponseUtils.packaging("00","上传成功",map);
    }


    /**
     * 删除模板文件（css、js）
     * @return
     */
    @RequestMapping("/deleteTempFile")
    public Map<String , Object> deleteTempFile(String fileName,String type){
        if(StringUtils.isEmpty(fileName)){
            return ResponseUtils.packaging("01","删除的文件名不能为空",null);
        }

        Map<String,Object> map = new HashMap<>();
        //拼凑文件保存的路径
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/"+type+"/";
        path += fileName;
        File file = new File(path);
        if(file.exists()){
            file.delete();
            map.put("fileName",fileName);
            return ResponseUtils.packaging("00","删除成功",map);
        }


        map.put("fileName",fileName);
        return ResponseUtils.packaging("01","文件不存在",map);
    }

    /**
     * 插入模板
     * @return
     */
    @PostMapping("/insertTemp")
    public Map<String,Object> insertTemp(PtAlbumTemp temp){
        boolean b = ptAlbumTempService.insertTemp(temp);

        return ResponseUtils.packaging("00","新增成功",null);
    }


    /**
     * 删除模板
     * @return
     */
    @GetMapping("/deleteTemp")
    public Map<String,Object> deleteTemp(Integer id){
        Map<String, Object> map = ptAlbumTempService.deleteTemp(id);

        String status = (boolean)map.get("result") ? "00":"01";

        return ResponseUtils.packaging(status,map.get("msg").toString(),null);
    }
}
