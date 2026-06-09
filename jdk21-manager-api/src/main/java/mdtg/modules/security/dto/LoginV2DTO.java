package mdtg.modules.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-27]
 */
@Getter
@Setter
public class LoginV2DTO {

    @Schema(description = "用户名", example = "admin")
    private String username;

    @Schema(description = "密码", example = "123456xx")
    private String password;
}
