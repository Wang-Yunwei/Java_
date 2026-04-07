package mdtg.business.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-04-03]
 */
@Getter
@Setter
public class UpdateVoiceIdDTO {

    @Schema(description = "mdtg_voice_clone表主键", required = true)
    private Long id;

    @Schema(description = "ai_voice_clone表主键", required = true)
    private String voiceId;
}
