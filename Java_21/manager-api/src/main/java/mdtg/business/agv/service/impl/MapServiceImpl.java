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
import mdtg.business.common.ResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author WangYunwei
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MapServiceImpl extends ServiceImpl<MapMapper, Map> implements MapService {

    @Override
    public ResponseDTO<?> addMap(AddMapInputDTO inputDTO) {

        Map map = new Map();
        BeanUtils.copyProperties(inputDTO, map);
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            int result = this.baseMapper.updateById(map);

            return ResponseDTO.wrapSuccess(result);
        }
        return ResponseDTO.wrapSuccess(this.baseMapper.insert(map));
    }

    @Override
    public ResponseDTO<?> deleteMap(Long id) {

        return ResponseDTO.wrapSuccess(this.baseMapper.deleteById(id));
    }

    @Override
    public ResponseDTO<?> queryMap(QueryMapInputDTO inputDTO) {

        LambdaQueryWrapper<Map> queryWrapper = new LambdaQueryWrapper<Map>().eq(Map::getDeleteFlag, 0);
        Optional.ofNullable(inputDTO.getMapId()).ifPresent(mapId -> queryWrapper.eq(Map::getId, mapId));
        Optional.ofNullable(inputDTO.getAlias()).ifPresent(alias -> queryWrapper.eq(Map::getAlias, alias));
        Optional.ofNullable(inputDTO.getName()).ifPresent(name -> queryWrapper.eq(Map::getName, name));
        Optional.ofNullable(inputDTO.getMacAddress()).ifPresent(macAddress -> queryWrapper.eq(Map::getMacAddress, macAddress));
        IPage<Map> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        return ResponseDTO.wrapSuccess(this.baseMapper.selectPage(page, queryWrapper));
    }
}