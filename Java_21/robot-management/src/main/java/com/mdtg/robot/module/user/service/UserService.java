package com.mdtg.robot.module.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdtg.robot.common.exception.ResponseDTO;
import com.mdtg.robot.module.user.dto.ChangePasswordInputDTO;
import com.mdtg.robot.module.user.dto.QueryUserInputDTO;
import com.mdtg.robot.module.user.dto.RegisterInputDTO;
import com.mdtg.robot.module.user.dto.UpdateUserInputDTO;
import com.mdtg.robot.module.user.entity.User;

/**
 * @author WangYunwei
 * @description 针对表【mdtg_user(用户表)】的数据库操作Service
 * @createDate 2026-03-10 14:11:49
 */
public interface UserService extends IService<User> {

    /**
     * 密码加密
     */
    String encryptPassword(String password, String salt);

    /**
     * 通过手机号获取用户信息
     */
    User getUserByPhone(String phone);

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
    ResponseDTO<?> changePassword(ChangePasswordInputDTO inputDTO);

    /**
     * 查询用户信息
     * @return DTO
     */
    ResponseDTO<?> queryUser(QueryUserInputDTO inputDTO);
}
