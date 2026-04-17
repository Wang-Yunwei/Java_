package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 坐标点表
 * @TableName mdtg_coordinate_point
 */
@TableName(value ="mdtg_coordinate_point")
@Data
public class CoordinatePoint {
    /**
     * 主键
     */
    @TableId
    private Long id;

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