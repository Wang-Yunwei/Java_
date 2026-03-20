package com.mdtg.robot.module.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-17]
 */
@Getter
@Setter
public class ResetPasswordInputDTO {

    @Schema(description = "用户ID",example = "2032370008612429825")
    @NotNull(message = "ID不能为空")
    private Long userId;

    @Schema(description = "旧密码",example = "123@123")
    private String oldPassword;

    @Schema(description = "新密码",example = "123@123")
    private String newPassword;
}
