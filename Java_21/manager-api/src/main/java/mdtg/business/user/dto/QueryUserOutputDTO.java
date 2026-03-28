package mdtg.business.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author WangYunwei [2026-03-27]
 */
@Getter
@Setter
public class QueryUserOutputDTO {

    @Schema(description = "用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "角色列表")
    private Object roleIds;

    @Schema(description = "设备数量")
    private Long deviceCount;

    @Schema(description = "创建时间")
    private Date createDate;

    @Schema(description = "状态(0-锁定,1-正常)")
    private Integer status;
}
