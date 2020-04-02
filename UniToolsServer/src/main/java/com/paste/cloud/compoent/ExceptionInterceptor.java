package com.paste.cloud.compoent;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(value = {Exception.class})
    public JSONObject catchException(Exception e) {
        e.printStackTrace();
        return JSONResult.inner_error;
    }

}
