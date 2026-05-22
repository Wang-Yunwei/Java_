package mdtg.business.agv.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import mdtg.business.common.BaseEntity;

/**
 * 地图表
 */
@Getter
@Setter
@TableName(value = "mdtg_map")
public class Map extends BaseEntity {

    /**
     * 别名
     */
    private String alias;

    /**
     * 地图名
     */
    private String name;

    /**
     * MAC地址
     */
    private String macAddress;

    /**
     * 坐标点[{"name": "办公室","x": 3.803025, "y": -7.810509, "yaw": -2.950007, "updated_at": 1776265404}]
     */
    private Object points;

    /**
     * 附件ID
     */
    private Long attachId;
}