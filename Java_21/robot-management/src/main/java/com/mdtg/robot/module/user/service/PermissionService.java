package com.mdtg.robot.module.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdtg.robot.common.exception.ResponseDTO;
import com.mdtg.robot.module.user.dto.AddPermissionInputDTO;
import com.mdtg.robot.module.user.dto.QueryPermissionInputDTO;
import com.mdtg.robot.module.user.entity.Permission;

/**
 * @author WangYunwei
 * @description 针对表【mdtg_permission(权限表)】的数据库操作Service
 * @createDate 2026-03-10 14:11:49
 */
public interface PermissionService extends IService<Permission> {

    ResponseDTO<?> addPermission(AddPermissionInputDTO inputDTO);

    ResponseDTO<?> deletePermission(String permissionId);

    ResponseDTO<?> queryPermission(QueryPermissionInputDTO inputDTO);
}
