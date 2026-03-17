package com.mdtg.robot.module.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdtg.robot.common.config.JwtUtil;
import com.mdtg.robot.common.exception.ResponseDto;
import com.mdtg.robot.module.user.dto.RegisterInputDTO;
import com.mdtg.robot.module.user.entity.User;
import com.mdtg.robot.module.user.service.PermissionService;
import com.mdtg.robot.module.user.service.RoleService;
import com.mdtg.robot.module.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseDto<?> login(@RequestParam String accountNumber, @RequestParam String password) {

        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, accountNumber));
        if (user == null) {
            return ResponseDto.wrapSuccess("账户不存在");
        }
        if (!user.getPassword().equals(password)) {
            return ResponseDto.wrapSuccess("账户或密码错误");
        }
        String token = jwtUtil.generateToken(accountNumber);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return ResponseDto.wrapSuccess(map);
    }

    @Operation(summary = "登出")
    @GetMapping("/logout")
    public ResponseDto<?> logout() {
        SecurityUtils.getSubject().logout();
        return ResponseDto.wrapSuccess("登出成功!");
    }

    @Operation(summary = "注册")
    @PostMapping("/user/register")
    public ResponseDto<?> register(@RequestBody RegisterInputDTO inputDTO) {

        return userService.register(inputDTO);
    }


    public ResponseDto<?> update(User user) {
        return null;
    }
}
