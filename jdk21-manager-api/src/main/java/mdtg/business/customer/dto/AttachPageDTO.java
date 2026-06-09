package mdtg.business.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author WangYunwei [2026-04-01]
 */
@Getter
@Setter
@Accessors(chain = true)
public class AttachPageDTO extends BaseDTO {

    @Schema(description = "业务ID")
    private Long businessId;

    @Schema(description = "附件类型")
    private Integer attachType;

    @Schema(description = "文件名")
    private String fileName;
}
