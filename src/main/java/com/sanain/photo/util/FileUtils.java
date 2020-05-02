package com.sanain.photo.util;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件工具类
 * @Author sanain
 */
public class FileUtils {
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

    /**
     * 保存多个文件
     * @param list
     * @param path
     * @return
     */
    public static List<String> saveMultipleFile(List<MultipartFile> list , String path){

        if(CollectionUtils.isEmpty(list)){
            return null;
        }

        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        List<String> fileNames = new ArrayList<>();

        for(MultipartFile multipartFile : list) {
            String random = UUID.randomUUID().toString();

            int i = multipartFile.getOriginalFilename().lastIndexOf(".");
            String suffix = multipartFile.getOriginalFilename().substring(i);

            File newFile = new File(path + random + suffix);
            try {
                multipartFile.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            fileNames.add(random+suffix);
        }
        return fileNames;
    }

    /**
     * 压缩文件
     *
     * @param filePaths 需要压缩的文件路径集合
     * @throws IOException
     */
    private static void zipFile(List<String> filePaths , ZipOutputStream zos) {
        //设置读取数据缓存大小
        byte[] buffer = new byte[4096];
        try {
            //循环读取文件路径集合，获取每一个文件的路径
            for (String filePath : filePaths) {
                File inputFile = new File(filePath);
                //判断文件是否存在
                if (inputFile.exists()) {
                    //创建输入流读取文件
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
                    //将文件写入zip内，即将文件进行打包
                    zos.putNextEntry(new ZipEntry(inputFile.getName()));
                    //写入文件的方法，同上
                    int size = 0;
                    //设置读取数据缓存大小
                    while ((size = bis.read(buffer)) > 0) {
                        zos.write(buffer, 0, size);
                    }
                    //关闭输入输出流
                    zos.closeEntry();
                    bis.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != zos) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 把多个文件夹打包成zip
     * @param response
     * @param srcFiles
     * @param zipFileName
     */
    public static void downloadZipFiles(HttpServletResponse response, List<String> srcFiles, String zipFileName) {
        try {
//            response.reset(); // 重点突出
            response.setCharacterEncoding("UTF-8"); // 重点突出
            response.setContentType("applicatoin/octet-stream"); // 不同类型的文件对应不同的MIME类型 // 重点突出
            // 对文件名进行编码处理中文问题
            zipFileName = new String(zipFileName.getBytes(), StandardCharsets.UTF_8);
            // inline在浏览器中直接显示，不提示用户下载
            // attachment弹出对话框，提示用户进行下载保存本地
            // 默认为inline方式
            response.setHeader("Content-Disposition", "attachment;filename=" + zipFileName);

            // --设置成这样可以不用保存在本地，再输出， 通过response流输出,直接输出到客户端浏览器中。
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            zipFile(srcFiles, zos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**UUID
     * 复制文件
     * @param source 原文件的路径
     * @param target 目标文件的路径
     */
    public static void copyFile(String source , String target){
        File sourceFile = new File(source);
        File targetFile = new File(target);

        try {
            Files.copy(sourceFile.toPath(),targetFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void getFile(byte[] bfile, String fileName) {    //创建文件
        File file=new File(fileName);
        try {
            if (!file.exists()){file.createNewFile();}
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void responseTo(File file, HttpServletResponse res) {  //将文件发送到前端
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

    /**
     * 删除多个文件
     * @param fileNames 多个文件名
     * @param delimiter 文件名之间的分隔符
     * @param path  文件所在的路径
     * @return
     */
    public static void deleteFiles(String fileNames , String delimiter , String path){
        if(StringUtils.isEmpty(fileNames)){
            return;
        }

        String[] nameArr = fileNames.split(delimiter);

        for(String name :  nameArr){
            File f = new File(path+name);
            if(f.exists()){
                f.delete();
            }
        }
    }
}
