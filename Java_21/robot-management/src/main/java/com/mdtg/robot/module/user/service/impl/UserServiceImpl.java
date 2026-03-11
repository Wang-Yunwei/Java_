package com.mdtg.robot.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdtg.robot.module.user.entity.User;
import com.mdtg.robot.module.user.mapper.UserMapper;
import com.mdtg.robot.module.user.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.lang.util.ByteSource;
import org.springframework.stereotype.Service;

/**
 * @author WangYunwei
 * @description 针对表【mdtg_user(用户表)】的数据库操作Service实现
 * @createDate 2026-03-10 14:11:49
 */
@Service
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
     *
     * @param phone
     */
    @Override
    public User getUserByPhone(String phone) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
    }
}




