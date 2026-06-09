package com.mdtg.robot.module.device;

import com.mdtg.robot.common.exception.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangYunwei [2026-03-20]
 */
@Tag(name = "设备管理")
@RestController
public class DeviceManagerController {

    @Operation(summary = "OTA版本检查", description = "设备上报当前版本信息，服务器返回是否需要更新以及更新的版本信息")
    @PostMapping("/ota")
    public ResponseDTO<?> checkOTAVersion() {

        return null;
    }
}
