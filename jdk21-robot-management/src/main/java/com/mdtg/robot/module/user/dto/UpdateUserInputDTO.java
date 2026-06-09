package com.mdtg.robot.module.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-17]
 */
@Getter
@Setter
public class UpdateUserInputDTO {

    @Schema(description = "用户ID", example = "2032370008612429825")
    @NotNull(message = "ID不能为空")
    private Long id;

    @Schema(description = "用户名", example = "张三")
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度必须在2-20之间")
    private String username;

    @Schema(description = "性别(0-未知,1-男,2-女)", example = "0")
    private Integer gender;

    @Schema(description = "居民身份证", example = "320826199904161212")
    @NotBlank(message = "居民身份证号不能为空")
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[\\dXx]$",
            message = "居民身份证号码格式不正确")
    private String identityCard;

    @Schema(description = "手机号", example = "18168960416")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Schema(description = "邮箱", example = "yunwei.w@medicalsystem.cn")
    @Email(message = "邮箱格式不正确", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @Schema(description = "住址", example = "江苏省苏州市工业园区归家港路222号麦迪斯顿南门")
    @Size(max = 255, message = "住址长度不能超过255个字符")
    private String address;

    @Schema(description = "角色列表", example = "[1,2,3]")
    private Object roleIds;

    @Schema(description = "类型(0:系统默认,1:自定义)", example = "1")
    @Size(min = 0, max = 1, message = "类型值错误")
    private Integer type;

    @Schema(description = "状态(0-正常,1-锁定)", example = "0")
    @Size(min = 0, max = 1, message = "状态值错误")
    private Integer status;
}
