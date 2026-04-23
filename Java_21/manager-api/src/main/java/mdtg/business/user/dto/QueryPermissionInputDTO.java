package mdtg.business.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author WangYunwei [2026-03-17]
 */
@Getter
@Setter
public class QueryPermissionInputDTO {
    @Schema(description = "权限ID")
    private Long permissionId;

    @Schema(description = "父级ID")
    private Long parentId;

    @Schema(description = "类型(0-系统默认,1-自定义)")
    private Integer type;

    @Schema(description = "页码")
    private Long pageNum = 0L;

    @Schema(description = "页长")
    private Long pageSize = 10L;
}
