package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 角色表
 * @TableName mdtg_role
 */
@TableName(value ="mdtg_role")
@Data
public class Role {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 角色编码(如: admin)
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    /**
     * 权限列表
     */
    private Object permissionIds;

    /**
     * 类型(0-系统默认,1-自定义)
     */
    private Integer type;

    /**
     * 创建者ID
     */
    private Long createBy;

    /**
     * 创建者名字
     */
    private String createName;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新者ID
     */
    private Long updateBy;

    /**
     * 更新者名字
     */
    private String updateName;

    /**
     * 更新时间
     */
    private Date updateDate;

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