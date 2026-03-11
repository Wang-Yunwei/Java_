package com.mdtg.robot.common.exception;

import java.io.Serializable;

/**
 * @author WangYunwei [2024-08-09]
 */
public class BusinessException extends RuntimeException implements Serializable {

    private Byte code = 0;

    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(Byte code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Byte getCode() {
        return code;
    }

    @Override
    public String getMessage() {

        return this.message;
    }
}
