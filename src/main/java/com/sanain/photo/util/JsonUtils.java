package com.sanain.photo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanain.photo.pojo.PtUser;

/**
 * @Author slearing
 */
public class JsonUtils {
    /**
     * 对象装成json
     * @param t
     * @param <T>
     * @return
     */
    public static <T>String toJson(T t){
        String str = "";

        try {
            str = new ObjectMapper().writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return str;
    }

    /**
     * json转成对象
     * @param
     * @param <T>
     * @return
     */
    public static <T>T toObject(String str , Class<T> clazz){
        T t = null;
        try {
            t = new ObjectMapper().readValue(str , clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return t;
    }
}
