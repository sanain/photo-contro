package com.sanain.photo.util;

import java.util.Random;

/**
 * 验证码工具
 * @Author slearing
 */
public class CodeUtils {
    public static String getCode(){
        StringBuffer code = new StringBuffer();

        Random random = new Random();
        for(int i = 0 ; i < 4 ; i++){
            code.append(random.nextInt(10));
        }

        return code.toString();
    }
}
