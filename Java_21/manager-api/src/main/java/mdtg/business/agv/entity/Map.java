package mdtg.business.agv.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import mdtg.business.common.toolkits.BaseEntity;

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
     * 附件ID
     */
    private Long attachId;
}