package mdtg.business.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mdtg.business.common.MQClient;
import mdtg.business.common.ResponseDTO;
import mdtg.business.device.dto.AddDeviceInputDTO;
import mdtg.business.device.dto.QueryDeviceInputDTO;
import mdtg.business.device.entity.Device;
import mdtg.business.device.mapper.DeviceMapper;
import mdtg.business.device.service.DeviceManageService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author WangYunwei
 */
@Service
public class DeviceManageServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceManageService {

    @Override
    public ResponseDTO<?> addDevice(AddDeviceInputDTO inputDTO) {

        Device device = new Device();
        BeanUtils.copyProperties(inputDTO, device);
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            return ResponseDTO.wrapSuccess(this.baseMapper.updateById(device));
        }
        if (inputDTO.getType() == 0) {
            Optional.ofNullable(inputDTO.getMacAddress()).ifPresent(inputDTO::setParentMac);
        }
        return ResponseDTO.wrapSuccess(this.baseMapper.insert(device));
    }

    @Override
    public ResponseDTO<?> deleteDevice(Long id) {

        return ResponseDTO.wrapSuccess(this.baseMapper.deleteById(id));
    }

    @Override
    public ResponseDTO<?> queryDevice(QueryDeviceInputDTO inputDTO) {

        if (inputDTO.getDeviceId() != null && inputDTO.getDeviceId() > 0) {
            return ResponseDTO.wrapSuccess(this.baseMapper.selectById(inputDTO.getDeviceId()));
        }
        LambdaQueryWrapper<Device> queryWrapper = new LambdaQueryWrapper<Device>().eq(Device::getDeleteFlag, 0);
        Optional.ofNullable(inputDTO.getAlias()).ifPresent(alias -> queryWrapper.like(Device::getAlias, alias));
        Optional.ofNullable(inputDTO.getMacAddress()).ifPresent(macAddress -> queryWrapper.like(Device::getMacAddress, macAddress));
        Optional.ofNullable(inputDTO.getBoard()).ifPresent(board -> queryWrapper.like(Device::getBoard, board));
        Optional.ofNullable(inputDTO.getFirmwareVersion()).ifPresent(firmwareVersion -> queryWrapper.eq(Device::getFirmwareVersion, firmwareVersion));
        Optional.ofNullable(inputDTO.getLastConnectionTime()).ifPresent(lastConnectionTime -> queryWrapper.eq(Device::getLastConnectionTime, lastConnectionTime));
        Optional.ofNullable(inputDTO.getAutoUpdate()).ifPresent(autoUpdate -> queryWrapper.eq(Device::getAutoUpdate, autoUpdate));
        Optional.ofNullable(inputDTO.getType()).ifPresent(type -> queryWrapper.eq(Device::getType, type));
        Optional.ofNullable(inputDTO.getParentMac()).ifPresent(parentMac -> queryWrapper.eq(Device::getParentMac, parentMac));
        // 返回在线设备Mac地址列表
        if (inputDTO.getStatus() != null && inputDTO.getStatus() == 3) {
            Optional.ofNullable(inputDTO.getOrgCode()).ifPresent(orgCode -> queryWrapper.eq(Device::getOrgCode, orgCode));
            return ResponseDTO.wrapSuccess(this.baseMapper.selectList(queryWrapper).stream().map(Device::getMacAddress).filter(MQClient.ONLINE_HASH_SET::contains).collect(Collectors.toSet()));
        }
        Page<Device> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        Page<Device> devicePage = this.baseMapper.selectPage(page, queryWrapper);
        if (!MQClient.ONLINE_HASH_SET.isEmpty()) {
            devicePage.getRecords().forEach(dto -> {
                if (MQClient.ONLINE_HASH_SET.contains(dto.getMacAddress())) {
                    dto.setStatus(3);
                }
            });
        }
        return ResponseDTO.wrapSuccess(devicePage);
    }
}




