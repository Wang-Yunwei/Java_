package mdtg.business.customer.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-31]
 */
@Getter
@Setter
public class QueryVoiceInputDTO {

    private Long name;

    private Long orgCode;

    private Integer status;

    private Long current = 0L;

    private Long pageSize = 10L;
}
