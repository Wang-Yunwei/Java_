package mdtg.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mdtg.business.common.ResponseDTO;
import mdtg.business.user.dto.QueryUserInputDTO;
import mdtg.business.user.dto.RegisterInputDTO;
import mdtg.business.user.dto.UpdateUserInputDTO;
import mdtg.business.user.entity.User;

/**
 * @author WangYunwei
 */
public interface UserService extends IService<User> {

    ResponseDTO<?> verifyToken(String token);

    ResponseDTO<?> register(RegisterInputDTO inputDTO);

    ResponseDTO<?> updateUser(UpdateUserInputDTO inputDTO);

    ResponseDTO<?> deleteUser(Long id);

    ResponseDTO<?> queryUser(QueryUserInputDTO inputDTO);
}
