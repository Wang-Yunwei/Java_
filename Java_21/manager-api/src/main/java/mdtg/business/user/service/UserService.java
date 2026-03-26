package mdtg.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.user.dto.UpdateUserInputDTO;
import mdtg.business.user.dto.QueryUserInputDTO;
import mdtg.business.user.dto.VerifyTokenInputDTO;
import mdtg.business.user.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author WangYunwei
 */
public interface UserService extends IService<User> {

    ResponseDTO<?> verifyToken(VerifyTokenInputDTO inputDTO);

    ResponseDTO<?> updateUser(UpdateUserInputDTO inputDTO);

    ResponseDTO<?> deleteUser( String userId);

    ResponseDTO<?> queryUser( QueryUserInputDTO inputDTO);
}
