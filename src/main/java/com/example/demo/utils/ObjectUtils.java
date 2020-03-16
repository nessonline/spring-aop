package com.example.demo.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.io.IOUtils;

public class ObjectUtils {

    private static ObjectMapper objectMapper;

    public static <T> T fromJson(String string, Class<T> clazz) {
        try {
            return getObjectMapper().readValue(string, clazz);
        } catch (Exception ex) {
            return null;
        }
    }

    public static <T> T fromFile(String fileName, Class<T> clazz) {
        return fromJson(readFile(fileName), clazz);
    }

    public static String toJson(Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String readFile(String fileName) {
        try {
            return IOUtils.toString(ObjectUtils.class.getClassLoader().getResourceAsStream(fileName), "UTF-8");
        } catch (Exception ex) {
            return null;
        }
    }

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            synchronized(ObjectUtils.class) {
                objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            }
        }

        return objectMapper;
    }
}
