package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 权限表
 * @TableName mdtg_permission
 */
@TableName(value ="mdtg_permission")
@Data
public class Permission {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 菜单路径(user_manage:list:read)
     */
    private String menuPath;

    /**
     * 路径级别
     */
    private Integer level;

    /**
     * 插件列表(字典表ID)
     */
    private Object plugIds;

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