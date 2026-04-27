package mdtg.business.agv.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mdtg.business.agv.dto.AddPointInputDTO;
import mdtg.business.agv.dto.QueryPointInputDTO;
import mdtg.business.agv.entity.CoordinatePoint;
import mdtg.business.common.ResponseDTO;

/**
 * @author WangYunwei
 */
public interface CoordinatePointService extends IService<CoordinatePoint> {

    ResponseDTO<?> addPoint(AddPointInputDTO inputDTO);

    ResponseDTO<?> deletePoint(Long id);

    ResponseDTO<?> queryPoint(QueryPointInputDTO inputDTO);
}
