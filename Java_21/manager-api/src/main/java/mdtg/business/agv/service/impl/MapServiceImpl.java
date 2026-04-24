package mdtg.business.agv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mdtg.business.agv.dto.AddMapInputDTO;
import mdtg.business.agv.dto.QueryMapInputDTO;
import mdtg.business.agv.entity.Map;
import mdtg.business.agv.mapper.MapMapper;
import mdtg.business.agv.service.MapService;
import mdtg.business.common.toolkits.ResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author WangYunwei
 */
@Service
public class MapServiceImpl extends ServiceImpl<MapMapper, Map> implements MapService {

    @Override
    public ResponseDTO<?> addMap(AddMapInputDTO inputDTO) {

        assert inputDTO != null : "参数不能为 NULL！";
        Map map = new Map();
        BeanUtils.copyProperties(inputDTO, map);
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            return ResponseDTO.wrapSuccess(this.baseMapper.updateById(map));
        }
        return ResponseDTO.wrapSuccess(this.baseMapper.insert(map));
    }

    @Override
    public ResponseDTO<?> deleteMap(Long id) {

        return ResponseDTO.wrapSuccess(this.baseMapper.deleteById(id));
    }

    @Override
    public ResponseDTO<?> queryMap(QueryMapInputDTO inputDTO) {

        assert inputDTO != null : "参数不能为 NULL！";
        LambdaQueryWrapper<Map> queryWrapper = new LambdaQueryWrapper<Map>().eq(Map::getDeleteFlag, 0);
        Optional.ofNullable(inputDTO.getMapId()).ifPresent(mapId -> queryWrapper.eq(Map::getId, mapId));
        Optional.ofNullable(inputDTO.getAlias()).ifPresent(id -> queryWrapper.eq(Map::getId, id));
        Optional.ofNullable(inputDTO.getName()).ifPresent(id -> queryWrapper.eq(Map::getId, id));
        IPage<Map> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        return ResponseDTO.wrapSuccess(this.baseMapper.selectPage(page, queryWrapper));
    }
}