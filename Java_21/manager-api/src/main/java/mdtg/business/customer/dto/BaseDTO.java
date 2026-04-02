package mdtg.business.customer.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-04-01]
 */
@Getter
@Setter
public class BaseDTO {

    private Long current = 0L;

    private Long pageSize = 10L;

}
