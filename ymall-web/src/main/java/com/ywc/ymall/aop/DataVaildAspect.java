package com.ywc.ymall.aop;

import com.ywc.ymall.to.ResultParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author 嘟嘟~
 * @date 2020/4/14 22:37
 */

@Aspect
@Component
public class DataVaildAspect {
    @Around("execution(* com.ywc.ymall..*Controller.*(..))")
    public Object validAround(ProceedingJoinPoint point) throws Throwable {
        Object proceed = null;
        Object[] args = point.getArgs();
        for (Object obj:args){
            if(obj instanceof BindingResult){
                BindingResult r = (BindingResult) obj;
                if(r.getErrorCount()>0){
                    //框架自动校验检测到错了
                    return new ResultParam().validateFailed(r);
                };
            }
        }
        //就是我们反射的  method.invoke();
        proceed = point.proceed(point.getArgs());
        return proceed;
    }
}
