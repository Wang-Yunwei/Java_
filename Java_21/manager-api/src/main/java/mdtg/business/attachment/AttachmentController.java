package mdtg.business.attachment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mdtg.business.attachment.dto.QueryAttachmentInputDTO;
import mdtg.business.attachment.entity.Attach;
import mdtg.business.attachment.service.AttachService;
import mdtg.business.common.toolkits.ResponseDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangYunwei [2026-03-26]
 */
@Tag(name = "MDTG - 附件管理")
@RestController
@RequestMapping("attachment")
public class AttachmentController {

    private AttachService attachService;

    public AttachmentController(AttachService attachService) {

        this.attachService = attachService;
    }

    @Operation(summary = "附件 - 新增or更新")
    @PostMapping("/add-or-update")
    public ResponseDTO<?> addAttachment(@RequestBody Attach inputDTO) {

        return attachService.addAttachment(inputDTO);
    }

    @Operation(summary = "附件 - 删除")
    @GetMapping("/delete/{attachId}")
    public ResponseDTO<?> deleteAttachment(@PathVariable String attachId) {

        return attachService.deleteAttachment(attachId);
    }

    @Operation(summary = "附件 - 查询",description = "1.根据条件查询附件列表，支持分页; 2.如果attachId存在，则根据附件Id查询附件详情")
    @PostMapping("/query")
    public ResponseDTO<?> queryAttachment(@RequestBody QueryAttachmentInputDTO inputDTO) {

        return attachService.queryAttachment(inputDTO);
    }
}
