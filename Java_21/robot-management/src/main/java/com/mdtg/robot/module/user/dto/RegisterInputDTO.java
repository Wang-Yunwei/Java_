package com.mdtg.robot.module.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-11]
 */
@Getter
@Setter
@Schema(description = "用户-注册")
public class RegisterInputDTO {

    @Schema(description = "用户名",example = "张三")
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度必须在2-20之间")
    private String username;

    @Schema(description = "密码",example = "123@123")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20之间")
    private String password;

    @Schema(description = "性别(0-未知,1-男,2-女)")
    private Integer gender;

    @Schema(description = "居民身份证",example = "320826199904161212")
    @NotBlank(message = "居民身份证号不能为空")
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[\\dXx]$",
            message = "居民身份证号码格式不正确")
    private String identityCard;

    @Schema(description = "手机号",example = "18168960416")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Schema(description = "邮箱",example = "yunwei.w@medicalsystem.cn")
    @Email(message = "邮箱格式不正确", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @Schema(description = "住址",example = "江苏省苏州市工业园区归家港路222号麦迪斯顿南门")
    @Size(max = 255, message = "住址长度不能超过255个字符")
    private String address;
}
