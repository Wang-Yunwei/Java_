package com.mdtg.robot.module.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdtg.robot.common.exception.ResponseDTO;
import com.mdtg.robot.module.user.dto.*;
import com.mdtg.robot.module.user.entity.User;

/**
 * @author WangYunwei
 */
public interface UserService extends IService<User> {

    /**
     * 验证 Token
     * @return DTO
     */
    ResponseDTO<?> verifyToken(VerifyTokenInputDTO inputDTO);

    /**
     * 注册用户
     * @return userID
     */
    ResponseDTO<?> registerUser(RegisterInputDTO inputDTO);

    /**
     * 删除用户
     * @return Boolean
     */
    ResponseDTO<?> deleteUser(String userId);

    /**
     * 更新用户信息
     * @return Boolean
     */
    ResponseDTO<?> updateUser(UpdateUserInputDTO inputDTO);

    /**
     * 修改密码
     * @return Boolean
     */
    ResponseDTO<?> changePassword(ResetPasswordInputDTO inputDTO);

    /**
     * 查询用户信息
     * @return DTO
     */
    ResponseDTO<?> queryUser(QueryUserInputDTO inputDTO);
}
