package mdtg.business.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-25]
 */
@Getter
@Setter
public class UpdateUserInputDTO {

    /**
     * 用户ID
     */
    @Schema(description = "用户ID",example = "123")
    private Long id;

    /**
     * 用户名
     */
    @Schema(description = "用户名",example = "张三")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码",example = "123@1231")
    private String password;

    /**
     * 性别(0-未知,1-男,2-女)
     */
    @Schema(description = "性别(0-未知,1-男,2-女)")
    private Integer gender;

    /**
     * 居民身份证
     */
    @Schema(description = "居民身份证",example = "320826199904161212")
    private String identityCard;

    /**
     * 手机号
     */
    @Schema(description = "手机号",example = "18168960416")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱",example = "yunwei.w@medicalsystem.cn")
    private String email;

    /**
     * 住址
     */
    @Schema(description = "住址",example = "江苏省苏州市工业园区归家港路222号麦迪斯顿南门")
    private String address;

    /**
     * 角色列表
     */
    @Schema(description = "角色列表",example = "[1,2,3]")
    private Object roleIds;

    /**
     * 类型(0-系统默认,1-自定义)
     */
    @Schema(description = "类型(0-系统默认,1-自定义)",example = "0")
    private Integer type;

    /**
     * 状态(0-正常,1-锁定)
     */
    @Schema(description = "状态(0-正常,1-锁定)",example = "0")
    private Integer status;

    /**
     * 系统用户ID(关联系统用户表ID)
     */
    @Schema(description = "系统用户ID",example = "123")
    private Long sysUserId;
}
