package mdtg.business.device.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.device.dto.AddDeviceInputDTO;
import mdtg.business.device.dto.QueryDeviceInputDTO;
import mdtg.business.device.entity.Device;
import mdtg.business.device.mapper.DeviceMapper;
import mdtg.business.device.service.DeviceService;
import org.springframework.stereotype.Service;

/**
 * @author WangYunwei
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    @Override
    public ResponseDTO<?> addDevice(AddDeviceInputDTO inputDTO) {

        return null;
    }

    @Override
    public ResponseDTO<?> deleteDevice(Long deviceId) {

        return null;
    }

    @Override
    public ResponseDTO<?> queryDevice(QueryDeviceInputDTO inputDTO) {

        return null;
    }
}




