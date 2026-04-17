package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.CoordinatePoint;
import generator.service.CoordinatePointService;
import generator.mapper.CoordinatePointMapper;
import org.springframework.stereotype.Service;

/**
* @author WangYunwei
* @description 针对表【mdtg_coordinate_point(坐标点表)】的数据库操作Service实现
* @createDate 2026-04-16 15:54:43
*/
@Service
public class CoordinatePointServiceImpl extends ServiceImpl<CoordinatePointMapper, CoordinatePoint>
    implements CoordinatePointService{

}




