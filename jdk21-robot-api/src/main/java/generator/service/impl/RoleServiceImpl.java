package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Role;
import generator.service.RoleService;
import generator.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author WangYunwei
* @description 针对表【mdtg_role(角色表)】的数据库操作Service实现
* @createDate 2026-03-25 13:40:57
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




