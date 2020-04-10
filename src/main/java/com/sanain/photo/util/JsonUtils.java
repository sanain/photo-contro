package com.sanain.photo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanain.photo.pojo.PtUser;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * json转成list
     * @param str
     * @param
     * @param <T>
     * @return
     */
    public static <T>List<T> jsonToList(String str , Class<T> t){
        ObjectMapper mapper = new ObjectMapper();
        List<T> list = new ArrayList<>();
        try {
            JavaType type = mapper.getTypeFactory().constructParametricType(ArrayList.class, t);
            list = mapper.readValue(str, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * list转成json
     * @param list
     * @return
     */
    public static <T>String listToJson(List<T> list){
        String str = "";

        try {
            str = new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return str;
    }
}
