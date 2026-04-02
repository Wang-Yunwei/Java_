package mdtg.business.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.device.dto.AddDeviceInputDTO;
import mdtg.business.device.dto.QueryDeviceInputDTO;
import mdtg.business.device.entity.Device;

/**
 * @author WangYunwei
 */
public interface DeviceManageService extends IService<Device> {

    ResponseDTO<?> addDevice(AddDeviceInputDTO inputDTO);

    ResponseDTO<?> deleteDevice(Long deviceId);

    ResponseDTO<?> queryDevice(QueryDeviceInputDTO inputDTO);
}
