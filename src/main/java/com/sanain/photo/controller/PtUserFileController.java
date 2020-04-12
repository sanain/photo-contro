package com.sanain.photo.controller;

import com.sanain.photo.pojo.PtDir;
import com.sanain.photo.pojo.PtUser;
import com.sanain.photo.pojo.PtUserFile;
import com.sanain.photo.service.PtDirService;
import com.sanain.photo.service.PtUserFileService;
import com.sanain.photo.util.*;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * 用户上传的文件controller
 * @Author sanain
 */
@Controller
@RequestMapping("/userFile")
public class PtUserFileController {
    @Autowired
    private PtUserFileService ptUserFileService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PtDirService ptDirService;
    @Resource
    private ResourceLoader resourceLoader;
    /**
     * 根据相册id查询所有的图片
     * @param dirId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllByDirId")
    public Map<String,Object> getAllByDirId(Integer dirId){
        if(dirId == null){
            return ResponseUtils.packaging("01","相册id不能为空",null);
        }

        List<PtUserFile> ptUserFiles = ptUserFileService.selectAllByDirId(dirId);

        //把数据库中存储的路径替换成通过网址访问的路径
        if(!CollectionUtils.isEmpty(ptUserFiles)){
            for(PtUserFile userFile : ptUserFiles){
                userFile.setFilePath(ConstantUtil.PRO_PATH+ConstantUtil.PATH_IMAGES+userFile.getFilePath()+userFile.getFileName());
            }
        }

        Map<String,Object> map = new HashMap<>();
        map.put("fileList",ptUserFiles);

        return ResponseUtils.packaging("00","查询成功",map);
    }


    /**
     * 上传新的图片
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadFiles")
    public Map<String , Object> uploadFiles(PtDir ptDir , MultipartFile file , HttpServletRequest request){
        if(ptDir.getDirId() == null){
            return ResponseUtils.packaging("01","相册id不能为空",null);
        }
        if(file == null){
            return ResponseUtils.packaging("01","上传的文件错误",null);
        }

        //获取当前用户
        String token = TokenUtils.getInstance().getToken(request);
        Object o = redisUtil.get(token);
        PtUser ptUser = JsonUtils.toObject(o.toString(), PtUser.class);

        //拼凑图片保存的路径
        String path = ConstantUtil.IMAGES+ptUser.getUserId()+"/"+ptDir.getDirId()+"/";
        //把图片保存到本地磁盘
        String fileName = FileUtils.saveOneFile(file, path);

        //把图片信息保存到数据库,并返回刚刚插入的图片对象
        PtUserFile userFile = ptUserFileService.insert(fileName, ptDir, ptUser);

        //查找出来相册中的所有的图片
        List<PtUserFile> ptUserFiles = ptUserFileService.selectAllByDirId(ptDir.getDirId());

        //只有当图片总数等于1，说明原来相册没图片，才需要更新封面
        if(ptUserFiles.size() == 1) {
            updateDirImg(ptDir,ptUserFiles.get(0));
        }

        Map<String,Object> map = new HashMap<>();
        map.put("fileInfo",userFile);
        return ResponseUtils.packaging("00","上传成功",map);
    }

    /**
     * 更新相册封面
     * @param ptDir
     * @param ptUserFile
     */
    private void updateDirImg(PtDir ptDir,PtUserFile ptUserFile){

        //把相册中第一张图片复制到相册封面的文件夹
        String source = ConstantUtil.IMAGES + ptUserFile.getFilePath() + ptUserFile.getFileName();
        String target = ConstantUtil.DIR_IMG + ptUserFile.getFileName();
        FileUtils.copyFile(source, target);

        //更新相册的封面
        ptDir.setDirImg(ptUserFile.getFileName());
        ptDirService.updateDir(ptDir);
    }

    /**
     * 删除图片
     * @param userFileId
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteFile")
    public Map<String,Object> deleteFile(Integer userFileId){
        if(userFileId == null){
            return ResponseUtils.packaging("01","图片id不能为空",null);
        }

        //查出需要删除的图片对象
        PtUserFile userFile = ptUserFileService.selectByUserFileId(userFileId);

        //删除本地文件
        String path = ConstantUtil.IMAGES+userFile.getFilePath()+userFile.getFileName();
        File file = new File(path);
        file.delete();

        //删除图片对象
        boolean b = ptUserFileService.deleteById(userFileId);

        //查出图片所在的相册对象，因为可能需要更新封面
        PtDir ptDir = ptDirService.selectById(userFile.getDirId());
        //查找出来相册中的所有的图片
        List<PtUserFile> ptUserFiles = ptUserFileService.selectAllByDirId(ptDir.getDirId());
        //更新相册封面
        updateDirImg(ptDir,ptUserFiles.get(0));

        String msg = b?"删除成功":"删除失败";
        Map<String,Object> map = new HashMap<>();
        map.put("result",b);

        return ResponseUtils.packaging("00",msg,map);
    }

    /**
     * 下载文件
     * @param response
     * @param userFileId
     */
    @ResponseBody
    @GetMapping("/downloadFile")
    public void downloadTemplate(HttpServletResponse response,Integer userFileId){
        // 下载路径
        PtUserFile ptUserFile = ptUserFileService.selectByUserFileId(userFileId);
        String path = ConstantUtil.IMAGES+ptUserFile.getFilePath()+ptUserFile.getFileName();
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
            response.setContentType("application/octet-stream");
            response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.addHeader("Pragma", "no-cache");

            inputStream = new FileInputStream(new File(path));
            servletOutputStream = response.getOutputStream();
            IOUtils.copy(inputStream, servletOutputStream);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (servletOutputStream != null) {
                    servletOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}

