package com.gateway.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static <T>T parseObject(String json,Class<T> tClass){
        try {
            return objectMapper.readValue(json,tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String toJsonString(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
