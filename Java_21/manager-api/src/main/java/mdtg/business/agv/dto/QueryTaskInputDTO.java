package mdtg.business.agv.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-04-21]
 */
@Getter
@Setter
public class QueryTaskInputDTO {

    @Schema(description = "任务ID")
    private Long taskId;

    @Schema(description = "任务名称")
    private String name;

    @Schema(description = "任务类型(0-手动,1-定时)")
    private String type;

    @Schema(description = "任务状态(0-待执行,1-执行中,2-已完成,3-执行失败)")
    private Integer status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "关联设备ID")
    private Long deviceId;

    @Schema(description = "地图ID")
    private Long mapId;

    @Schema(description = "页码")
    private Long pageNum = 0L;

    @Schema(description = "页长")
    private Long pageSize = 10L;
}
