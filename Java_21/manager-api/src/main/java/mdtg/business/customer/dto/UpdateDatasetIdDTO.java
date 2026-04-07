package mdtg.business.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-04-03]
 */
@Getter
@Setter
public class UpdateDatasetIdDTO {

    @Schema(description = "唯一标识")
    private Long id;

    @Schema(description = "知识库ID")
    private String datasetId;
}
