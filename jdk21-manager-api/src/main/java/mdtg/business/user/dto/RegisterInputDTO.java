package mdtg.business.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-27]
 */
@Getter
@Setter
public class RegisterInputDTO {

//    @Schema(description = "用户名", example = "mdtg")
//    private String username;
//
//    @Schema(description = "密码", example = "123456")
//    private String password;

    @Schema(description = "性别(0-未知,1-男,2-女)")
    private Integer gender;

    @Schema(description = "居民身份证", example = "320826199904161212")
    private String identityCard;

    @Schema(description = "手机号", example = "18168960416")
    private String phone;

    @Schema(description = "邮箱", example = "yunwei.w@medicalsystem.cn")
    private String email;

    @Schema(description = "住址", example = "江苏省苏州市工业园区归家港路222号麦迪斯顿南门")
    private String address;
}
