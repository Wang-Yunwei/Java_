package mdtg.business.device.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import mdtg.business.common.BaseEntity;

import java.time.LocalDateTime;

/**
 * 设备信息表
 */
@Getter
@Setter
@TableName(value = "mdtg_device")
public class Device extends BaseEntity {

    /**
     * 设备别名
     */
    @Schema(description = "别名")
    private String alias;

    /**
     * MAC地址
     */
    @Schema(description = "MAC地址")
    private String macAddress;

    /**
     * 硬件型号
     */
    @Schema(description = "硬件型号")
    private String board;

    /**
     * 固件版本号
     */
    @Schema(description = "固件版本号")
    private String firmwareVersion;

    /**
     * 最后连接时间
     */
    @Schema(description = "最后连接时间")
    private LocalDateTime lastConnectionTime;

    /**
     * 自动更新开关(0-关闭,1-开启)
     */
    @Schema(description = "自动更新开关(0-关闭,1-开启)")
    private Integer autoUpdate;

    /**
     * 类型(0-头,1-工牌,2-小车)
     */
    @Schema(description = "类型(0-头,1-工牌,2-小车)")
    private Integer type;

    /**
     * type为1、2时,绑定type为0的设备MAC地址
     */
    @Schema(description = "type为1、2时,绑定type为0的设备MAC地址")
    private String parentMac;

    /**
     * 设备状态(0-故障,1-正常[2-离线,3-在线])
     */
    @Schema(description = "设备状态(0-故障,1-正常[2-离线,3-在线])")
    private Integer status;
}