package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName mdtg_user
 */
@TableName(value ="mdtg_user")
@Data
public class User {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
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
     * 类型(0-系统默认,1-自定义)
     */
    private Integer type;

    /**
     * 状态(0-正常,1-锁定)
     */
    private Integer status;

    /**
     * 状态(0-正常,1-锁定)
     */
    private Long sysUserId;

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