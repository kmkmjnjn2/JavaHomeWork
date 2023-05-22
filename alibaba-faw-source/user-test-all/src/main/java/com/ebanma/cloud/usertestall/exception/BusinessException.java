package com.ebanma.cloud.usertestall.exception;

import com.ebanma.cloud.usertestall.domain.common.ErrorCode;
import org.springframework.web.bind.annotation.ControllerAdvice;


public class BusinessException extends RuntimeException{
    private final String code;

    /**
     * 根据枚举构建业务异常
     * @param errorCode 错误代码
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDesc());
        this.code = errorCode.getCode();
    }

    /**
     * 根据自定义消息来构建业务异常
     * @param errorCode
     * @param message
     */
    public BusinessException(ErrorCode errorCode,String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    /**
     * 根据异常来构建业务异常
     * @param errorCode
     * @param cause
     */
    public BusinessException(ErrorCode errorCode,Throwable cause) {
        super(cause);
        this.code = errorCode.getCode();
    }
}
