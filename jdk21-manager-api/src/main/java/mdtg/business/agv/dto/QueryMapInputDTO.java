package mdtg.business.agv.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-04-22]
 */
@Getter
@Setter
public class QueryMapInputDTO {

    @Schema(description = "主键")
    private Long mapId;

    @Schema(description = "别名")
    private String alias;

    @Schema(description = "地图名")
    private String name;

    @Schema(description = "MAC地址")
    private String macAddress;

    @Schema(description = "页码")
    private Long pageNum = 0L;

    @Schema(description = "页长")
    private Long pageSize = 10L;
}
