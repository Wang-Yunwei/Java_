package mdtg.business.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author WangYunwei [2026-04-03]
 */
@Getter
@Setter
public class UpdateAttachInputDTO {

    @Schema(description = "附件ID")
    private String attachId;

    @Schema(description = "附件状态 0-驳回,1-待审核,2-审核中,3-待付费,4-训练中,5-训练成功,6-训练失败")
    private Integer attachStatus;

    @Schema(description = "声音克隆ID", example = "12345678901234567890")
    private Long voiceCloneId;

    @Schema(description = "平台名称", example = "3c9399622ae5cd8f1b5a54df5018af99")
    private String modelId;

    @Schema(description = "归属账号", example = "2009448031926194178")
    private Long orgCode;

    @Schema(description = "声音ID", example = "[\"111\"]")
    private List<String> voiceIds;

    @Schema(description = "知识库ID", example = "12345678901234567890")
    private Long knowledgeBaseId;

    @Schema(description = "知识库名称", example = "1122")
    private String name;

    @Schema(description = "知识库描述", example = "1122")
    private String description;

    @Schema(description = "RAG模型ID", example = "RAG_RAGFlow")
    private String ragModelId;
}
