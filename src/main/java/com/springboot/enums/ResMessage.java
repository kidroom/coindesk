package com.springboot.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ResMessage {

    OK(200, "success"),//成功
    NOT_FOUND(404, "not_found"),//找不到
    METHOD_NOT_ALLOWED(405, "method_not_allowed"),//方法不允許
    INTERNAL_SERVER_ERROR(500, "server_error"),//服務端異常    
    NO_DATA(521, "no_data"),//找不到資料
    JSON_PARSE_FAIL_STRING(600, "json_parse_fail_string"),//Json格式轉換錯誤(String to Object)
    JSON_PARSE_FAIL_OBJECT(601, "json_parse_fail_object");//Json格式轉換錯誤(Object to String)
	
    public static final List<Integer> SUCCESS_LIST = new ArrayList<>(Arrays.asList(200));

    Integer code;
    String desc;

    ResMessage(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public static ResMessage fromInt(int i) {
    	ResMessage type = intToTypeMap.get(Integer.valueOf(i));
        if (type == null) 
            return null;
        return type;
    }
    
    private static final Map<Integer, ResMessage> intToTypeMap = new HashMap<Integer, ResMessage>();
    static {
        for (ResMessage type : ResMessage.values()) {
            intToTypeMap.put(type.code, type);
        }
    }
}
