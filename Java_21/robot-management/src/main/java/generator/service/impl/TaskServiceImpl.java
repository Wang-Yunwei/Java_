package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Task;
import generator.service.TaskService;
import generator.mapper.TaskMapper;
import org.springframework.stereotype.Service;

/**
* @author WangYunwei
* @description 针对表【mdtg_task(任务表)】的数据库操作Service实现
* @createDate 2026-04-21 10:50:25
*/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements TaskService{

}




