package com.mdtg.robot.common.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author WangYunwei [2024-07-11]
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Schema(description = "接口统一返回的包装类")
public final class ResponseDTO<T> implements Serializable {

    @Schema(description = "业务状态码，除 0 以外都是错误状态")
    private int code = 0;

    @Schema(description = "执行信息")
    private String message = "SUCCESS";

    @Schema(description = "时间戳，精度: 毫秒")
    private LocalDateTime timestamp;

    private T body;

    public static final String MSG_SUCCESS = "SUCCESS";

    public static final String MSG_ERROR = "ERROR";

    public ResponseDTO() {
        this.timestamp = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public static <T> ResponseDTO<T> wrapSuccess() {

        return new ResponseDTO<>();
    }

    public static <T> ResponseDTO<T> wrapSuccess(final T body) {

        return new ResponseDTO<T>().setBody(body);
    }

    public static <T> ResponseDTO<T> wrapException(String message) {

        return new ResponseDTO<T>().setCode(-1).setMessage(message);
    }

    public static <T> ResponseDTO<T> wrapException(int code, String message) {

        return new ResponseDTO<T>().setCode(code).setMessage(message);
    }

}
