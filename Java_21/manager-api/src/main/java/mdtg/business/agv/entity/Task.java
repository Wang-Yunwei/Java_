package mdtg.business.agv.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import mdtg.business.common.toolkits.BaseEntity;

import java.util.Date;

/**
 * 任务表
 */
@Getter
@Setter
@TableName(value = "mdtg_task")
public class Task extends BaseEntity {

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务类型(0-手动,1-定时)
     */
    private String type;

    /**
     * 任务状态(0-待执行,1-执行中,2-已完成,3-执行失败)
     */
    private Integer status;

    /**
     * 执行次数
     */
    private Integer executeNum;

    /**
     * 计划执行时间
     */
    private Date executeTime;

    /**
     * 实际完成时间
     */
    private Date finishTime;

    /**
     * 起始坐标点索引
     */
    private Integer startIndex;

    /**
     * 备注
     */
    private String remark;

    /**
     * 关联坐标点ID列表
     */
    private Object coordinatePointIds;

    /**
     * 设备ID
     */
    private Long deviceId;

    /**
     * 地图ID
     */
    private Long mapId;
}