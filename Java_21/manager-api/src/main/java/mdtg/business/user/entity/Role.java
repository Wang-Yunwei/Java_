package mdtg.business.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Getter;
import lombok.Setter;
import mdtg.business.common.entity.BaseEntity;

/**
 * 角色表
 */
@Getter
@Setter
@TableName(value = "mdtg_role")
public class Role extends BaseEntity {

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
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Object permissionIds;

    /**
     * 类型(0-系统默认,1-自定义)
     */
    private Integer type;
}