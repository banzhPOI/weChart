package com.poison.wechart.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class JsonUtils {
	//java对象转换成json字符串  
	public static String ObjectToJson(Object o){
		return ObjectToJson(o, false);
    }
	
	//java对象转换成json字符串  
	public static String ObjectToJson(Object o, boolean ignoreNull){
        ObjectMapper om = new ObjectMapper();
        if(ignoreNull){
        	om.setSerializationInclusion(Include.NON_NULL);
        }
        Writer w = new StringWriter();  
        String json = null;  
        try {  
        	om.writeValue(w, o);  
            json = w.toString();
        } catch (IOException e) {  
        } finally {
        	if(w!=null){
        		try {
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return json; 
    }
	
	/**
	 * json轉Object
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T JsonToObject(String json, Class<T> type){
        ObjectMapper om = new ObjectMapper();
        T t = null;
        try {  
        	t = om.readValue(json, type);
        } catch (Exception e) {  
        } finally {
        }
        return t; 
    }
	
	/**
	 * json轉List
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> List<T> JsonToList(String json, Class<T> type){
        ObjectMapper om = new ObjectMapper();
        List<T> t = null;
        try {
        	JavaType javaType = om.getTypeFactory().constructParametricType(List.class, type);
        	t = om.readValue(json, javaType);
        } catch (Exception e) {  
        } finally {
        }
        return t; 
    }
	
	/**
	 * json轉List<List>
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> List<List<T>> JsonToListList(String json, Class<T> type){
        ObjectMapper om = new ObjectMapper();
        List<List<T>> t = null;
        try {
//        	JavaType javaType = om.readValue(json, new TypeReference<List<List<T>>>() {});
        	JavaType javaType = om.getTypeFactory().constructParametricType(List.class, type);
        	t = om.readValue(json, om.getTypeFactory().constructParametricType(List.class, javaType));
        } catch (Exception e) {  
        } finally {
        }
        return t; 
    }

	public static <T, K> Map<T, List<K>> JsonToMapList(String json, Class<T> keyType, Class<K> valueType) {
		ObjectMapper om = new ObjectMapper();
        Map<T, List<K>> map = null;
        try {
	        JavaType listType = om.getTypeFactory().constructParametricType(List.class, valueType);
	        JavaType javaType = om.constructType(keyType);
        	JavaType mapType = om.getTypeFactory().constructParametricType(Map.class, javaType, listType);
        	map = om.readValue(json, mapType);
        } catch (Exception e) {  
        } finally {
        }
        return map; 
	}
	
}
