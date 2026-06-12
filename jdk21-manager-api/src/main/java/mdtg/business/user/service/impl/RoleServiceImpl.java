package mdtg.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mdtg.business.common.ResponseDTO;
import mdtg.business.user.dto.AddRoleInputDTO;
import mdtg.business.user.dto.QueryRoleInputDTO;
import mdtg.business.common.entity.Role;
import mdtg.business.user.mapper.RoleMapper;
import mdtg.business.user.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author WangYunwei
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public ResponseDTO<?> addRole(AddRoleInputDTO inputDTO) {

        Role role = new Role();
        BeanUtils.copyProperties(inputDTO, role);
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            return ResponseDTO.wrapSuccess(this.baseMapper.updateById(role) != 0);
        }
        return ResponseDTO.wrapSuccess(this.baseMapper.insert(role));
    }

    @Override
    public ResponseDTO<?> deleteRole(String roleId) {

        return ResponseDTO.wrapSuccess(this.baseMapper.deleteById(roleId));
    }

    @Override
    public ResponseDTO<?> queryRole(QueryRoleInputDTO inputDTO) {

        if (inputDTO.getRoleId() != null && inputDTO.getRoleId() > 0) {
            return ResponseDTO.wrapSuccess(this.baseMapper.selectById(inputDTO.getRoleId()));
        }
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>().eq(Role::getDeleteFlag, 0);
        Optional.ofNullable(inputDTO.getCode()).ifPresent(code -> queryWrapper.eq(Role::getCode, code));
        Optional.ofNullable(inputDTO.getType()).ifPresent(type -> queryWrapper.eq(Role::getType, type));
        IPage<Role> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        return ResponseDTO.wrapSuccess(this.baseMapper.selectPage(page, queryWrapper));
    }
}




