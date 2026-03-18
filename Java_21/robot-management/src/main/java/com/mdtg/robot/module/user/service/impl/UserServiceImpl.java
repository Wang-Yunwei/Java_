package com.mdtg.robot.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdtg.robot.common.exception.BusinessException;
import com.mdtg.robot.common.exception.ResponseDTO;
import com.mdtg.robot.module.user.dto.*;
import com.mdtg.robot.module.user.entity.User;
import com.mdtg.robot.module.user.mapper.UserMapper;
import com.mdtg.robot.module.user.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.lang.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author WangYunwei
 */
@Service
@Transactional
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
        return ResponseDTO.wrapSuccess(this.baseMapper.deleteById(userId) != 0);
    }

    /**
     * 更新用户信息
     *
     * @return Boolean
     */
    @Override
    public ResponseDTO<?> updateUser(UpdateUserInputDTO inputDTO) {
        User user = new User();
        BeanUtils.copyProperties(inputDTO, user);
        return ResponseDTO.wrapSuccess(this.baseMapper.updateById(user) != 0);
    }

    /**
     * 修改密码
     *
     * @return Boolean
     */
    @Override
    public ResponseDTO<?> changePassword(ChangePasswordInputDTO inputDTO) {

        // 1. 新旧密码不能相同
        if (inputDTO.getOldPassword().equals(inputDTO.getNewPassword())) {
            return ResponseDTO.wrapException("新密码不能和旧密码相同!");
        }
        return ResponseDTO.wrapSuccess(this.baseMapper.update(new LambdaUpdateWrapper<User>().eq(User::getId, inputDTO.getId()).set(User::getPassword, inputDTO.getNewPassword())) != 0);
    }

    /**
     * 查询用户信息
     *
     * @return DTO or List<DTO>
     */
    @Override
    public ResponseDTO<?> queryUser(QueryUserInputDTO inputDTO) {
        // 1.按ID精确查询 (返回单个用户)
        assert inputDTO != null : "入参为空!";
        if (inputDTO.getUserId() != null && inputDTO.getUserId() > 0) {
            User user = this.baseMapper.selectById(inputDTO.getUserId());
            return ResponseDTO.wrapSuccess(user);
        }
        // 2.按条件查询 (返回用户列表)
        IPage<User> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(inputDTO.getUserName())
                .ifPresent(username -> queryWrapper.eq(User::getUsername, username));
        Optional.ofNullable(inputDTO.getGender())
                .ifPresent(gender -> queryWrapper.eq(User::getGender, gender));
        Optional.ofNullable(inputDTO.getAddress())
                .ifPresent(address -> queryWrapper.eq(User::getAddress, address));
        IPage<User> userIPage = this.baseMapper.selectPage(page, queryWrapper);
        return ResponseDTO.wrapSuccess(userIPage);
    }
}




