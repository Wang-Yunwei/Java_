package com.mdtg.robot.module.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-17]
 */
@Getter
@Setter
public class QueryRoleInputDTO {

    @Schema(description = "角色ID")
    private Long id;

    @Schema(description = "角色编码(如: admin)")
    private String code;

    @Schema(description = "类型(0-系统默认,1-自定义)")
    private Integer type;
}
