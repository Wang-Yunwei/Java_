package com.mdtg.robot.module.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-20]
 */
@Getter
@Setter
public class VerifyTokenOutputDTO {

    @Schema(description = "验证结果")
    private Boolean valid;

    @Schema(description = "Token验证信息")
    private String message;

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "组织编码")
    private String orgCode;
}
