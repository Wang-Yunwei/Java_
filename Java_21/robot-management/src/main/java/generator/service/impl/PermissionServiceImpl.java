package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Permission;
import generator.service.PermissionService;
import generator.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author WangYunwei
* @description 针对表【mdtg_permission(权限表)】的数据库操作Service实现
* @createDate 2026-03-25 13:40:57
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




