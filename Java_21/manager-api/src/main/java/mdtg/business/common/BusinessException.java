package mdtg.business.common;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author WangYunwei [2024-08-09]
 */
@Getter
public class BusinessException extends RuntimeException implements Serializable {

    private int code = 0;

    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
