package com.sanain.photo.controller;

import com.github.pagehelper.PageInfo;
import com.sanain.photo.pojo.PtAlbumTemp;
import com.sanain.photo.service.PtAlbumTempService;
import com.sanain.photo.util.ConstantUtil;
import com.sanain.photo.util.FileUtils;
import com.sanain.photo.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 *动态相册模板的controller
 * @Author sanain
 */
@RestController
@RequestMapping("/albumTemp")
public class PtAlbumTempController {
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
     * 查询所有的动态相册
     * @return
     */
    @GetMapping("/getAllAlbum")
    public Map<String,Object> getAllAlbum() {
        List<PtAlbumTemp> list = ptAlbumTempService.getAllAlbum();

        Map<String, Object> map = new HashMap<>();
        map.put("listInfo", list);

        return ResponseUtils.packaging("00", "查询成功", map);
    }


    /**
     * 查看动态相册模板的演示
     * @param tempId
     * @return
     */
    @GetMapping("/show")
    public Map<String,Object> show(Integer tempId){
        if(tempId == null){
            return ResponseUtils.packaging("01","id不能为空",null);
        }
        PtAlbumTemp temp = ptAlbumTempService.selectById(tempId);

//        拼凑封面路径
        temp.setCoverPaths(ConstantUtil.PRO_PATH+ConstantUtil.PATH_TEMP_IMG+temp.getCoverPaths());
//        拼凑演示图片路径
        if(!StringUtils.isEmpty(temp.getPhotoPaths())){
            StringBuffer strBuf = new StringBuffer();
            for(String str : temp.getPhotoPaths().split(";")){
                if(StringUtils.isEmpty(str)){
                    continue;
                }
                strBuf.append(ConstantUtil.PRO_PATH+ConstantUtil.PATH_TEMP_IMG+str+";");
            }
            temp.setPhotoPaths(strBuf.toString());
        }
        // 拼凑js路径
        if(!StringUtils.isEmpty(temp.getJsPaths())){
            StringBuffer strBuf = new StringBuffer();
            for(String str : temp.getJsPaths().split(";")){
                if(StringUtils.isEmpty(str)){
                    continue;
                }
                strBuf.append(ConstantUtil.PRO_PATH+"/js/"+str+";");
            }
            temp.setJsPaths(strBuf.toString());
        }
        // 拼凑css路径
        if(!StringUtils.isEmpty(temp.getCssPaths())){
            StringBuffer strBuf = new StringBuffer();
            for(String str : temp.getCssPaths().split(";")){
                if(StringUtils.isEmpty(str)){
                    continue;
                }
                strBuf.append(ConstantUtil.PRO_PATH+"/css/"+str+";");
            }
            temp.setCssPaths(strBuf.toString());
        }

        Map<String,Object> map = new HashMap<>();
        map.put("tempInfo",temp);

        return ResponseUtils.packaging("00","查询成功",map);
    }
}
