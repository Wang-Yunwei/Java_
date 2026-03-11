package com.mdtg.robot.module.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mdtg.robot.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 权限表
 * @TableName mdtg_permission
 */
@Getter
@Setter
@TableName(value ="mdtg_permission")
public class Permission extends BaseEntity {
    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 编码(如: user_manage:list:create/read/update/delete)
     */
    private String code;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 菜单路径(user_manage:list:read)
     */
    private String menuPath;

    /**
     * 类型(0-系统默认,1-自定义)
     */
    private Integer type;
}