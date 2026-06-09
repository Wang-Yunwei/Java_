package mdtg.business.customer.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-04-01]
 */
@Getter
@Setter
public class VoiceClonePageDTO extends BaseDTO {

    private Long name;

    private Long orgCode;

    private Integer status;
}
