package mdtg.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.user.dto.*;
import mdtg.business.user.entity.User;
import mdtg.business.user.mapper.UserMapper;
import mdtg.business.user.service.UserService;
import mdtg.modules.device.service.DeviceService;
import mdtg.modules.security.entity.SysUserTokenEntity;
import mdtg.modules.security.service.ShiroService;
import mdtg.modules.sys.entity.SysUserEntity;
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

    private final DeviceService deviceService;

    private final ShiroService shiroService;

    public UserServiceImpl(DeviceService deviceService, ShiroService shiroService) {

        this.deviceService = deviceService;
        this.shiroService = shiroService;
    }

    @Override
    public ResponseDTO<?> verifyToken(String token) {

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // 从第 8 个字符开始截取，去掉 "Bearer "
        }
        VerifyTokenOutputDTO outputDTO = new VerifyTokenOutputDTO();
        // 根据accessToken，查询用户信息
        SysUserTokenEntity tokenEntity = shiroService.getByToken(token);
        if (tokenEntity == null) {
            outputDTO.setValid(false);
            outputDTO.setMessage("Token无效!");
            return ResponseDTO.wrapSuccess(outputDTO);
        }
        if (tokenEntity.getExpireDate() != null && tokenEntity.getExpireDate().getTime() < System.currentTimeMillis()) {
            outputDTO.setValid(false);
            outputDTO.setMessage("Token已经过期!");
            return ResponseDTO.wrapSuccess(outputDTO);
        }
        User user = this.baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getSysUserId, tokenEntity.getUserId()));
        if (user == null) {
            outputDTO.setValid(false);
            outputDTO.setMessage("未找到对应用户");
            return ResponseDTO.wrapSuccess(outputDTO);
        }
        outputDTO.setUserId(user.getId().toString());
        outputDTO.setPhone(user.getPhone());
        outputDTO.setUsername(user.getUsername());
        outputDTO.setOrgCode(user.getOrgCode());
        return ResponseDTO.wrapSuccess(outputDTO);
    }

    @Override
    public ResponseDTO<?> register(RegisterInputDTO inputDTO) {

        return null;
    }

    @Override
    public ResponseDTO<?> updateUser(UpdateUserInputDTO inputDTO) {

        assert inputDTO != null : "入参为空!";
        User user = new User();
        BeanUtils.copyProperties(inputDTO, user);
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            // 1.如果传入了id,则说明是修改用户
            return this.baseMapper.updateById(user) > 0 ? ResponseDTO.wrapSuccess() : ResponseDTO.wrapException("修改用户失败!");
        }
        // 2.如果没有传入id,则说明是新增用户
        return this.baseMapper.insert(user) > 0 ? ResponseDTO.wrapSuccess() : ResponseDTO.wrapException("新增用户失败!");
    }

    @Override
    public ResponseDTO<?> deleteUser(Long userId) {

        Long id = Long.valueOf(userId);
        User user = this.baseMapper.selectById(id);
        if (user != null) {
            int result = this.baseMapper.deleteById(user.getId());
            if (result > 0) {
                result = this.baseMapper.deleteSysUser(new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getId, user.getSysUserId()));
            }
            return result > 0 ? ResponseDTO.wrapSuccess() : ResponseDTO.wrapException("删除用户失败!");
        }
        return ResponseDTO.wrapException("删除用户失败!");
    }

    @Override
    public ResponseDTO<?> queryUser(QueryUserInputDTO inputDTO) {

        assert inputDTO != null : "入参为空!";
        Page<QueryUserOutputDTO> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getDeleteFlag, 0);
        Optional.ofNullable(inputDTO.getUserId()).ifPresent(userId -> queryWrapper.eq(User::getId, userId));
        Optional.ofNullable(inputDTO.getSysUserId()).ifPresent(sysUserId -> queryWrapper.eq(User::getSysUserId, sysUserId));
        Optional.ofNullable(inputDTO.getPhone()).ifPresent(phone -> queryWrapper.eq(User::getPhone, phone));
        Optional.ofNullable(inputDTO.getUserName()).ifPresent(username -> queryWrapper.like(User::getUsername, username));
        Optional.ofNullable(inputDTO.getAddress()).ifPresent(address -> queryWrapper.like(User::getAddress, address));

        Page<QueryUserOutputDTO> dtoPage = this.baseMapper.queryUser(page, queryWrapper);
        dtoPage.getRecords().forEach(record -> record.setDeviceCount(deviceService.selectCountByUserId(record.getId())));
        return ResponseDTO.wrapSuccess(dtoPage);
    }
}
