package com.mdtg.robot.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdtg.robot.common.exception.ResponseDTO;
import com.mdtg.robot.module.user.dto.AddPermissionInputDTO;
import com.mdtg.robot.module.user.dto.QueryPermissionInputDTO;
import com.mdtg.robot.module.user.entity.Permission;
import com.mdtg.robot.module.user.mapper.PermissionMapper;
import com.mdtg.robot.module.user.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author WangYunwei
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public ResponseDTO<?> addPermission(AddPermissionInputDTO inputDTO) {

        assert inputDTO != null : "入参为空!";
        Permission permission = new Permission();
        BeanUtils.copyProperties(inputDTO, permission);
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            // 执行更新
            return ResponseDTO.wrapSuccess(this.baseMapper.updateById(permission));
        }
        return this.baseMapper.insert(permission) > 0 ? ResponseDTO.wrapSuccess(permission.getId()) : ResponseDTO.wrapException("新增权限失败!");
    }

    @Override
    public ResponseDTO<?> deletePermission(String permissionId) {

        return ResponseDTO.wrapSuccess(this.baseMapper.deleteById(permissionId)>0);
    }

    @Override
    public ResponseDTO<?> queryPermission(QueryPermissionInputDTO inputDTO) {

        assert inputDTO != null : "入参为空!";
        Permission permission = new Permission();
        BeanUtils.copyProperties(inputDTO, permission);
        if(inputDTO.getId()!=null && inputDTO.getId()>0){
            // 详情
            return ResponseDTO.wrapSuccess(this.baseMapper.selectById(inputDTO.getId()));
        }
        Page<Permission> page = new Page<>();
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(inputDTO.getParentId()).ifPresent(parentId -> queryWrapper.eq(Permission::getParentId, parentId));
        Optional.ofNullable(inputDTO.getType()).ifPresent(type -> queryWrapper.eq(Permission::getType, type));
        return ResponseDTO.wrapSuccess(this.baseMapper.selectPage(page, queryWrapper));
    }
}




