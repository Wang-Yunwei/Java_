package mdtg.business.agv;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mdtg.business.agv.dto.AddPointInputDTO;
import mdtg.business.agv.dto.AddTaskInputDTO;
import mdtg.business.agv.dto.QueryPointInputDTO;
import mdtg.business.agv.dto.QueryTaskInputDTO;
import mdtg.business.agv.service.CoordinatePointService;
import mdtg.business.agv.service.TaskService;
import mdtg.business.common.toolkits.ResponseDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangYunwei [2026-04-16]
 */
@Tag(name = "MDTG - AGV管理")
@RequestMapping("/v2/agv")
@RestController
public class AgvController {

    private final CoordinatePointService coordinatePointService;

    private final TaskService taskService;

    public AgvController(CoordinatePointService coordinatePointService, TaskService taskService) {

        this.coordinatePointService = coordinatePointService;
        this.taskService = taskService;
    }

    @Operation(summary = "坐标点 - 添加or更新")
    @PostMapping("/add-point")
    public ResponseDTO<?> addPoint(@RequestBody AddPointInputDTO inputDTO) {

        return coordinatePointService.addPoint(inputDTO);
    }

    @Operation(summary = "坐标点 - 删除")
    @GetMapping("/delete-point/{id}")
    public ResponseDTO<?> deletePoint(@PathVariable Long id) {

        return coordinatePointService.deletePoint(id);
    }

    @Operation(summary = "坐标点 - 查询")
    @PostMapping("/query-point")
    public ResponseDTO<?> queryPoint(@RequestBody QueryPointInputDTO inputDTO) {

        return coordinatePointService.queryPoint(inputDTO);
    }

    @Operation(summary = "任务 - 新增or更新")
    @PostMapping("/add-task")
    public ResponseDTO<?> addTask(@RequestBody AddTaskInputDTO inputDTO) {

        return taskService.addTask(inputDTO);
    }

    @Operation(summary = "任务 - 新增or更新")
    @GetMapping("/delete-task/{id}")
    public ResponseDTO<?> deleteTask(@PathVariable Long id) {

        return taskService.deleteTask(id);
    }

    @Operation(summary = "任务 - 新增or更新")
    @PostMapping("/query-task")
    public ResponseDTO<?> queryTask(@RequestBody QueryTaskInputDTO inputDTO) {

        return taskService.queryTask(inputDTO);
    }
}
