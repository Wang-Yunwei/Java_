package mdtg.business.agv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mdtg.business.agv.dto.AddPointInputDTO;
import mdtg.business.agv.dto.QueryPointInputDTO;
import mdtg.business.agv.entity.CoordinatePoint;
import mdtg.business.agv.mapper.CoordinatePointMapper;
import mdtg.business.agv.service.CoordinatePointService;
import mdtg.business.common.ResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author WangYunwei
 */
@Service
public class CoordinatePointServiceImpl extends ServiceImpl<CoordinatePointMapper, CoordinatePoint> implements CoordinatePointService {

    @Override
    public ResponseDTO<?> addPoint(AddPointInputDTO inputDTO) {

        CoordinatePoint coordinatePoint = new CoordinatePoint();
        BeanUtils.copyProperties(inputDTO, coordinatePoint);
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            // 执行更新
            this.baseMapper.updateById(coordinatePoint);
        }
        this.baseMapper.insert(coordinatePoint);
        return ResponseDTO.wrapSuccess();
    }

    @Override
    public ResponseDTO<?> deletePoint(Long id) {

        return ResponseDTO.wrapSuccess(this.baseMapper.deleteById(id));
    }

    @Override
    public ResponseDTO<?> queryPoint(QueryPointInputDTO inputDTO) {

        LambdaQueryWrapper<CoordinatePoint> queryWrapper = new LambdaQueryWrapper<CoordinatePoint>().eq(CoordinatePoint::getDeleteFlag, 0);
        Optional.ofNullable(inputDTO.getPointId()).ifPresent(pointId -> queryWrapper.eq(CoordinatePoint::getId, pointId));
        Optional.ofNullable(inputDTO.getName()).ifPresent(name -> queryWrapper.like(CoordinatePoint::getName, name));
        Optional.ofNullable(inputDTO.getType()).ifPresent(type -> queryWrapper.like(CoordinatePoint::getType, type));
        Optional.ofNullable(inputDTO.getMacAddress()).ifPresent(macAddress -> queryWrapper.eq(CoordinatePoint::getMacAddress, macAddress));
        Optional.ofNullable(inputDTO.getMapId()).ifPresent(mapId -> queryWrapper.eq(CoordinatePoint::getMapId, mapId));
        IPage<CoordinatePoint> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        return ResponseDTO.wrapSuccess(this.baseMapper.selectList(page, queryWrapper));
    }
}




