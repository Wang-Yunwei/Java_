package mdtg.business.customer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author WangYunwei [2026-04-01]
 */
@Getter
@Setter
@Accessors(chain = true)
public class AttachUpdateStatusDTO {

    private String id;

    private Integer status;
}
