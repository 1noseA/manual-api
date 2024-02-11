package com.manualapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class CustomControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentNotValidException.class })
    public Map<String, Object> handleError400() {
    	Map<String, Object> errorMap = new HashMap<String, Object>();
    	errorMap.put("message", "リクエストが正しくありません。");
    	errorMap.put("status", HttpStatus.BAD_REQUEST);
		return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ NoResourceFoundException.class })
    public Map<String, Object> handleError404() {
    	Map<String, Object> errorMap = new HashMap<String, Object>();
    	errorMap.put("message", "該当するエンドポイントがありませんでした。");
    	errorMap.put("status", HttpStatus.NOT_FOUND);
		return errorMap;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public Map<String, Object> handleError405() {
    	Map<String, Object> errorMap = new HashMap<String, Object>();
    	errorMap.put("message", "許可されていないメソッドでアクセスされました。");
    	errorMap.put("status", HttpStatus.METHOD_NOT_ALLOWED);
		return errorMap;
    }
}
