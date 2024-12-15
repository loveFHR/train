package com.fhr.train.common.exception;

import com.fhr.train.common.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * @author FHR
 * @create 2024/12/15 21:49
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 所有的异常统一处理
     * @param e 异常
     * @return 统一返回
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Void> exceptionHandler(Exception e) {
        //log.error(e.getMessage());
        e.printStackTrace();
        return Result.error("系统错误");
    }

    /**
     * 自定义业务异常统一处理
     * @param e 业务异常
     * @return 统一返回
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result<Void> exceptionHandler(BusinessException e) {
        log.error("业务异常：{}", e.getExceptionEnum().getDesc());
        return Result.error(e.getExceptionEnum().getDesc());
    }

    /**
     * 校验异常
     * @param e 检验时出现的异常
     * @return 统一返回
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result<Void> exceptionHandler(BindException e) {
        log.error("校验异常：{}", e.getBindingResult().getAllErrors().getFirst().getDefaultMessage());
        return Result.error(e.getBindingResult().getAllErrors().getFirst().getDefaultMessage());
    }
}
