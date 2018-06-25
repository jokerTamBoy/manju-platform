package com.manjushirwa.config.aop.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.druid.support.json.JSONUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 切面类
 * AOP切面，记录方法的调用，入参以及出參
 * @date 2018年4月26日 下午3:48:27
 * @author jopson
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    private ExceptionHandle exceptionHandle;

    @Pointcut("execution(public * com.manjushirwa.controller.*.*(..))")
//    @Pointcut("@annotation(logOnly)")
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        LOGGER.info("url={}",request.getRequestURL());
        //method
        LOGGER.info("method={}",request.getMethod());
        //ip
        LOGGER.info("id={}",request.getRemoteAddr());
        //class_method
        LOGGER.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName() + "," + joinPoint.getSignature().getName());
        //args[]
        LOGGER.info("args={}",joinPoint.getArgs());

        LOGGER.info("User-Agent = {}", request.getHeader("User-Agent"));


}

    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
            Result result = null;
        try {

        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
        if(result == null){
            return proceedingJoinPoint.proceed();
        }else {
        	 LOGGER.info("result={}", JSONUtils.toJSONString(result));
            return result;
        }
    }


    /**
     * 在切入点,return后执行
     * @param object
     */
    @AfterReturning(pointcut = "log()",returning = "object")//打印输出结果
    public void doAfterReturing(Object object){
        LOGGER.info("response={}",object == null ? null : object.toString());
    }

    /**
     * 在切点后抛出异常进行处理
     * @param object
     */

//    @AfterThrowing(pointcut = "log()", throwing = "object")
//    public void doAfterThrowing(Exception object){
//        LOGGER.info("exceptionHandle = {}", exceptionHandle.exceptionGet(object));
//
//        LOGGER.info("Exception={}", object.getMessage());
//        LOGGER.info("Exception={}", exceptionHandle.toString());
//    }

}
