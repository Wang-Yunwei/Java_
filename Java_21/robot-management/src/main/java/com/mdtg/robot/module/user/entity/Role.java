package com.mdtg.robot.module.user.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mdtg.robot.common.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色表
 */
@Getter
@Setter
@TableName(value ="mdtg_role")
public class Role extends BaseEntity {
    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
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
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Object permissionIds;

    /**
     * 类型(0-系统默认,1-自定义)
     */
    private Integer type;
}