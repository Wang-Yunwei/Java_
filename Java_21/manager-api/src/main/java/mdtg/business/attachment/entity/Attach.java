package mdtg.business.attachment.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 业务关联Id: 声音克隆Id、知识库Id
     */
    private Long businessId;

    /**
     * 附件业务类别: 0-声音克隆,1-知识库,2-...
     */
    private Integer businessType;

    /**
     * 附件类型:audio/way
     */
    private String contentType;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * minio的key
     */
    private String objectName;

    /**
     * 训练状态(0-审核中,1-待付费,2-训练中,3-训练成功,4-训练失败)
     */
    private Integer status;

    /**
     * 创建者ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建者名字
     */
    @TableField(fill = FieldFill.INSERT)
    private String createName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
}