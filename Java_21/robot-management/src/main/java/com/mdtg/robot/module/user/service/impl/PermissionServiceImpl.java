package com.mdtg.robot.module.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdtg.robot.common.exception.ResponseDTO;
import com.mdtg.robot.module.user.dto.AddPermissionInputDTO;
import com.mdtg.robot.module.user.dto.QueryPermissionInputDTO;
import com.mdtg.robot.module.user.entity.Permission;
import com.mdtg.robot.module.user.mapper.PermissionMapper;
import com.mdtg.robot.module.user.service.PermissionService;
import org.springframework.stereotype.Service;

/**
* @author WangYunwei
* @description 针对表【mdtg_permission(权限表)】的数据库操作Service实现
* @createDate 2026-03-10 14:11:49
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public ResponseDTO<?> addPermission(AddPermissionInputDTO inputDTO) {
        return null;
    }

    @Override
    public ResponseDTO<?> deletePermission(String permissionId) {
        return null;
    }

    @Override
    public ResponseDTO<?> queryPermission(QueryPermissionInputDTO inputDTO) {
        return null;
    }
}




