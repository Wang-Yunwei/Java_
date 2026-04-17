package mdtg.business.device.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author WangYunwei [2026-04-01]
 */
@Getter
@Setter
public class AddDeviceInputDTO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "设备别名")
    private String alias;

    @Schema(description = "MAC地址")
    private String macAddress;

    @Schema(description = "硬件型号")
    private String board;

    @Schema(description = "固件版本号")
    private String firmwareVersion;

    @Schema(description = "最后连接时间")
    private Date lastConnectionTime;

    @Schema(description = "自动更新开关(0-关闭,1-开启)")
    private Integer autoUpdate;

    @Schema(description = "类型(0-头,1-工牌,2-小车)")
    private Integer type;

    @Schema(description = "type为1、2时,绑定type为0的设备MAC地址")
    private String parentMac;

    @Schema(description = "单位编码")
    private String companyCode;

    @Schema(description = "单位简称")
    private String companyName;

    @Schema(description = "二级组织编码")
    private String secondOrgCode;

    @Schema(description = "二级组织简称")
    private String secondOrgName;

    @Schema(description = "组织编码")
    private String orgCode;

    @Schema(description = "组织简称")
    private String orgName;
}
