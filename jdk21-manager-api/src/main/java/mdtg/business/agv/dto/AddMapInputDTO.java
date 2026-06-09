package mdtg.business.agv.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

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

    @Schema(description = "MAC地址")
    private String macAddress;

    @Schema(description = "坐标点", example = "[{'name': '办公室','x': 3.803025, 'y': -7.810509, 'yaw': -2.950007, 'updated_at': 1776265404}]")
    private List<Map<String,Object>> points;

    @Schema(description = "附件ID")
    private Long attachId;
}
