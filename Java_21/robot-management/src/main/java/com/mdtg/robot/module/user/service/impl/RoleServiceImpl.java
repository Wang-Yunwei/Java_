package com.mdtg.robot.module.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdtg.robot.module.user.entity.Role;
import com.mdtg.robot.module.user.mapper.RoleMapper;
import com.mdtg.robot.module.user.service.RoleService;
import org.springframework.stereotype.Service;

/**
* @author WangYunwei
* @description 针对表【mdtg_role(角色表)】的数据库操作Service实现
* @createDate 2026-03-10 14:11:49
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

}




