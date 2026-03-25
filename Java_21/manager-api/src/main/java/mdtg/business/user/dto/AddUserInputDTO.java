package mdtg.business.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-25]
 */
@Getter
@Setter
public class AddUserInputDTO {

    /**
     * 用户ID
     */
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
     * 系统用户ID(关联系统用户表ID)
     */
    private Long sysUserId;
}
