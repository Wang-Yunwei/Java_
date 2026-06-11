package com.mdtg.robot.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        Role role = new Role();
        BeanUtils.copyProperties(inputDTO, role);
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            // 1.执行更新(存在角色ID)
            return ResponseDTO.wrapSuccess(this.baseMapper.updateById(role) != 0);
        }
        // 2.执行新增
        return this.baseMapper.insert(role) > 0 ? ResponseDTO.wrapSuccess(role.getId()) : ResponseDTO.wrapException("新增角色失败");
    }

    @Override
    public ResponseDTO<?> deleteRole(String roleId) {

        return this.baseMapper.deleteById(roleId) > 0 ? ResponseDTO.wrapSuccess() : ResponseDTO.wrapException("删除角色失败!");
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
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(inputDTO.getCode()).ifPresent(code -> queryWrapper.eq(Role::getCode, code));
        Optional.ofNullable(inputDTO.getType()).ifPresent(type -> queryWrapper.eq(Role::getType, type));
        IPage<Role> roleIPage = this.baseMapper.selectPage(page, queryWrapper);
        return ResponseDTO.wrapSuccess(roleIPage);
    }
}




