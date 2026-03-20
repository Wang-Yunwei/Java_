package com.mdtg.robot.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdtg.robot.common.exception.ResponseDTO;
import com.mdtg.robot.module.user.config.JwtUtil;
import com.mdtg.robot.module.user.dto.*;
import com.mdtg.robot.module.user.entity.User;
import com.mdtg.robot.module.user.mapper.UserMapper;
import com.mdtg.robot.module.user.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author WangYunwei
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private JwtUtil jwtUtil;

    /**
     * 验证 Token
     *
     * @return DTO
     */
    @Override
    public ResponseDTO<?> verifyToken(VerifyTokenInputDTO inputDTO) {

        VerifyTokenOutputDTO outputDTO = new VerifyTokenOutputDTO();
        try {
            jwtUtil.isTokenExpired(inputDTO.getToken());
        } catch (ExpiredJwtException e) {
            // 如果抛出这个异常，说明确实过期了
            outputDTO.setValid(false);
            outputDTO.setMessage("该Token已经过期!");
            return ResponseDTO.wrapSuccess(outputDTO);
        } catch (JwtException e) {
            // 其他JWT异常(签名错误、格式错误等)
            outputDTO.setValid(false);
            outputDTO.setMessage("Token验证失败(签名错误、格式错误)");
            return ResponseDTO.wrapSuccess(outputDTO);
        }
        String phone = jwtUtil.getClaimFiled(inputDTO.getToken(), "phone");
        User user = this.baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
        BeanUtils.copyProperties(user, outputDTO);
        outputDTO.setValid(true);
        return ResponseDTO.wrapSuccess(outputDTO);
    }

    /**
     * 注册用户
     *
     * @return userID
     */
    @Override
    public ResponseDTO<?> registerUser(RegisterInputDTO inputDTO) {

        Long count = this.baseMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getPhone, inputDTO.getPhone()));
        if (count > 0) {
            return ResponseDTO.wrapException("该账户已存在!");
        }
        User user = new User();
        BeanUtils.copyProperties(inputDTO, user);
        return this.baseMapper.insert(user) != 0 ? ResponseDTO.wrapSuccess(user.getId()) : ResponseDTO.wrapException("注册失败!");
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
        Optional.ofNullable(inputDTO.getPhone())
                .ifPresent(phone -> queryWrapper.eq(User::getPhone, phone));
        Optional.ofNullable(inputDTO.getAddress())
                .ifPresent(address -> queryWrapper.eq(User::getAddress, address));
        IPage<User> userIPage = this.baseMapper.selectPage(page, queryWrapper);
        return ResponseDTO.wrapSuccess(userIPage);
    }
}




