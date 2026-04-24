package mdtg.business.device.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import mdtg.business.device.entity.Device;
import mdtg.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author WangYunwei
 */
@Mapper
public interface DeviceMapper extends BaseMapper<Device> {

    @Select("SELECT EXISTS ( SELECT 1 FROM customer_user WHERE id = ${ew.sqlSegment})")
    Boolean customerDeviceSelect(@Param(Constants.WRAPPER) Wrapper<SysUserEntity> queryWrapper);
}




