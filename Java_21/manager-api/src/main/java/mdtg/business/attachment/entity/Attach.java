package mdtg.business.attachment.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 附件表
 */
@Getter
@Setter
@TableName(value = "mdtg_attach")
public class Attach {

    /**
     * 主键
     */
    @Schema(description = "附件Id",example = "1234567890123456789")
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 业务关联Id: 声音克隆Id、知识库Id
     */
    @Schema(description = "业务关联Id: 声音克隆Id、知识库Id",example = "08f83ae903d9899a61de044d6b2dc69b")
    private String businessId;

    /**
     * 附件业务类别: 0-声音克隆,1-知识库,2-...
     */
    @Schema(description = "附件业务类别: 0-声音克隆,1-知识库,2-...",example = "0")
    private Integer businessType;

    /**
     * 附件类型:audio/way
     */
    @Schema(description = "附件类型",example = "audio/wav")
    private String contentType;

    /**
     * 附件名称
     */
    @Schema(description = "附件名称",example = "my_voice.wav")
    private String fileName;

    /**
     * 文件大小
     */
    @Schema(description = "文件大小，单位字节",example = "1024000")
    private Long fileSize;

    /**
     * minio的key
     */
    @Schema(description = "minio的key",example = "attach/2026/03/26/1234567890123456789.wav")
    private String objectName;

    /**
     * 训练状态(0-审核中,1-待付费,2-训练中,3-训练成功,4-训练失败)
     */
    @Schema(description = "训练状态(0-审核中,1-待付费,2-训练中,3-训练成功,4-训练失败)",example = "0")
    private Integer status;

    /**
     * 创建者ID
     */
    @Schema(description = "创建者ID",hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建者名字
     */
    @Schema(description = "创建者名字",hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private String createName;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间",hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
}