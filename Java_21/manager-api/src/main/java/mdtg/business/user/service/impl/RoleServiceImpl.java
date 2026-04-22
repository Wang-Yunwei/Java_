package mdtg.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.user.dto.AddRoleInputDTO;
import mdtg.business.user.dto.QueryRoleInputDTO;
import mdtg.business.user.entity.Role;
import mdtg.business.user.mapper.RoleMapper;
import mdtg.business.user.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author WangYunwei
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public ResponseDTO<?> addRole(AddRoleInputDTO inputDTO) {

        assert inputDTO != null : "入参为空!";
        Role role = new Role();
        BeanUtils.copyProperties(inputDTO, role);
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            // 1.执行更新(存在角色ID)
            return ResponseDTO.wrapSuccess(this.baseMapper.updateById(role) != 0);
        }
        // 2.执行新增
        return ResponseDTO.wrapSuccess(this.baseMapper.insert(role));
    }

    @Override
    public ResponseDTO<?> deleteRole(String roleId) {

        return ResponseDTO.wrapSuccess(this.baseMapper.deleteById(roleId));
    }

    @Override
    public ResponseDTO<?> queryRole(QueryRoleInputDTO inputDTO) {

        assert inputDTO != null : "入参为空!";
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            // 查详情
            return ResponseDTO.wrapSuccess(this.baseMapper.selectById(inputDTO.getId()));
        }
        // 查询列表
        IPage<Role> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>().eq(Role::getDeleteFlag, 0);
        Optional.ofNullable(inputDTO.getCode()).ifPresent(code -> queryWrapper.eq(Role::getCode, code));
        Optional.ofNullable(inputDTO.getType()).ifPresent(type -> queryWrapper.eq(Role::getType, type));
        return ResponseDTO.wrapSuccess(this.baseMapper.selectPage(page, queryWrapper));
    }
}




