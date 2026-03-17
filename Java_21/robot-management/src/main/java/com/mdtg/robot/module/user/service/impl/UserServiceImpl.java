package com.mdtg.robot.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdtg.robot.common.exception.BusinessException;
import com.mdtg.robot.common.exception.ResponseDTO;
import com.mdtg.robot.module.user.dto.ChangePasswordInputDTO;
import com.mdtg.robot.module.user.dto.QueryUserInputDTO;
import com.mdtg.robot.module.user.dto.RegisterInputDTO;
import com.mdtg.robot.module.user.dto.UpdateInputDTO;
import com.mdtg.robot.module.user.entity.User;
import com.mdtg.robot.module.user.mapper.UserMapper;
import com.mdtg.robot.module.user.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.lang.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author WangYunwei
 * @description 针对表【mdtg_user(用户表)】的数据库操作Service实现
 * @createDate 2026-03-10 14:11:49
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 生成加密后的密码
     *
     * @param password 明文密码
     * @param salt     盐值
     * @return 密文
     */
    @Override
    public String encryptPassword(String password, String salt) {
        // 参数：算法、明文、盐、迭代次数
        SimpleHash hash = new SimpleHash("SHA-256", password, ByteSource.Util.bytes(salt), 1024);
        return hash.toHex(); // 转为 16 进制字符串存储
    }

    /**
     * 通过手机号获取用户信息
     */
    @Override
    public User getUserByPhone(String phone) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
    }

    /**
     * 注册用户
     *
     * @return userID
     */
    @Override
    public ResponseDTO<?> registerUser(RegisterInputDTO inputDTO) {

        Long cou = this.baseMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getPhone, inputDTO.getPhone()));
        if (cou > 0) {
            return ResponseDTO.wrapException("该账户已近存在!");
        }
        User user = new User();
        BeanUtils.copyProperties(inputDTO, user);
        int result = this.baseMapper.insert(user);
        if (result == 1) {
            return ResponseDTO.wrapSuccess(Long.toString(user.getId()));
        }
        throw new BusinessException("注册失败!");
    }

    /**
     * 删除用户
     *
     * @return Boolean
     */
    @Override
    public ResponseDTO<?> deleteUser(String userId) {
        return null;
    }

    /**
     * 更新用户信息
     *
     * @return Boolean
     */
    @Override
    public ResponseDTO<?> updateUser(UpdateInputDTO inputDTO) {
        return null;
    }

    /**
     * 修改密码
     *
     * @return Boolean
     */
    @Override
    public ResponseDTO<?> changePassword(ChangePasswordInputDTO inputDTO) {
        return null;
    }

    /**
     * 查询用户信息
     *
     * @return DTO
     */
    @Override
    public ResponseDTO<?> queryUser(QueryUserInputDTO inputDTO) {
        return null;
    }
}




