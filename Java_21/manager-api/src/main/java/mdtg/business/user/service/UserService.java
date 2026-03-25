package mdtg.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.user.dto.*;
import mdtg.business.user.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author WangYunwei
 */
public interface UserService extends IService<User> {

    ResponseDTO<?> addUser(AddUserInputDTO inputDTO);

    ResponseDTO<?> deleteUser( String userId);

    ResponseDTO<?> queryUser( QueryUserInputDTO inputDTO);
}
