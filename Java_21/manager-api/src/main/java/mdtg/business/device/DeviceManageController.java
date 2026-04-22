package mdtg.business.device;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.device.dto.AddDeviceInputDTO;
import mdtg.business.device.dto.QueryDeviceInputDTO;
import mdtg.business.device.service.DeviceManageService;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangYunwei [2026-04-01]
 */
@Tag(name = "MDTG - 设备管理")
@RequestMapping("/v2/device")
@RestController
public class DeviceManageController {

    DeviceManageService deviceService;

    public DeviceManageController(DeviceManageService deviceService) {

        this.deviceService = deviceService;
    }

    @Operation(summary = "设备 - 新增or更新")
    @PostMapping("/add-or-update")
    public ResponseDTO<?> addDevice(@RequestBody AddDeviceInputDTO inputDTO) {

        return deviceService.addDevice(inputDTO);
    }

    @Operation(summary = "设备 - 删除")
    @GetMapping("/delete/{id}")
    public ResponseDTO<?> deleteDevice(@PathVariable Long id) {

        return deviceService.deleteDevice(id);
    }

    @Operation(summary = "设备 - 查询")
    @PostMapping("/query")
    public ResponseDTO<?> queryDevice(@RequestBody QueryDeviceInputDTO inputDTO) {

        return deviceService.queryDevice(inputDTO);
    }
}
