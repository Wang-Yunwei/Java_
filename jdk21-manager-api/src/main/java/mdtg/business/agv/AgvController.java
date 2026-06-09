package mdtg.business.agv;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mdtg.business.agv.dto.*;
import mdtg.business.agv.service.MapService;
import mdtg.business.agv.service.TaskService;
import mdtg.business.common.MQClient;
import mdtg.business.common.ResponseDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangYunwei [2026-04-16]
 */
@Tag(name = "MDTG - AGV管理")
@RequestMapping("/v2/agv")
@RestController
public class AgvController {

    private final MapService mapService;

    private final TaskService taskService;

    public AgvController(MapService mapService, TaskService taskService) {

        this.mapService = mapService;
        this.taskService = taskService;
    }

    @Operation(summary = "地图 - 新增or更新")
    @PostMapping("/add-map")
    public ResponseDTO<?> addMap(@RequestBody AddMapInputDTO inputDTO) {

        return mapService.addMap(inputDTO);
    }

    @Operation(summary = "地图 - 删除")
    @GetMapping("/delete-map/{id}")
    public ResponseDTO<?> deleteMap(@PathVariable Long id) {

        return mapService.deleteMap(id);
    }

    @Operation(summary = "地图 - 查询")
    @PostMapping("/query-map")
    public ResponseDTO<?> queryMap(@RequestBody QueryMapInputDTO inputDTO) {

        return mapService.queryMap(inputDTO);
    }

    @Operation(summary = "任务 - 新增or更新")
    @PostMapping("/add-task")
    public ResponseDTO<?> addTask(@RequestBody AddTaskInputDTO inputDTO) {

        return taskService.addTask(inputDTO);
    }

    @Operation(summary = "任务 - 删除")
    @GetMapping("/delete-task/{id}")
    public ResponseDTO<?> deleteTask(@PathVariable Long id) {

        return taskService.deleteTask(id);
    }

    @Operation(summary = "任务 - 查询")
    @PostMapping("/query-task")
    public ResponseDTO<?> queryTask(@RequestBody QueryTaskInputDTO inputDTO) {

        return taskService.queryTask(inputDTO);
    }

    @Operation(summary = "任务 - 执行")
    @GetMapping("/execute-task")
    public ResponseDTO<?> executeTask(@RequestParam String pointName) {

        return ResponseDTO.wrapSuccess();
    }

    @Operation(summary = "AGV - 获取当前坐标点")
    @GetMapping("/current-point")
    public ResponseDTO<?> getCurrentPoint(@RequestParam String macAddress) {

        return ResponseDTO.wrapSuccess(MQClient.CURRENT_POINT_HASH_MAP.get(macAddress));
    }

    @Operation(summary = "AGV - OTA")
    @PostMapping("/ota")
    public ResponseDTO<?> ota() {

        return ResponseDTO.wrapSuccess();
    }
}
