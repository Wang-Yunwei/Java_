package mdtg.business.agv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mdtg.business.agv.dto.AddTaskInputDTO;
import mdtg.business.agv.dto.QueryTaskInputDTO;
import mdtg.business.common.entity.Task;
import mdtg.business.agv.mapper.TaskMapper;
import mdtg.business.agv.service.TaskService;
import mdtg.business.common.ResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author WangYunwei
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {


    private final ThreadPoolTaskScheduler taskScheduler;

    // 任务注册表，存储任务编码 -> ScheduledFuture的映射
    private final Map<String, ScheduledFuture<?>> taskHolder = new ConcurrentHashMap<>();

    public TaskServiceImpl(ThreadPoolTaskScheduler taskScheduler) {

        this.taskScheduler = taskScheduler;
    }

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

    /**
     * 注册/新增任务
     */
    public void registerTask(String taskCode, String cronExpression) {
        // 如果任务已存在，先移除旧的，避免重复
        if (taskHolder.containsKey(taskCode)) {
            cancelTask(taskCode);
        }
        // 定义要执行的业务逻辑
        Runnable taskLogic = () -> {
            // 这里通过 taskCode 找到对应的业务 Bean 执行逻辑
            System.out.println("Executing task: " + taskCode + " at " + System.currentTimeMillis());
            // 推荐：使用策略模式或 switch 分发到具体的 Service 方法
        };

        // 创建触发器，动态解析 Cron 表达式
        CronTrigger trigger = new CronTrigger(cronExpression);

        // 交给调度器执行，并保存返回的 ScheduledFuture
        ScheduledFuture<?> future = taskScheduler.schedule(taskLogic, trigger);
        taskHolder.put(taskCode, future);
    }

    /**
     * 取消/移除任务
     */
    public boolean cancelTask(String taskCode) {
        ScheduledFuture<?> future = taskHolder.remove(taskCode);
        if (future != null && !future.isCancelled()) {
            return future.cancel(true); // true 表示尝试中断正在运行的任务
        }
        return false;
    }

    /**
     * 刷新任务 (常用于数据库配置变更后)
     */
    public void refreshTask(String taskCode) {
        // 从数据库获取最新配置
        Task task = this.baseMapper.selectOne(new LambdaQueryWrapper<Task>().eq(Task::getName, taskCode));
        if (task != null && task.getStatus() == 1) {
            // 重新注册，内部会先取消旧的再注册新的
            registerTask(taskCode, task.getExecuteTime());
        } else {
            // 如果任务状态是禁用或不存在，则直接取消
            cancelTask(taskCode);
        }
    }
}




