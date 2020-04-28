package com.sanain.photo.util;

/**
 * @Author sanain
 * 常量工具类
 */
public class ConstantUtil {
    /*登录验证码的key*/
    public static final String LOGIN_CODE="login_code_";
    /*注册验证码的key*/
    public static final String EMAIL_CODE="email_code_";
    /*更新个人信息邮箱验证码的key*/
    public static final String  UPDATE_USER_INFO_EMAIL_CODE="update_user_info_email_code_";
    /*token放入cookie 的key*/
    public static final String COOKIE_TOKEN="cookie_token";
    /*用户头像保存的路径*/
    public static final String HEAD_PORTRAIT= "E:/photo/head_portrait/";
    /*用户上传图片的路径*/
    public static final String IMAGES= "E:/photo/images/";
    /*请求头像的路径*/
    public static final String PATH_HEAD_PORTRAIT= "/headPortrait/";
    /*请求上传图片的路径*/
    public static final String PATH_IMAGES= "/images/";
    /*头像虚拟路径映射中的本地路径*/
    public static final String FILE_HEAD_PORTRAIT= "file:E:/photo/head_portrait/";
    /*上传文件虚拟路径映射中的本地路径*/
    public static final String FILE_IMAGES= "file:E:/photo/images/";

    /*文件夹封面保存的路径*/
    public static final String DIR_IMG= "E:/photo/dir_img/";
    /*请求文件夹封面的路径*/
    public static final String PATH_DIR_IMG= "/dirImg/";
    /*上传文件虚拟路径映射中的本地路径*/
    public static final String FILE_DIR_IMG= "file:E:/photo/dir_img/";

    /*项目路径*/
    public static final String PRO_PATH= "http://localhost:8080/photo";
    /*所有文件夹分类的key*/
    public static final String ALL_DIR_TYPE="all_dir_type";
    /*文件夹默认图片*/
    public static final String DEFAULT_IMG="default.png";
    /*所有在线用户存储在redis中的key*/
    public static final String ALL_INLINE_USER = "allInLineUser";
}
