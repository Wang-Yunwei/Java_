package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Map;
import generator.service.MapService;
import generator.mapper.MapMapper;
import org.springframework.stereotype.Service;

/**
* @author WangYunwei
* @description 针对表【mdtg_map(地图表)】的数据库操作Service实现
* @createDate 2026-04-22 16:42:46
*/
@Service
public class MapServiceImpl extends ServiceImpl<MapMapper, Map>
    implements MapService{

}




