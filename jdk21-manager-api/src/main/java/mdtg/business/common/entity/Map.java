package mdtg.business.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 地图表
 */
@Getter
@Setter
@TableName(value = "mdtg_map",autoResultMap = true)
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
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<java.util.Map<String, Object>> points;

    /**
     * 附件ID
     */
    private Long attachId;
}