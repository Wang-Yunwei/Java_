package mdtg.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.user.dto.AddUserInputDTO;
import mdtg.business.user.dto.QueryUserInputDTO;
import mdtg.business.user.entity.User;
import mdtg.business.user.mapper.UserMapper;
import mdtg.business.user.service.UserService;
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

    @Override
    public ResponseDTO<?> addUser(AddUserInputDTO inputDTO) {

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
        if (inputDTO.getUserId() != null && inputDTO.getUserId() > 0) {
            return ResponseDTO.wrapSuccess(this.baseMapper.selectById(inputDTO.getUserId()));
        }
        if (inputDTO.getPhone() != null && !inputDTO.getPhone().isEmpty()) {
            return ResponseDTO.wrapSuccess(this.baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, inputDTO.getPhone())));
        }
        IPage<User> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(inputDTO.getUserName()).ifPresent(username -> queryWrapper.like(User::getUsername, username));
        Optional.ofNullable(inputDTO.getAddress()).ifPresent(address -> queryWrapper.like(User::getAddress, address));
        return ResponseDTO.wrapSuccess(this.baseMapper.selectList(page, queryWrapper));
    }
}




