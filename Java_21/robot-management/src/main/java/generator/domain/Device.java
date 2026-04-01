package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 设备信息表
 * @TableName mdtg_device
 */
@TableName(value ="mdtg_device")
@Data
public class Device {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 设备别名
     */
    private String alias;

    /**
     * MAC地址
     */
    private String macAddress;

    /**
     * 硬件型号
     */
    private String board;

    /**
     * 固件版本号
     */
    private String firmwareVersion;

    /**
     * 最后连接时间
     */
    private Date lastConnectionTime;

    /**
     * 自动更新开关(0-关闭,1-开启)
     */
    private Integer autoUpdate;

    /**
     * 类型(0-头,1-工牌,2-小车)
     */
    private Integer type;

    /**
     * 绑定ID(注: type为1、2时,绑定type为0的设备ID)
     */
    private Long bindingId;

    /**
     * ai_device表主键ID
     */
    private Long aiDeviceId;

    /**
     * 更新者ID
     */
    private Long updateBy;

    /**
     * 更新者名
     */
    private String updateName;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 创建者ID
     */
    private Long createBy;

    /**
     * 创建者名
     */
    private String createName;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 单位编码
     */
    private String companyCode;

    /**
     * 单位简称
     */
    private String companyName;

    /**
     * 二级组织编码
     */
    private String secondOrgCode;

    /**
     * 二级组织简称
     */
    private String secondOrgName;

    /**
     * 组织编码
     */
    private String orgCode;

    /**
     * 组织简称
     */
    private String orgName;

    /**
     * 删除标识(0-未删除,1-已删除)
     */
    private Integer deleteFlag;
}