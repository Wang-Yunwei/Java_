package com.mdtg.robot.module.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-17]
 */
@Getter
@Setter
public class QueryUserInputDTO {

    @Schema(description = "用户ID",example = "3208261518615861")
    private Long userId;

    @Schema(description = "用户名",example = "张三")
    private String userName;

    @Schema(description = "性别(0-未知,1-男,2-女)",example = "0")
    private Integer gender;

    @Schema(description = "住址",example = "江苏省苏州市工业园区归家港路222号麦迪斯顿南门")
    private String address;

    @Schema(description = "页码")
    private Long pageNum = 0L;

    @Schema(description = "页长")
    private Long pageSize = 10L;
}
