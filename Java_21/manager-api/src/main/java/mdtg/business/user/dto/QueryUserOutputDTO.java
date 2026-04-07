package mdtg.business.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import mdtg.business.user.entity.Role;

import java.util.Date;
import java.util.List;

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

    // 注: 必须为 Object 类型, 否则会导致数据库的Json字段无法正确映射
    @Schema(description = "角色ID列表")
    private Object roleIds;

    @Schema(description = "设备数量")
    private Long deviceCount;

    @Schema(description = "创建时间")
    private Date createDate;

    @Schema(description = "状态(0-锁定,1-正常)")
    private Integer status;

    @Schema(description = "组织编码")
    private String orgCode;

    @Schema(description = "组织名称")
    private String orgName;

    @Schema(description = "角色列表")
    private List<Role> roleList;
}