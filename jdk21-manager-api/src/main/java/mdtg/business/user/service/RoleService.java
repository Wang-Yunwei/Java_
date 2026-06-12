package mdtg.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mdtg.business.common.ResponseDTO;
import mdtg.business.user.dto.AddRoleInputDTO;
import mdtg.business.user.dto.QueryRoleInputDTO;
import mdtg.business.common.entity.Role;

/**
 * @author WangYunwei
 */
public interface RoleService extends IService<Role> {

    ResponseDTO<?> addRole(AddRoleInputDTO inputDTO);

    ResponseDTO<?> deleteRole(String roleId);

    ResponseDTO<?> queryRole(QueryRoleInputDTO inputDTO);
}
