package com.springboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.enums.ResMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Map;

public class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJ_MAPPER = new ObjectMapper();

    public static String convertObjectToJsonString(Object pojo) {
        try {
            String jsonString = getObjectMapper()
                    .writeValueAsString(pojo);
            return jsonString;
        } catch (JsonProcessingException e) {
            LOGGER.error("Exception occurred when convert object to json string, Error stack: ", e);
            throw new CustomException(HttpStatus.resolve(ResMessage.JSON_PARSE_FAIL_OBJECT.getCode()), "Exception occurred when convert object to json string");
        }
    }

    public static <T extends Object> T convertJsonStringToObjectByClass(
            String jsonString, Class<T> clazz) {
        try {
            T targetObject = getObjectMapper().readValue(jsonString, clazz);
            return targetObject;
        } catch (IOException e) {
            LOGGER.error("Exception occurred when convert json string to object, Error stack: ", e);
            throw new CustomException(HttpStatus.resolve(ResMessage.JSON_PARSE_FAIL_STRING.getCode()), "Exception occurred when convert json string to object");
        }
    }

    public static <T extends Object> T mapToObjectByClass(Map map , Class<T> clazz){
        try {
            T targetObject = getObjectMapper().convertValue(map, clazz);
            return targetObject;
        } catch (Exception e) {
            LOGGER.error("Exception occurred when convert map string to object, Error stack: ", e);
            throw new CustomException(HttpStatus.resolve(ResMessage.JSON_PARSE_FAIL_STRING.getCode()), "Exception occurred when convert map to object");
        }
    }

    public static ObjectMapper getObjectMapper() {
        return OBJ_MAPPER;
    } 
}