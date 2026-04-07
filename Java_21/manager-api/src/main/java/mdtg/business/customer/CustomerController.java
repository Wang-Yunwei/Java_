package mdtg.business.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.customer.dto.*;
import mdtg.business.customer.feign.CustomerFeign;
import mdtg.common.utils.Result;
import mdtg.modules.voiceclone.dto.VoiceCloneDTO;
import mdtg.modules.voiceclone.service.VoiceCloneService;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangYunwei [2026-03-31]
 */
@Tag(name = "MDTG - 用户端 API")
@RequestMapping("/customer-service")
@RestController
public class CustomerController {

    CustomerFeign customerFeign;
    VoiceCloneService voiceCloneService;

    public CustomerController(CustomerFeign customerFeign) {

        this.customerFeign = customerFeign;
    }

    @Operation(summary = "声音克隆 - 查询")
    @PostMapping("/voice-clone/query")
    public ResponseDTO<?> queryVoice(@RequestHeader("Authorization") String token, @RequestBody VoiceClonePageDTO inputDTO) {

        Result page = customerFeign.voiceClonePage(token, inputDTO);
        return ResponseDTO.wrapSuccess(page.getData());
    }

    @Operation(summary = "声音克隆 - 更新VoiceId")
    @PostMapping("/voice-clone/update-voiceId")
    public ResponseDTO<?> updateVoiceId(@RequestHeader("Authorization") String token, @RequestBody UpdateVoiceIdDTO inputDTO) {

        Result page = customerFeign.updateVoiceId(token, inputDTO);
        return ResponseDTO.wrapSuccess(page.getData());
    }

    @Operation(summary = "知识库 - 查询")
    @PostMapping("/knowledge/query")
    public ResponseDTO<?> queryKnowledge(@RequestHeader("Authorization") String token, @RequestBody KnowledgePageDTO inputDTO) {

        Result page = customerFeign.knowledgePage(token, inputDTO);
        return ResponseDTO.wrapSuccess(page.getData());
    }

    @Operation(summary = "附件 - 查询")
    @PostMapping("/attach/query")
    public ResponseDTO<?> queryAttach(@RequestHeader("Authorization") String token, @RequestBody AttachPageDTO inputDTO) {

        Result page = customerFeign.attachPage(token, inputDTO);
        return ResponseDTO.wrapSuccess(page.getData());
    }

    @Operation(summary = "附件 - 更新状态")
    @PostMapping("/attach/update-status")
    public ResponseDTO<?> updateAttach(@RequestHeader("Authorization") String token, @RequestBody UpdateAttachInputDTO inputDTO) {

        if (inputDTO.getAttachStatus() == 3) {
            if (inputDTO.getKnowledgeBaseId() != null && inputDTO.getKnowledgeBaseId() > 0) {

            }
            if (inputDTO.getVoiceCloneId() != null && inputDTO.getVoiceCloneId() > 0) {
                VoiceCloneDTO voiceCloneDTO = new VoiceCloneDTO();
                voiceCloneDTO.setModelId(inputDTO.getModelId());
                voiceCloneDTO.setUserId(inputDTO.getOrgCode());
                voiceCloneDTO.setVoiceIds(inputDTO.getVoiceIds());
                voiceCloneService.save(voiceCloneDTO);
            }
        }
        AttachUpdateStatusDTO dto = new AttachUpdateStatusDTO().setId(inputDTO.getAttachId()).setStatus(inputDTO.getAttachStatus());
        Result page = customerFeign.updateAttach(token, dto);

        return ResponseDTO.wrapSuccess(page.getData());
    }

    @Operation(summary = "附件 - 下载链接")
    @GetMapping("/attach/dow-link")
    public ResponseDTO<?> attachInfo(@RequestParam String fileName) {

        Result page = customerFeign.fileInfo(fileName);
        return ResponseDTO.wrapSuccess(page.getData());
    }
}
