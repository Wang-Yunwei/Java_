package mdtg.business.customer.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-04-01]
 */
@Getter
@Setter
public class AttachPageDTO extends BaseDTO {

    private String id;

    private Integer attachType;

    private String fileName;
}
