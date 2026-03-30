package mdtg.business.user.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mdtg.business.user.dto.QueryUserOutputDTO;
import mdtg.business.user.entity.User;
import mdtg.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author WangYunwei
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    Page<QueryUserOutputDTO> queryUser(IPage<QueryUserOutputDTO> page, @Param(Constants.WRAPPER) Wrapper<User> queryWrapper);

    @Select("DELETE FROM `sys_user` WHERE ${ew.sqlSegment}")
    int deleteSysUser(@Param(Constants.WRAPPER) Wrapper<SysUserEntity> queryWrapper);
}




