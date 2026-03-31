package mdtg.business.common.feign;

import mdtg.business.customer.dto.QueryVoiceInputDTO;
import mdtg.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author WangYunwei [2026-03-31]
 */
@FeignClient(name = "customer-service", url = "http://${service-address.customer.ip}:${service-address.mdtg.port}/customerService")
public interface CustomerFeign {

    @PostMapping(name = "声音克隆 - 分页查询", path = "/voiceClone/page")
    Result page(@RequestHeader(value = "Authorization") String token, @RequestBody QueryVoiceInputDTO inputDTO);
}
