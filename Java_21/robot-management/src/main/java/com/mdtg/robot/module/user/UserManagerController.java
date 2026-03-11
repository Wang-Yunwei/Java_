package com.mdtg.robot.module.user;

import com.mdtg.robot.common.exception.ResponseDto;
import com.mdtg.robot.module.user.entity.User;
import com.mdtg.robot.module.user.service.PermissionService;
import com.mdtg.robot.module.user.service.RoleService;
import com.mdtg.robot.module.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangYunwei [2026-03-09]
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

    @Operation(summary = "登录")
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            // 执行登录，会自动调用 Realm 中的 doGetAuthenticationInfo 方法
            subject.login(token);
            return "登录成功";
        } catch (UnknownAccountException e) {
            return "用户不存在";
        } catch (IncorrectCredentialsException e) {
            return "密码错误";
        } catch (Exception e) {
            return "登录失败：" + e.getMessage();
        }
    }

    @Operation(summary = "登出")
    @GetMapping("/logout")
    public ResponseDto<String> logout() {
        SecurityUtils.getSubject().logout();
        return ResponseDto.wrapSuccess("登出成功!");
    }

    @PostMapping(name = "注册用户信息", path = "/user/register-or-update")
    public ResponseDto<String> register(@RequestBody User user) {

        return ResponseDto.wrapSuccess();
    }


    @PostMapping(name = "", path = "/addOr")
    public ResponseDto<String> userSaveOrUpdate() {
        return ResponseDto.wrapSuccess();
    }


}
