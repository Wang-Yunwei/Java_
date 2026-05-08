package mdtg.business.device.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author WangYunwei [2026-04-01]
 */
@Getter
@Setter
public class QueryDeviceInputDTO {

    @Schema(description = "主键")
    private Long deviceId;

    @Schema(description = "设备别名")
    private String alias;

    @Schema(description = "MAC地址")
    private String macAddress;

    @Schema(description = "硬件型号")
    private String board;

    @Schema(description = "固件版本号")
    private String firmwareVersion;

    @Schema(description = "最后连接时间")
    private LocalDateTime lastConnectionTime;

    @Schema(description = "自动更新开关(0-关闭,1-开启)")
    private Integer autoUpdate;

    @Schema(description = "类型(0-头,1-工牌,2-小车)")
    private Integer type;

    @Schema(description = "type为1、2时,绑定type为0的设备MAC地址")
    private String parentMac;

    @Schema(description = "设备状态(0-故障,1-正常[2-离线,3-在线])")
    private Integer status;

    @Schema(description = "组织编码")
    private String orgCode;

    @Schema(description = "页码")
    private Long pageNum = 0L;

    @Schema(description = "页长")
    private Long pageSize = 10L;
}
