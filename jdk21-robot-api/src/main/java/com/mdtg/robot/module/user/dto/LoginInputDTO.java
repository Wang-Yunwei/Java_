package com.mdtg.robot.module.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-19]
 */
@Getter
@Setter
public class LoginInputDTO {

    @Schema(description = "账号",example = "18168960416")
    @NotBlank(message = "账号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String accountNumber;

    @Schema(description = "密码",example = "123@1231")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20之间")
    private String password;
}
