package mdtg.business.agv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mdtg.business.agv.dto.AddTaskInputDTO;
import mdtg.business.agv.dto.QueryTaskInputDTO;
import mdtg.business.agv.entity.Task;
import mdtg.business.agv.mapper.TaskMapper;
import mdtg.business.agv.service.TaskService;
import mdtg.business.common.ResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author WangYunwei
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Override
    public ResponseDTO<?> addTask(AddTaskInputDTO inputDTO) {

        Task task = new Task();
        BeanUtils.copyProperties(inputDTO, task);
        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            return ResponseDTO.wrapSuccess(this.baseMapper.updateById(task));
        }
        return ResponseDTO.wrapSuccess(this.baseMapper.insert(task));
    }

    @Override
    public ResponseDTO<?> deleteTask(Long id) {

        return ResponseDTO.wrapSuccess(this.baseMapper.deleteById(id));
    }

    @Override
    public ResponseDTO<?> queryTask(QueryTaskInputDTO inputDTO) {

        LambdaQueryWrapper<Task> queryWrapper = new LambdaQueryWrapper<Task>().eq(Task::getDeleteFlag, 0);
        Optional.ofNullable(inputDTO.getTaskId()).ifPresent(taskId -> queryWrapper.eq(Task::getId, taskId));
        Optional.ofNullable(inputDTO.getName()).ifPresent(name -> queryWrapper.like(Task::getName, name));
        Optional.ofNullable(inputDTO.getType()).ifPresent(type -> queryWrapper.eq(Task::getType, type));
        Optional.ofNullable(inputDTO.getStatus()).ifPresent(status -> queryWrapper.eq(Task::getStatus, status));
        Optional.ofNullable(inputDTO.getRemark()).ifPresent(remark -> queryWrapper.like(Task::getRemark, remark));
        Optional.ofNullable(inputDTO.getDeviceId()).ifPresent(deviceId -> queryWrapper.eq(Task::getDeviceId, deviceId));
        Optional.ofNullable(inputDTO.getMapId()).ifPresent(mapId -> queryWrapper.eq(Task::getMapId, mapId));
        IPage<Task> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        return ResponseDTO.wrapSuccess(this.baseMapper.selectPage(page, queryWrapper));
    }
}




