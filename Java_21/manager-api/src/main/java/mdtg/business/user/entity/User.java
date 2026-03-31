package mdtg.business.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import mdtg.business.common.entity.BaseEntity;

/**
 * 用户表
 */
@Getter
@Setter
@TableName(value = "mdtg_user")
public class User extends BaseEntity {

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 性别(0-未知,1-男,2-女)
     */
    @Schema(description = "性别(0-未知,1-男,2-女)")
    private Integer gender;

    /**
     * 居民身份证
     */
    @Schema(description = "居民身份证")
    private String identityCard;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 住址
     */
    @Schema(description = "住址")
    private String address;

    /**
     * 角色列表
     */
    @Schema(description = "角色列表")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Object roleIds;

    /**
     * 类型(0-系统默认,1-自定义)
     */
    @Schema(description = "类型(0-系统默认,1-自定义)")
    private Integer type;

    /**
     * 状态(0-锁定,1-正常)
     */
    @Schema(description = "状态(0-锁定,1-正常)")
    private Integer status = 1;

    /**
     * 系统用户ID(关联系统用户表ID)
     */
    @Schema(description = "系统用户ID(关联系统用户表ID)")
    private Long sysUserId;
}