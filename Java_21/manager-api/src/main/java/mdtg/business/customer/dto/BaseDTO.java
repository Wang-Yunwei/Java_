package mdtg.business.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-04-01]
 */
@Getter
@Setter
public class BaseDTO {

    @Schema(description = "页码")
    private Long current = 0L;

    @Schema(description = "页长")
    private Long pageSize = 10L;

}
