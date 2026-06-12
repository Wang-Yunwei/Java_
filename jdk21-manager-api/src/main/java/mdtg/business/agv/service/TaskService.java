package mdtg.business.agv.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mdtg.business.agv.dto.AddTaskInputDTO;
import mdtg.business.agv.dto.QueryTaskInputDTO;
import mdtg.business.common.entity.Task;
import mdtg.business.common.ResponseDTO;

/**
 * @author WangYunwei
 */
public interface TaskService extends IService<Task> {

    ResponseDTO<?> addTask(AddTaskInputDTO inputDTO);

    ResponseDTO<?> deleteTask(Long id);

    ResponseDTO<?> queryTask(QueryTaskInputDTO inputDTO);
}
