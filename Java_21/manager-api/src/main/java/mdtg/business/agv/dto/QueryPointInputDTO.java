package mdtg.business.agv.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-04-16]
 */
@Getter
@Setter
public class QueryPointInputDTO {

    @Schema(description = "主键")
    private Long pointId;

    @Schema(description = "点位名称")
    private String name;

    @Schema(description = "类别(0-点位,1-轨迹)")
    private Integer type;

    @Schema(description = "MAC地址")
    private String macAddress;

    @Schema(description = "地图ID")
    private Long mapId;

    @Schema(description = "页码")
    private Long pageNum = 0L;

    @Schema(description = "页长")
    private Long pageSize = 10L;
}
