package com.wisdom.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * 全局异常处理页面
 * author longg
 */
@ControllerAdvice
public class GloablExceptionHandler {
    /*@ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        // 记录错误信息
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
        }
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("message", msg);
        //个人自定义
        jsonObject.put("message","抱歉，服务器跑丢了！请点击浏览器返回");
        return jsonObject;
    }*/
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        //跳转公共异常页面
        return "500";
    }
}

