package com.mdtg.robot.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author WangYunwei [2024-08-09]
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler({BusinessException.class})
    public ResponseDto<?> handleBusinessException(final BusinessException e) {

        log.error(e.getMessage(), e);
        if(e.getCode() != 0) {
            return ResponseDto.wrapException(e.getCode(),e.getMessage());
        }
        return ResponseDto.wrapException(e.getMessage());
    }

    /**
     * 默认异常处理
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<?> defaultHandleException(final RuntimeException e) {

        log.error(e.getMessage(), e);
        return ResponseDto.wrapException(e.getMessage());
    }
}
