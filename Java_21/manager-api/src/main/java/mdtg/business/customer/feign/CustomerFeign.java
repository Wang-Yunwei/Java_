package mdtg.business.customer.feign;

import mdtg.business.customer.dto.*;
import mdtg.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangYunwei [2026-03-31]
 */
@FeignClient(name = "customer-service", url = "http://${service-address.customer.ip}:${service-address.customer.port}/customerService/api")
public interface CustomerFeign {

    @PostMapping(name = "声音克隆 - 分页查询", path = "/voiceClone/page")
    Result<?> voiceClonePage(@RequestHeader(value = "Authorization") String token, @RequestBody VoiceClonePageDTO inputDTO);

    @PostMapping(name = "声音克隆 - 更新VoiceId", path = "/voiceClone/updateVoiceld")
    Result<?> updateVoiceId(@RequestHeader(value = "Authorization") String token, @RequestBody UpdateVoiceIdDTO inputDTO);

    @PostMapping(name = "知识库 - 分页查询", path = "/knowledge/base/page")
    Result<?> knowledgePage(@RequestHeader(value = "Authorization") String token, @RequestBody KnowledgePageDTO inputDTO);

    @PostMapping(name = "知识库 - 更新DatasetId", path = "/knowledge/base/updateDatasetId")
    Result<?> updateDatasetId(@RequestHeader(value = "Authorization") String token, @RequestBody UpdateDatasetIdDTO inputDTO);

    @PostMapping(name = "附件 - 分页查询", path = "/knowledge/base/attach/page")
    Result<?> attachPage(@RequestHeader(value = "Authorization") String token, @RequestBody AttachPageDTO inputDTO);

    @PostMapping(name = "附件 - 更新状态", path = "/attach/updateStatus")
    Result<?> updateAttach(@RequestHeader(value = "Authorization") String token, @RequestBody AttachUpdateStatusDTO inputDTO);

    @GetMapping(name = "附件 - 信息", path = "/attach/file/info")
    Result<?> fileInfo(@RequestParam(name = "objectName") String fileName);
}
