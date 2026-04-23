package mdtg.business.agv.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author WangYunwei [2026-04-21]
 */
@Getter
@Setter
public class AddTaskInputDTO {

    @Schema(description = "主键(注: ID存在执行更新,否则执行新增)")
    private Long id;

    @Schema(description = "任务名称")
    private String name;

    @Schema(description = "任务类型(0-手动,1-定时)")
    private String type;

    @Schema(description = "任务状态(0-待执行,1-执行中,2-已完成,3-执行失败)")
    private Integer status;

    @Schema(description = "执行次数")
    private Integer executeNum;

    @Schema(description = "计划执行时间")
    private String executeTime;

    @Schema(description = "实际完成时间")
    private Date finishTime;

    @Schema(description = "起始坐标点索引")
    private Integer startIndex;

    @Schema(description = "关联坐标点ID列表")
    private Object coordinatePointIds;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "设备ID")
    private Long deviceId;

    @Schema(description = "地图ID")
    private Long mapId;
}
