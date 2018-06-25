package com.manjushirwa.config.aop.utils;

import com.manjushirwa.pojo.admin.po.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 错误定义
 * @date 2018年4月26日 下午3:47:37
 * @author jopson
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 判断错误是否是已定义的已知错误，不是则由未知错误代替，同时记录在log中
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionGet(Exception e){
        if(e instanceof DescribeException){
            DescribeException pingjiaException = (DescribeException) e;
            return ResultUtil.error(pingjiaException.getCode(), pingjiaException.getMessage() );
        }

        LOGGER.error("【系统异常】",e);
        return ResultUtil.error(ExceptionEnum.UNKNOW_ERROR);
    }
}
