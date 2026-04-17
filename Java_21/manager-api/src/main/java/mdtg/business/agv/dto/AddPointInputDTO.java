package mdtg.business.agv.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-04-16]
 */
@Getter
@Setter
public class AddPointInputDTO {

    @Schema(description = "主键(注: 该字段存在值执行更新,否则执行新增)")
    private Long id;

    @Schema(description = "点位名称")
    private String name;

    @Schema(description = "坐标点，格式示例：{x: 3.455, y: -12.921, yaw: 1.725}")
    private Object point;

    @Schema(description = "地图名称")
    private String mapName;
}
