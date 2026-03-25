package mdtg.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.user.dto.AddRoleInputDTO;
import mdtg.business.user.dto.QueryRoleInputDTO;
import mdtg.business.user.entity.Role;

/**
* @author WangYunwei
*/
public interface RoleService extends IService<Role> {

    ResponseDTO<?> addRole(AddRoleInputDTO inputDTO);

    ResponseDTO<?> deleteRole(String roleId);

    ResponseDTO<?> queryRole(QueryRoleInputDTO inputDTO);
}
