package mdtg.business.attachment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author WangYunwei [2026-03-26]
 */
@Getter
@Setter
public class AddAttachmentInputDTO {

    @Schema(description = "业务关联Id: 声音克隆Id、知识库Id")
    private String businessId;

    @Schema(description = "附件业务类别: 0-声音克隆,1-知识库,2-...")
    private Integer businessType;

    @Schema(description = "附件类型:audio/way")
    private String contentType;

    @Schema(description = "附件名称")
    private String fileName;

    @Schema(description = "文件大小")
    private Long fileSize;

    @Schema(description = "minio的key")
    private String objectName;

    @Schema(description = "创建者ID")
    private Long createBy;

    @Schema(description = "创建者名字")
    private String createName;

    @Schema(description = "创建时间")
    private LocalDateTime createDate;
}
