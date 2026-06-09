package mdtg.business.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-19]
 */
@Getter
@Setter
public class VerifyTokenInputDTO {

    @Schema(description = "Token",example = "7f978e1557d9e6e6222eeb286ae46a54")
    @NotBlank
    private String token;
}
