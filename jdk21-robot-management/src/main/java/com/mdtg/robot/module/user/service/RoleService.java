package com.mdtg.robot.module.user.service;

import com.mdtg.robot.common.exception.ResponseDTO;
import com.mdtg.robot.module.user.dto.AddRoleInputDTO;
import com.mdtg.robot.module.user.dto.QueryRoleInputDTO;
import com.mdtg.robot.module.user.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author WangYunwei
*/
public interface RoleService extends IService<Role> {

    ResponseDTO<?> addRole(AddRoleInputDTO inputDTO);

    ResponseDTO<?> deleteRole(String roleId);

    ResponseDTO<?> queryRole(QueryRoleInputDTO inputDTO);
}
