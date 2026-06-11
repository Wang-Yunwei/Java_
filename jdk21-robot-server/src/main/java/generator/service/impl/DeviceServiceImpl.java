package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Device;
import generator.service.DeviceService;
import generator.mapper.DeviceMapper;
import org.springframework.stereotype.Service;

/**
* @author WangYunwei
* @description 针对表【mdtg_device(设备信息表)】的数据库操作Service实现
* @createDate 2026-04-01 16:12:45
*/
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device>
    implements DeviceService{

}




