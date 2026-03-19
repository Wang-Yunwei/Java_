package com.mdtg.robot.module.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-17]
 */
@Getter
@Setter
public class AddPermissionInputDTO {

    @Schema(description = "权限ID")
    private Long id;

    @Schema(description = "父级ID")
    private Long parentId;

    @Schema(description = "user_manage:list:create/read/update/delete")
    private String code;

    @Schema(description = "级别")
    private Integer level;

    @Schema(description = "user_manage:list:read")
    private String menuPath;

    @Schema(description = "类型(0-系统默认,1-自定义)")
    private Integer type;
}
