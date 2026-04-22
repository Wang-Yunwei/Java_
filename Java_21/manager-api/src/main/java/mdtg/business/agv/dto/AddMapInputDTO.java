package mdtg.business.agv.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-04-22]
 */
@Getter
@Setter
public class AddMapInputDTO {

    @Schema(description = "主键(注: 存在执行更新,否则执行新增)")
    private Long id;

    @Schema(description = "别名")
    private String alias;

    @Schema(description = "地图名")
    private String name;

    @Schema(description = "附件ID")
    private Long attachId;
}
