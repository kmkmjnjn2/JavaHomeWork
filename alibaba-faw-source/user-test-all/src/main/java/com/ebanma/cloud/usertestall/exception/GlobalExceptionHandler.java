package com.ebanma.cloud.usertestall.exception;

/**
 * @author WHY
 * @version $ Id: GlobalExceptionHandler, v 0.1 2023/03/21 20:35 kmkmj Exp $
 */

import com.ebanma.cloud.usertestall.domain.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Result businessException(BusinessException e) {
        logger.error("捕捉到业务异常",e);
        return Result.fail(e);
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException e){
        logger.error(e.getMessage(), e);
        return Result.fail(e);
    }
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public Result throwableHandle(Throwable th) {
        logger.error("捕捉Throwable异常：", th);
        return Result.fail(th);
    }
}