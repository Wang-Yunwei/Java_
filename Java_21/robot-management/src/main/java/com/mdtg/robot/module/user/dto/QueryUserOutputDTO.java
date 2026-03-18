package com.mdtg.robot.module.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-17]
 */
@Getter
@Setter
public class QueryUserOutputDTO {
    @Schema(description = "用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "性别(0-未知,1-男,2-女)")
    private Integer gender;

    @Schema(description = "居民身份证")
    private String identityCard;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "住址")
    private String address;

    @Schema(description = "角色列表")
    private Object roleIds;

    @Schema(description = "类型(0-系统默认,1-自定义)")
    private Integer type;

    @Schema(description = "状态(0-正常,1-锁定)")
    private Integer status;

    @Schema(description = "机构标识")
    private String orgCode;
}
