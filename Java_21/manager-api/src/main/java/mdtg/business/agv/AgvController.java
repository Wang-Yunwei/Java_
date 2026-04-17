package mdtg.business.agv;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mdtg.business.agv.dto.AddPointInputDTO;
import mdtg.business.agv.dto.QueryPointInputDTO;
import mdtg.business.agv.service.CoordinatePointService;
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

    public AgvController(CoordinatePointService coordinatePointService) {

        this.coordinatePointService = coordinatePointService;
    }

    @Operation(summary = "坐标点 - 添加or更新")
    @PostMapping("/add-point")
    public ResponseDTO<?> addPoint(@RequestBody AddPointInputDTO inputDTO) {

        return null;
    }

    @Operation(summary = "坐标点 - 删除")
    @GetMapping("/delete-point/{id}")
    public ResponseDTO<?> deletePoint(@PathVariable Long id) {

        return null;
    }

    @Operation(summary = "坐标点 - 查询")
    @PostMapping("/query-point")
    public ResponseDTO<?> queryPoint(@RequestBody QueryPointInputDTO inputDTO) {

        return null;
    }
}
