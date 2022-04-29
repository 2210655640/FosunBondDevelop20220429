package com.inspur.fosunbond.core.domain.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class JSONHelper {

    /**
     * 将JSONObject对象转换成Map集合
     */
    public static Map<String, Object> reflect(JSONObject json){
        Map<String, Object> map = new HashMap<>();
        Set keys = json.keySet();
        for(Object key : keys){
            Object o = json.get(key);
            if(o instanceof JSONObject)
                map.put((String) key, reflect((JSONObject) o));
            else if(o instanceof JSONObject)
                map.put((String) key, reflect((JSONObject) o));
            else
                map.put((String) key, o);
        }
        return map;
    }
    public static LinkedHashMap<String, Object> reflect2(JSONObject json){
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        Set keys = json.keySet();
        for(Object key : keys){
            Object o = json.get(key);
            if(o instanceof JSONObject)
                map.put((String) key, reflect((JSONObject) o));
            else if(o instanceof JSONObject)
                map.put((String) key, reflect((JSONObject) o));
            else
                map.put((String) key, o);
        }
        return map;
    }
}
