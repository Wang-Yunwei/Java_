package mdtg.business.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.customer.dto.AttachPageDTO;
import mdtg.business.customer.dto.AttachUpdateStatusDTO;
import mdtg.business.customer.dto.KnowledgePageDTO;
import mdtg.business.customer.dto.VoiceClonePageDTO;
import mdtg.business.customer.feign.CustomerFeign;
import mdtg.common.utils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangYunwei [2026-03-31]
 */
@Tag(name = "MDTG - 用户端 API")
@RequestMapping("/customer-service")
@RestController
public class CustomerController {

    CustomerFeign customerFeign;

    public CustomerController(CustomerFeign customerFeign) {

        this.customerFeign = customerFeign;
    }

    @Operation(summary = "声音克隆 - 查询")
    @PostMapping("/voice-clone/query")
    public ResponseDTO<?> queryVoice(@RequestHeader("Authorization") String token, @RequestBody VoiceClonePageDTO inputDTO) {

        Result page = customerFeign.voiceClonePage(token, inputDTO);
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
    public ResponseDTO<?> updateStatusAttach(@RequestHeader("Authorization") String token, @RequestBody AttachUpdateStatusDTO inputDTO) {

        Result page = customerFeign.attachUpdateStatus(token, inputDTO);
        return ResponseDTO.wrapSuccess(page.getData());
    }
}
