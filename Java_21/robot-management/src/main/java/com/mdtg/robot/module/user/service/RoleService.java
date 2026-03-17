package com.mdtg.robot.module.user.service;

import com.mdtg.robot.common.exception.ResponseDto;
import com.mdtg.robot.module.user.dto.AddRoleInputDTO;
import com.mdtg.robot.module.user.dto.QueryRoleInputDTO;
import com.mdtg.robot.module.user.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
* @author WangYunwei
* @description 针对表【mdtg_role(角色表)】的数据库操作Service
* @createDate 2026-03-10 14:11:49
*/
public interface RoleService extends IService<Role> {

    ResponseDto<?> addRole(AddRoleInputDTO inputDTO);

    ResponseDto<?> deleteRole(String roleId);

    ResponseDto<?> queryRole(QueryRoleInputDTO inputDTO);
}
