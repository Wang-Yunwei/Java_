package mdtg.business.agv.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
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
     * 坐标点{"x":"3.455", "y":"-12.921", "yaw":"1.725"}
     */
    private Object point;

    /**
     * 地图名称
     */
    private String mapName;
}