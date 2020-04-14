package com.ywc.ymall.aop;

import com.ywc.ymall.to.ResultParam;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 嘟嘟~
 * @date 2020/4/14 22:44
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ArithmeticException.class})
    public Object handlerException(Exception exception){
        return new ResultParam().validateFailed("看好条件!");
    }


    @ExceptionHandler(value = {NullPointerException.class})
    public Object handlerException02(Exception exception){
        return new ResultParam().validateFailed("空指针了...");
    }
}
