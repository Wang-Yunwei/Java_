package mdtg.business.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mdtg.business.common.feign.CustomerFeign;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.customer.dto.QueryVoiceInputDTO;
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
    @GetMapping("/voice-clone/query")
    public ResponseDTO<?> queryVoice(@RequestHeader("Authorization") String token, @RequestBody QueryVoiceInputDTO inputDTO) {

        Result page = customerFeign.page(token, inputDTO);
        return ResponseDTO.wrapSuccess(page.getData());
    }
}
