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
import mdtg.business.common.toolkits.ResponseDTO;
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

        assert inputDTO != null : "参数不能为NULL!";
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

        assert inputDTO != null : "参数不能为NULL!";
        LambdaQueryWrapper<CoordinatePoint> queryWrapper = new LambdaQueryWrapper<CoordinatePoint>().eq(CoordinatePoint::getDeleteFlag, 0);
        Optional.ofNullable(inputDTO.getId()).ifPresent(id -> queryWrapper.eq(CoordinatePoint::getId, id));
        Optional.ofNullable(inputDTO.getName()).ifPresent(name -> queryWrapper.eq(CoordinatePoint::getName, name));
        Optional.ofNullable(inputDTO.getMapName()).ifPresent(mapName -> queryWrapper.eq(CoordinatePoint::getMapName, mapName));
        IPage<CoordinatePoint> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        return ResponseDTO.wrapSuccess(this.baseMapper.selectList(page, queryWrapper));
    }
}




