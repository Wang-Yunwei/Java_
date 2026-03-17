package com.mdtg.robot.module.user.service;

import com.mdtg.robot.common.exception.ResponseDTO;
import com.mdtg.robot.module.user.dto.AddRoleInputDTO;
import com.mdtg.robot.module.user.dto.QueryRoleInputDTO;
import com.mdtg.robot.module.user.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author WangYunwei
* @description 针对表【mdtg_role(角色表)】的数据库操作Service
* @createDate 2026-03-10 14:11:49
*/
public interface RoleService extends IService<Role> {

    ResponseDTO<?> addRole(AddRoleInputDTO inputDTO);

    ResponseDTO<?> deleteRole(String roleId);

    ResponseDTO<?> queryRole(QueryRoleInputDTO inputDTO);
}
