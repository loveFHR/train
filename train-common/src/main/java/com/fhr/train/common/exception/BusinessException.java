package com.fhr.train.common.exception;

import lombok.Data;

/**
 * 业务异常
 *
 * @author FHR
 * @create 2024/12/15 22:00
 */
public class BusinessException extends RuntimeException {
    private BusinessExceptionEnum exceptionEnum;

    public BusinessException(BusinessExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public BusinessExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(BusinessExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    /**
     * <p>自定义异常我们已经知道异常的情况，所以不用写入堆栈信息</p>
     * <p>提高性能</p>
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
