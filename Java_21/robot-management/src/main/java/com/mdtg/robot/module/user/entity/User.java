package com.mdtg.robot.module.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mdtg.robot.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户表
 */
@Getter
@Setter
@TableName(value = "mdtg_user")
public class User extends BaseEntity {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     * - @JsonIgnore 敏感字段永远不返回
     */
    @JsonIgnore
    private String password;

    /**
     * 性别(0-未知,1-男,2-女)
     */
    private Integer gender;

    /**
     * 居民身份证
     */
    private String identityCard;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 住址
     */
    private String address;

    /**
     * 角色列表
     */
    private Object roleIds;

    /**
     * 类型(0:系统默认,1:自定义)
     */
    private Integer type;

    /**
     * 状态(0-正常,1-锁定)
     */
    private Integer status;

    /**
     * 机构标识
     */
    private String orgCode;
}