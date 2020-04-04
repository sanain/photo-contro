package com.sanain.photo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来封装返回的json
 * @Author sinain
 */
public class ResponseUtils {
    /**
     * 封装json
     * @param status
     * @param msg
     * @param data
     * @return
     */
    public static Map<String,Object>  packaging(String status , String msg , Object data){
        Map<String,Object> map = new HashMap<>();
        map.put("status",status);
        map.put("msg",msg);
        map.put("data",data);

        Map<String,Object> parseMap = new HashMap<>();
        parseMap.put("content",map);

        return parseMap;
    }
}
