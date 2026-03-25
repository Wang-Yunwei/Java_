package mdtg.business.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.user.dto.*;
import mdtg.business.user.service.PermissionService;
import mdtg.business.user.service.RoleService;
import mdtg.business.user.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangYunwei [2026-03-25]
 */
@Tag(name = "用户管理")
@RestController
public class UserManagerController {

    UserService userService;

    RoleService roleService;

    PermissionService permissionService;

    public UserManagerController(UserService userService, RoleService roleService, PermissionService permissionService) {

        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @Operation(summary = "用户-新增or更新")
    @PostMapping("/user/add-or-update")
    public ResponseDTO<?> addUser(@RequestBody AddUserInputDTO inputDTO) {

        return null;
    }

    @Operation(summary = "用户-删除")
    @PostMapping("/user/delete/{userId}")
    public ResponseDTO<?> deleteUser(@PathVariable String userId) {

        return null;
    }

    @Operation(summary = "用户-查询")
    @PostMapping("/user/query")
    public ResponseDTO<?> queryUser(@RequestBody QueryUserInputDTO inputDTO) {

        return null;
    }

    @Operation(summary = "角色-新增or更新")
    @PostMapping("/role/add-or-update")
    public ResponseDTO<?> addRole(@RequestBody AddRoleInputDTO inputDTO) {

        return roleService.addRole(inputDTO);
    }

    @Operation(summary = "角色-删除")
    @GetMapping("/role/delete/{roleId}")
    public ResponseDTO<?> deleteRole(@PathVariable String roleId) {

        return roleService.deleteRole(roleId);
    }

    @Operation(summary = "角色-查询")
    @PostMapping("/role/query")
    public ResponseDTO<?> queryRole(@RequestBody QueryRoleInputDTO inputDTO) {

        return roleService.queryRole(inputDTO);
    }

    @Operation(summary = "权限-新增or更新")
    @PostMapping("/permission/add-or-update")
    public ResponseDTO<?> addPermission(@RequestBody AddPermissionInputDTO inputDTO) {

        return permissionService.addPermission(inputDTO);
    }

    @Operation(summary = "权限-删除")
    @GetMapping("/permission/delete/{permissionId}")
    public ResponseDTO<?> deletePermission(@PathVariable String permissionId) {

        return permissionService.deletePermission(permissionId);
    }

    @Operation(summary = "权限-查询")
    @PostMapping("/permission/query")
    public ResponseDTO<?> queryPermission(@RequestBody QueryPermissionInputDTO inputDTO) {

        return permissionService.queryPermission(inputDTO);
    }
}
