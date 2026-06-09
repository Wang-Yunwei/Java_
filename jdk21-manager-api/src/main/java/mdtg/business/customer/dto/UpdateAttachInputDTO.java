package mdtg.business.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import mdtg.business.attachment.entity.Attach;

import java.util.Date;
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

    @Schema(description = "知识库信息")
    private KnowledgeBaseInfo knowledgeBaseInfo;

    @Schema(description = "声音克隆信息")
    private VoiceCloneInfo voiceCloneInfo;

    @Getter
    @Setter
    public static class KnowledgeBaseInfo {

        @Schema(description = "主键")
        private Long id;

        @Schema(description = "知识库ID")
        private String datasetId;

        @Schema(description = "RAG模型配置ID")
        private String ragModelId;

        @Schema(description = "知识库名称")
        private String name;

        @Schema(description = "知识库描述")
        private String description;

        @Schema(description = "创建者")
        private Long creator;

        @Schema(description = "创建时间")
        private Date createdAt;

        @Schema(description = "更新者")
        private Long updater;

        @Schema(description = "更新时间")
        private Date updatedAt;
    }

    @Getter
    @Setter
    public static class VoiceCloneInfo {

        @Schema(description = "主键")
        private Long id;

        @Schema(description = "声音名称")
        private String name;

        @Schema(description = "平台名称")
        private String modelId;

        @Schema(description = "组织编码")
        private Long orgCode;

        @Schema(description = "声音ID")
        private List<String> voiceIds;

        @Schema(description = "附件信息")
        private List<Attach> voiceFiles;
    }
}
