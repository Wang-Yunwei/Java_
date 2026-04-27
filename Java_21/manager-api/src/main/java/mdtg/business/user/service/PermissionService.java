package mdtg.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mdtg.business.common.ResponseDTO;
import mdtg.business.user.dto.AddPermissionInputDTO;
import mdtg.business.user.dto.QueryPermissionInputDTO;
import mdtg.business.user.entity.Permission;

/**
 * @author WangYunwei
 */
public interface PermissionService extends IService<Permission> {

    ResponseDTO<?> addPermission(AddPermissionInputDTO inputDTO);

    ResponseDTO<?> deletePermission(String permissionId);

    ResponseDTO<?> queryPermission(QueryPermissionInputDTO inputDTO);
}
