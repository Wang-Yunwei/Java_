package mdtg.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.user.dto.AddPermissionInputDTO;
import mdtg.business.user.dto.QueryPermissionInputDTO;
import mdtg.business.user.entity.Permission;
import mdtg.business.user.mapper.PermissionMapper;
import mdtg.business.user.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author WangYunwei
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public ResponseDTO<?> addPermission(AddPermissionInputDTO inputDTO) {

        assert inputDTO != null : "入参为空!";
        Permission permission = new Permission();
        BeanUtils.copyProperties(inputDTO, permission);
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            // 执行更新
            return ResponseDTO.wrapSuccess(this.baseMapper.updateById(permission));
        }
        return this.baseMapper.insert(permission) > 0 ? ResponseDTO.wrapSuccess(permission.getId()) : ResponseDTO.wrapException("新增权限失败!");
    }

    @Override
    public ResponseDTO<?> deletePermission(String permissionId) {

        return ResponseDTO.wrapSuccess(this.baseMapper.deleteById(permissionId)>0);
    }

    @Override
    public ResponseDTO<?> queryPermission(QueryPermissionInputDTO inputDTO) {

        assert inputDTO != null : "入参为空!";
        Permission permission = new Permission();
        BeanUtils.copyProperties(inputDTO, permission);
        if(inputDTO.getId()!=null && inputDTO.getId()>0){
            // 详情
            return ResponseDTO.wrapSuccess(this.baseMapper.selectById(inputDTO.getId()));
        }
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<Permission>().eq(Permission::getDeleteFlag, 0);
        Optional.ofNullable(inputDTO.getParentId()).ifPresent(parentId -> queryWrapper.eq(Permission::getParentId, parentId));
        Optional.ofNullable(inputDTO.getType()).ifPresent(type -> queryWrapper.eq(Permission::getType, type));
        IPage<Permission> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        return ResponseDTO.wrapSuccess(this.baseMapper.selectPage(page, queryWrapper));
    }
}




