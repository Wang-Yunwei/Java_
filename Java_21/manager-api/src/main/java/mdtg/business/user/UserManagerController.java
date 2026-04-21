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
@Tag(name = "MDTG - 用户管理")
@RestController
@RequestMapping("/v2")
public class UserManagerController {

    UserService userService;

    RoleService roleService;

    PermissionService permissionService;

    public UserManagerController(UserService userService, RoleService roleService, PermissionService permissionService) {

        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @Operation(summary = "验证Token")
    @GetMapping("/verify-token")
    public ResponseDTO<?> verifyToken(@RequestHeader("Authorization") String token) {

        return userService.verifyToken(token);
    }

    @Operation(summary = "用户 - 注册",hidden = true)
    @PostMapping("/user/register")
    public ResponseDTO<?> register(@RequestBody RegisterInputDTO inputDTO) {

        return userService.register(inputDTO);
    }

    @Operation(summary = "用户 - 更新")
    @PostMapping("/user/update")
    public ResponseDTO<?> updateUser(@RequestBody UpdateUserInputDTO inputDTO) {

        return userService.updateUser(inputDTO);
    }

    @Operation(summary = "用户 - 删除")
    @PostMapping("/user/delete/{id}")
    public ResponseDTO<?> deleteUser(@PathVariable Long id) {

        return userService.deleteUser(id);
    }

    @Operation(summary = "用户 - 查询")
    @PostMapping("/user/query")
    public ResponseDTO<?> queryUser(@RequestBody QueryUserInputDTO inputDTO) {

        return userService.queryUser(inputDTO);
    }

    @Operation(summary = "角色 - 新增or更新")
    @PostMapping("/role/add-or-update")
    public ResponseDTO<?> addRole(@RequestBody AddRoleInputDTO inputDTO) {

        return roleService.addRole(inputDTO);
    }

    @Operation(summary = "角色 - 删除")
    @GetMapping("/role/delete/{roleId}")
    public ResponseDTO<?> deleteRole(@PathVariable String roleId) {

        return roleService.deleteRole(roleId);
    }

    @Operation(summary = "角色 - 查询")
    @PostMapping("/role/query")
    public ResponseDTO<?> queryRole(@RequestBody QueryRoleInputDTO inputDTO) {

        return roleService.queryRole(inputDTO);
    }

    @Operation(summary = "权限 - 新增or更新")
    @PostMapping("/permission/add-or-update")
    public ResponseDTO<?> addPermission(@RequestBody AddPermissionInputDTO inputDTO) {

        return permissionService.addPermission(inputDTO);
    }

    @Operation(summary = "权限 - 删除")
    @GetMapping("/permission/delete/{permissionId}")
    public ResponseDTO<?> deletePermission(@PathVariable String permissionId) {

        return permissionService.deletePermission(permissionId);
    }

    @Operation(summary = "权限 - 查询")
    @PostMapping("/permission/query")
    public ResponseDTO<?> queryPermission(@RequestBody QueryPermissionInputDTO inputDTO) {

        return permissionService.queryPermission(inputDTO);
    }
}
