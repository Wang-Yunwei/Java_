package mdtg.modules.security.service;

import mdtg.modules.security.entity.SysUserTokenEntity;
import mdtg.modules.sys.entity.SysUserEntity;

/**
 * shiro相关接口
 */
public interface ShiroService {

    SysUserTokenEntity getByToken(String token);

    /**
     * 根据用户ID，查询用户
     *
     * @param userId
     */
    SysUserEntity getUser(Long userId);

}
