package com.mdtg.robot.module.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdtg.robot.common.exception.ResponseDTO;
import com.mdtg.robot.module.user.dto.AddRoleInputDTO;
import com.mdtg.robot.module.user.dto.QueryRoleInputDTO;
import com.mdtg.robot.module.user.entity.Role;
import com.mdtg.robot.module.user.mapper.RoleMapper;
import com.mdtg.robot.module.user.service.RoleService;
import org.springframework.stereotype.Service;

/**
* @author WangYunwei
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public ResponseDTO<?> addRole(AddRoleInputDTO inputDTO) {
        return null;
    }

    @Override
    public ResponseDTO<?> deleteRole(String roleId) {
        return null;
    }

    @Override
    public ResponseDTO<?> queryRole(QueryRoleInputDTO inputDTO) {
        return null;
    }
}




