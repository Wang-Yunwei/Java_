package mdtg.business.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.device.dto.AddDeviceInputDTO;
import mdtg.business.device.dto.QueryDeviceInputDTO;
import mdtg.business.device.entity.Device;
import mdtg.business.device.mapper.DeviceMapper;
import mdtg.business.device.service.DeviceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author WangYunwei
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    @Override
    public ResponseDTO<?> addDevice(AddDeviceInputDTO inputDTO) {

        assert inputDTO != null : "输入参数不能为空!";
        Device device = new Device();
        BeanUtils.copyProperties(inputDTO, device);
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            return this.baseMapper.updateById(device) > 0 ? ResponseDTO.wrapSuccess("更新设备成功!") : ResponseDTO.wrapException("更新设备失败!");
        }
        return this.baseMapper.insert(device) > 0 ? ResponseDTO.wrapSuccess("添加设备成功!") : ResponseDTO.wrapException("添加设备失败!");
    }

    @Override
    public ResponseDTO<?> deleteDevice(Long deviceId) {

        return this.baseMapper.deleteById(deviceId) > 0 ? ResponseDTO.wrapSuccess("删除设备成功!") : ResponseDTO.wrapException("删除设备失败!");
    }

    @Override
    public ResponseDTO<?> queryDevice(QueryDeviceInputDTO inputDTO) {

        assert inputDTO != null : "输入参数不能为空!";
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            return ResponseDTO.wrapSuccess(this.baseMapper.selectById(inputDTO.getId()));
        }
        LambdaQueryWrapper<Device> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(inputDTO.getAlias()).ifPresent(alias -> queryWrapper.eq(Device::getAlias, alias));
        Optional.ofNullable(inputDTO.getMacAddress()).ifPresent(macAddress -> queryWrapper.eq(Device::getMacAddress, macAddress));
        Optional.ofNullable(inputDTO.getBoard()).ifPresent(board -> queryWrapper.eq(Device::getBoard, board));
        Optional.ofNullable(inputDTO.getFirmwareVersion()).ifPresent(firmwareVersion -> queryWrapper.eq(Device::getFirmwareVersion, firmwareVersion));
        Optional.ofNullable(inputDTO.getLastConnectionTime()).ifPresent(lastConnectionTime -> queryWrapper.eq(Device::getLastConnectionTime, lastConnectionTime));
        Optional.ofNullable(inputDTO.getAutoUpdate()).ifPresent(autoUpdate -> queryWrapper.eq(Device::getAutoUpdate, autoUpdate));
        Optional.ofNullable(inputDTO.getType()).ifPresent(type -> queryWrapper.eq(Device::getType, type));
        Optional.ofNullable(inputDTO.getDeviceId()).ifPresent(deviceId -> queryWrapper.eq(Device::getBindingId, deviceId));
        Page<Device> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        return ResponseDTO.wrapSuccess(this.baseMapper.selectPage(page, queryWrapper));
    }
}




