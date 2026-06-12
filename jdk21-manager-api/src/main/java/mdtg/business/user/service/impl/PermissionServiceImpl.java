package mdtg.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mdtg.business.common.ResponseDTO;
import mdtg.business.user.dto.AddPermissionInputDTO;
import mdtg.business.user.dto.QueryPermissionInputDTO;
import mdtg.business.common.entity.Permission;
import mdtg.business.user.mapper.PermissionMapper;
import mdtg.business.user.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author WangYunwei
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public ResponseDTO<?> addPermission(AddPermissionInputDTO inputDTO) {

        Permission permission = new Permission();
        BeanUtils.copyProperties(inputDTO, permission);
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            return ResponseDTO.wrapSuccess(this.baseMapper.updateById(permission));
        }
        return ResponseDTO.wrapSuccess(this.baseMapper.insert(permission));
    }

    @Override
    public ResponseDTO<?> deletePermission(String permissionId) {

        return ResponseDTO.wrapSuccess(this.baseMapper.deleteById(permissionId));
    }

    @Override
    public ResponseDTO<?> queryPermission(QueryPermissionInputDTO inputDTO) {

        if (inputDTO.getPermissionId() != null && inputDTO.getPermissionId() > 0) {
            // 详情
            return ResponseDTO.wrapSuccess(this.baseMapper.selectById(inputDTO.getPermissionId()));
        }
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<Permission>().eq(Permission::getDeleteFlag, 0);
        Optional.ofNullable(inputDTO.getParentId()).ifPresent(parentId -> queryWrapper.eq(Permission::getParentId, parentId));
        Optional.ofNullable(inputDTO.getType()).ifPresent(type -> queryWrapper.eq(Permission::getType, type));
        IPage<Permission> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        return ResponseDTO.wrapSuccess(this.baseMapper.selectPage(page, queryWrapper));
    }
}




