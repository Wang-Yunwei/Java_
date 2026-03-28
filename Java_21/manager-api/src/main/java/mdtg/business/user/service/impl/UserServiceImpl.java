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

    private final DeviceService deviceService;

    private final ShiroService shiroService;

    public UserServiceImpl(DeviceService deviceService, ShiroService shiroService) {

        this.deviceService = deviceService;
        this.shiroService = shiroService;
    }

    @Override
    public ResponseDTO<?> verifyToken(VerifyTokenInputDTO inputDTO) {

        VerifyTokenOutputDTO outputDTO = new VerifyTokenOutputDTO();
        // 根据accessToken，查询用户信息
        SysUserTokenEntity tokenEntity = shiroService.getByToken(inputDTO.getToken());
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
        outputDTO.setValid(true);
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
    public ResponseDTO<?> deleteUser(String userId) {

        return this.baseMapper.deleteById(Long.valueOf(userId)) > 0 ? ResponseDTO.wrapSuccess() : ResponseDTO.wrapException("删除用户失败!");
    }

    @Override
    public ResponseDTO<?> queryUser(QueryUserInputDTO inputDTO) {

        assert inputDTO != null : "入参为空!";
        Page<QueryUserOutputDTO> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(inputDTO.getUserId()).ifPresent(userId -> queryWrapper.eq(User::getId, userId));
        Optional.ofNullable(inputDTO.getPhone()).ifPresent(phone -> queryWrapper.eq(User::getPhone, phone));
        Optional.ofNullable(inputDTO.getUserName()).ifPresent(username -> queryWrapper.like(User::getUsername, username));
        Optional.ofNullable(inputDTO.getAddress()).ifPresent(address -> queryWrapper.like(User::getAddress, address));

        Page<QueryUserOutputDTO> dtoPage = this.baseMapper.queryUser(page, queryWrapper);
        dtoPage.getRecords().forEach(record -> {
            record.setDeviceCount(deviceService.selectCountByUserId(record.getId()));
        });
        return ResponseDTO.wrapSuccess(dtoPage);
    }
}
