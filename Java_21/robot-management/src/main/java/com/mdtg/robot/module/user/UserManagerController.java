package com.mdtg.robot.module.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdtg.robot.module.user.config.JwtUtil;
import com.mdtg.robot.common.exception.ResponseDTO;
import com.mdtg.robot.module.user.dto.*;
import com.mdtg.robot.module.user.entity.User;
import com.mdtg.robot.module.user.service.PermissionService;
import com.mdtg.robot.module.user.service.RoleService;
import com.mdtg.robot.module.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangYunwei [2026-03-09]
 */
@Tag(name = "用户管理")
@RestController
public class UserManagerController {

    UserService userService;
    RoleService roleService;
    PermissionService permissionService;
    @Resource
    private JwtUtil jwtUtil;

    public UserManagerController(UserService userService, RoleService roleService, PermissionService permissionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @Operation(summary = "登录")
    @PostMapping("/login")
    public ResponseDTO<?> login(@RequestParam String accountNumber, @RequestParam String password) {

        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, accountNumber));
        if (user == null) {
            return ResponseDTO.wrapSuccess("账户不存在");
        }
        if (!user.getPassword().equals(password)) {
            return ResponseDTO.wrapSuccess("账户或密码错误");
        }
        String token = jwtUtil.generateToken(accountNumber);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return ResponseDTO.wrapSuccess(map);
    }

    @Operation(summary = "登出")
    @GetMapping("/logout")
    public ResponseDTO<?> logout() {

        SecurityUtils.getSubject().logout();
        return ResponseDTO.wrapSuccess("登出成功!");
    }

    @Operation(summary = "用户-注册")
    @PostMapping("/user/register")
    public ResponseDTO<?> registerUser(@RequestBody RegisterInputDTO inputDTO) {

        return userService.registerUser(inputDTO);
    }

    @Operation(summary = "用户-删除")
    @GetMapping("/user/delete/{userId}")
    public ResponseDTO<?> deleteUser(@PathVariable String userId) {

        return userService.deleteUser(userId);
    }

    @Operation(summary = "用户-更新")
    @PostMapping("/user/update")
    public ResponseDTO<?> updateUser(@RequestBody UpdateUserInputDTO inputDTO) {

        return userService.updateUser(inputDTO);
    }

    @Operation(summary = "用户-修改密码")
    @PostMapping("/user/change-password")
    public ResponseDTO<?> changePassword(@RequestBody ChangePasswordInputDTO inputDTO) {

        return userService.changePassword(inputDTO);
    }

    @Operation(summary = "用户-查询")
    @PostMapping("/user/query")
    public ResponseDTO<?> queryUser(@RequestBody QueryUserInputDTO inputDTO) {

        return userService.queryUser(inputDTO);
    }

    @Operation(summary = "角色-新增or更新")
    @PostMapping("/role/add-or-update")
    public ResponseDTO<?> addRole(@RequestBody AddRoleInputDTO inputDTO) {

        return roleService.addRole(inputDTO);
    }

    @Operation(summary = "角色-删除")
    @GetMapping ("/role/delete/{roleId}")
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
    @GetMapping ("/permission/delete/{permissionId}")
    public ResponseDTO<?> deletePermission(@PathVariable String permissionId) {

        return permissionService.deletePermission(permissionId);
    }

    @Operation(summary = "权限-查询")
    @PostMapping("/permission/query")
    public ResponseDTO<?> queryPermission(@RequestBody QueryPermissionInputDTO inputDTO) {

        return permissionService.queryPermission(inputDTO);
    }

}
