package mdtg.business.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author WangYunwei [2026-03-31]
 */
@FeignClient(name = "customer-service", url = "http://${mdtg.ip}:${mdtg.port}/customerService")
public interface CustomerFeign {

    @PostMapping(name = "获取 AccessToken", path = "/voiceClone/page")
    Map<String,Object> page();
}
