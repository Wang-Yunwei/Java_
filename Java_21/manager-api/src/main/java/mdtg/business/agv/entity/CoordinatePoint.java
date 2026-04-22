package mdtg.business.agv.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import mdtg.business.common.toolkits.BaseEntity;

/**
 * 坐标点表
 */
@Getter
@Setter
@TableName(value = "mdtg_coordinate_point")
public class CoordinatePoint extends BaseEntity {

    /**
     * 点位名称
     */
    private String name;

    /**
     * 类别(0-点位,1-轨迹)
     */
    private Integer type;

    /**
     * 坐标点{"x": 3.803025, "y": -7.810509, "yaw": -2.950007, "updated_at": 1776265404}或[{xx,xx}]
     */
    private Object point;

    /**
     * MAC地址
     */
    private String macAddress;

    /**
     * 地图ID
     */
    private Long mapId;
}