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




