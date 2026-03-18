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
        return ResponseDTO.wrapSuccess(this.baseMapper.deleteById(userId) == 0 ? false : true);
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
        return ResponseDTO.wrapSuccess(this.baseMapper.updateById(user) == 0 ? false : true);
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
        return ResponseDTO.wrapSuccess(this.baseMapper.update(new LambdaUpdateWrapper<User>().eq(User::getId, inputDTO.getId()).set(User::getPassword, inputDTO.getNewPassword())) == 0 ? false : true);
    }

    /**
     * 查询用户信息
     *
     * @return DTO or List<DTO>
     */
    @Override
    public ResponseDTO<?> queryUser(QueryUserInputDTO inputDTO) {
        // 1.按ID精确查询 (返回单个用户)
        if (inputDTO != null && inputDTO.getUserId() != null) {
            User user = this.baseMapper.selectById(inputDTO.getUserId());
            if (user == null) {
                return ResponseDTO.wrapException("用户不存在");
            }
            QueryUserOutputDTO outputDTO = new QueryUserOutputDTO();
            BeanUtils.copyProperties(user, outputDTO);
            return ResponseDTO.wrapSuccess(outputDTO);
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
        List<QueryUserOutputDTO> list = this.baseMapper.selectPageDTOList(page, queryWrapper);
        return ResponseDTO.wrapSuccess(list);
    }
}




