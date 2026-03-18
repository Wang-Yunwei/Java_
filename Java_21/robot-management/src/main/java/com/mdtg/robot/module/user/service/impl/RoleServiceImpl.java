package com.mdtg.robot.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdtg.robot.common.exception.ResponseDTO;
import com.mdtg.robot.module.user.dto.AddRoleInputDTO;
import com.mdtg.robot.module.user.dto.QueryRoleInputDTO;
import com.mdtg.robot.module.user.entity.Role;
import com.mdtg.robot.module.user.mapper.RoleMapper;
import com.mdtg.robot.module.user.service.RoleService;
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
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            // 1.执行更新(存在角色ID)
            LambdaUpdateWrapper<Role> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Role::getId, inputDTO.getId());
            Optional.ofNullable(inputDTO.getCode()).ifPresent(code -> updateWrapper.set(Role::getCode, code));
            Optional.ofNullable(inputDTO.getDescription()).ifPresent(desc -> updateWrapper.set(Role::getDescription, desc));
            Optional.ofNullable(inputDTO.getPermissionIds()).ifPresent(pers -> updateWrapper.set(Role::getPermissionIds, pers));
            Optional.ofNullable(inputDTO.getType()).ifPresent(type -> updateWrapper.set(Role::getType, type));
            return ResponseDTO.wrapSuccess(this.baseMapper.update(updateWrapper) != 0);
        }
        // 2.执行新增
        Role role = new Role();
        BeanUtils.copyProperties(inputDTO, role);
        return this.baseMapper.insert(role) > 0 ? ResponseDTO.wrapSuccess(role.getId()) : ResponseDTO.wrapException("新增角色失败");
    }

    @Override
    public ResponseDTO<?> deleteRole(String roleId) {

        return this.baseMapper.deleteById(roleId) > 0 ? ResponseDTO.wrapSuccess(): ResponseDTO.wrapException("删除角色失败!");
    }

    @Override
    public ResponseDTO<?> queryRole(QueryRoleInputDTO inputDTO) {
        assert inputDTO != null : "入参为空!";
        if (inputDTO.getId()!= null && inputDTO.getId() > 0) {
            // 查详情
            return ResponseDTO.wrapSuccess(this.baseMapper.selectById(inputDTO.getId()));
        }

        return null;
    }
}




