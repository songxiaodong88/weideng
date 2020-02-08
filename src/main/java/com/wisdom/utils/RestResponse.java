package com.wisdom.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
public class RestResponse extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    public static final int SUCCESS_CODE = 1;
    public static final  String CODE = "code";
    public RestResponse() {
        put(CODE, SUCCESS_CODE);
        put("msg", "success");
    }

    /*public static RestResponse error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }*/

    public static RestResponse error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static RestResponse error(int code, String msg) {
        RestResponse restResponse = new RestResponse();
        restResponse.put(CODE, code);
        restResponse.put("msg", msg);
        return restResponse;
    }

    public static RestResponse success(String msg) {
        RestResponse restResponse = new RestResponse();
        restResponse.put("msg", msg);
        return restResponse;
    }

    public static RestResponse success(int code, String msg) {
        RestResponse restResponse = new RestResponse();
        restResponse.put(CODE, code);
        restResponse.put("msg", msg);
        return restResponse;
    }

    public static RestResponse success(int code, Object obj) {
        RestResponse restResponse = new RestResponse();
        restResponse.put(CODE, code);
        restResponse.put("msg", obj);
        return restResponse;
    }

    public static RestResponse success(Map<String, Object> map) {
        RestResponse restResponse = new RestResponse();
        restResponse.putAll(map);
        return restResponse;
    }


    public static RestResponse success() {
        return new RestResponse();
    }

    @Override
    public RestResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
