package mdtg.business.attachment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-26]
 */
@Getter
@Setter
public class QueryAttachmentInputDTO {

    @Schema(description = "附件Id")
    private Long attachId;

    @Schema(description = "业务关联Id: 声音克隆Id、知识库Id")
    private String businessId;

    @Schema(description = "附件业务类别: 0-声音克隆,1-知识库,2-...")
    private Integer businessType;

    @Schema(description = "附件类型:audio/way")
    private String contentType;

    @Schema(description = "附件名称")
    private String fileName;

    @Schema(description = "页码")
    private Long pageNum = 0L;

    @Schema(description = "页长")
    private Long pageSize = 10L;
}
