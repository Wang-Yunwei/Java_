package mdtg.business.customer.feign;

import mdtg.business.customer.dto.AttachPageDTO;
import mdtg.business.customer.dto.AttachUpdateStatusDTO;
import mdtg.business.customer.dto.KnowledgePageDTO;
import mdtg.business.customer.dto.VoiceClonePageDTO;
import mdtg.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author WangYunwei [2026-03-31]
 */
@FeignClient(name = "customer-service", url = "http://${service-address.customer.ip}:${service-address.customer.port}/customerService")
public interface CustomerFeign {

    @PostMapping(name = "声音克隆 - 分页查询", path = "/voiceClone/page")
    Result<?> voiceClonePage(@RequestHeader(value = "Authorization") String token, @RequestBody VoiceClonePageDTO inputDTO);

    @PostMapping(name = "知识库 - 分页查询", path = "/api/knowledge/base/page")
    Result<?> knowledgePage(@RequestHeader(value = "Authorization") String token, @RequestBody KnowledgePageDTO inputDTO);

    @PostMapping(name = "附件 - 分页查询", path = "/api/knowledge/base/attach/page")
    Result<?> attachPage(@RequestHeader(value = "Authorization") String token, @RequestBody AttachPageDTO inputDTO);

    @PostMapping(name = "附件 - 更新状态", path = "/api/knowledge/base/attach/updateStatus")
    Result<?> attachUpdateStatus(@RequestHeader(value = "Authorization") String token, @RequestBody AttachUpdateStatusDTO inputDTO);
}
