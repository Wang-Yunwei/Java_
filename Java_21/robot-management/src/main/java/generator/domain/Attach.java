package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 附件表
 * @TableName mdtg_attach
 */
@TableName(value ="mdtg_attach")
@Data
public class Attach {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 业务关联Id，比如声音克隆Id，知识库Id
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
     * 机构标识
     */
    private String orgCode;

    /**
     * 创建者ID
     */
    private Long createBy;

    /**
     * 创建者名字
     */
    private String createName;

    /**
     * 创建时间
     */
    private Date createDate;
}