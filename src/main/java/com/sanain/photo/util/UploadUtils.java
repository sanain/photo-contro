package com.sanain.photo.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传工具类
 * @Author sanain
 */
public class UploadUtils {
    public static String saveOneFile(MultipartFile multipartFile , String path){

        if(multipartFile == null){
            return null;
        }

        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        String random = UUID.randomUUID().toString();

        int i = multipartFile.getOriginalFilename().lastIndexOf(".");
        String suffix = multipartFile.getOriginalFilename().substring(i);

        File newFile = new File(path+random+suffix);
        try {
            multipartFile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return random+suffix;
    }
}
