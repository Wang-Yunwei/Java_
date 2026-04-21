package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 任务表
 * @TableName mdtg_task
 */
@TableName(value ="mdtg_task")
@Data
public class Task {
    /**
     * 主键
     */
    @TableId
    private Long id;

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
     * 关联坐标点ID列表
     */
    private Object coordinatePointIds;

    /**
     * 关联设备ID
     */
    private Long deviceId;

    /**
     * 备注
     */
    private String remark;

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