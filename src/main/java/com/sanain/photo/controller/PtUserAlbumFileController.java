package com.sanain.photo.controller;

import com.sanain.photo.pojo.*;
import com.sanain.photo.service.PtUserAlbumFileService;
import com.sanain.photo.service.PtUserAlbumService;
import com.sanain.photo.util.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户动态相册文件的controller
 * @Author sanain
 */
@RestController
@RequestMapping("/userAlbumFile")
public class PtUserAlbumFileController {
    @Autowired
    private PtUserAlbumFileService ptUserAlbumFileService;
    @Autowired
    private PtUserAlbumService ptUserAlbumService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 上传新的图片
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadFiles")
    public Map<String , Object> uploadFiles(PtUserAlbum ptUserAlbum , MultipartFile file , HttpServletRequest request){
        if(ptUserAlbum.getId() == null){
            return ResponseUtils.packaging("01","相册id不能为空",null);
        }
        if(file == null){
            return ResponseUtils.packaging("01","上传的文件错误",null);
        }

        //拼凑图片保存的路径
        String path = ConstantUtil.TEMP_IMG;
        //把图片保存到本地磁盘
        String fileName = FileUtils.saveOneFile(file, path);


        //把图片信息保存到数据库,并返回刚刚插入的图片对象
        PtUserAlbumFile userFile = ptUserAlbumFileService.insert(fileName, ptUserAlbum);

        //查找出来相册中的所有的图片
        List<PtUserAlbumFile> fileList = ptUserAlbumFileService.selectByUserAlbumId(ptUserAlbum.getId());

        //只有当图片总数等于1，说明原来相册没图片，才需要更新封面
        if(fileList.size() == 1) {
            ptUserAlbum.setCoverPaths(fileList.get(0).getFilePath());
            ptUserAlbumService.updateAlbum(ptUserAlbum);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("fileInfo",userFile);
        return ResponseUtils.packaging("00","上传成功",map);
    }

    /**
     * 上传新的图片
     * @return
     */
    @ResponseBody
    @GetMapping("/getAllFileByAlbumId")
    public Map<String , Object> getAllFileByAlbumId(Integer albumId){
        if(albumId == null){
            return ResponseUtils.packaging("01","相册id不能为空",null);
        }

        //把图片信息保存到数据库,并返回刚刚插入的图片对象
        List<PtUserAlbumFile> fileList = ptUserAlbumFileService.selectByUserAlbumId(albumId);
        if(!CollectionUtils.isEmpty(fileList)){
            for(PtUserAlbumFile f : fileList){
                f.setFilePath(ConstantUtil.PRO_PATH+ConstantUtil.PATH_TEMP_IMG+f.getFilePath());
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("fileInfo",fileList);
        return ResponseUtils.packaging("00","查询成功",map);
    }
    /**
     * 删除图片
     * @param fileId
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteFile")
    public Map<String,Object> deleteFile(Integer fileId){
        if(fileId == null){
            return ResponseUtils.packaging("01","图片id不能为空",null);
        }

        //查出需要删除的图片对象
        PtUserAlbumFile albumFile = ptUserAlbumFileService.selectById(fileId);

        //删除本地文件
        String path = ConstantUtil.TEMP_IMG+albumFile.getFilePath();
        File file = new File(path);
        file.delete();

        //删除图片对象
        boolean b = ptUserAlbumFileService.deleteById(fileId);

        //查出图片所在的相册对象，因为可能需要更新封面
        PtUserAlbum album = ptUserAlbumService.selectById(albumFile.getUserAlbumId());
        //查找出来相册中的所有的图片
        List<PtUserAlbumFile> fileList = ptUserAlbumFileService.selectByUserAlbumId(album.getId());

//        如果还有图片就更新封面
        if(!CollectionUtils.isEmpty(fileList)){
            album.setCoverPaths(fileList.get(0).getFilePath());
            ptUserAlbumService.updateAlbum(album);
        }


        String msg = b?"删除成功":"删除失败";
        Map<String,Object> map = new HashMap<>();
        map.put("result",b);

        return ResponseUtils.packaging("00",msg,map);
    }

    /**
     * 下载文件
     * @param response
     * @param
     */
    @ResponseBody
    @GetMapping("/downloadFile")
    public void downloadTemplate(HttpServletResponse response, Integer fileId){
        // 下载路径
        PtUserAlbumFile file = ptUserAlbumFileService.selectById(fileId);
        String path = ConstantUtil.TEMP_IMG+file.getFilePath();
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
