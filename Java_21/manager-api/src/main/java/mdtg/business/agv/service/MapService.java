package mdtg.business.agv.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mdtg.business.agv.dto.AddMapInputDTO;
import mdtg.business.agv.dto.QueryMapInputDTO;
import mdtg.business.agv.entity.Map;
import mdtg.business.common.toolkits.ResponseDTO;

/**
 * @author WangYunwei
 */
public interface MapService extends IService<Map> {

    ResponseDTO<?> addMap(AddMapInputDTO inputDTO);

    ResponseDTO<?> deleteMap(Long id);

    ResponseDTO<?> queryMap(QueryMapInputDTO inputDTO);
}
