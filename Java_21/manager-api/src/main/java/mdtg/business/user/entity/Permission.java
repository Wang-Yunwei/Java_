package mdtg.business.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import mdtg.business.common.toolkits.BaseEntity;

/**
 * 权限表
 */
@Getter
@Setter
@TableName(value = "mdtg_permission")
public class Permission extends BaseEntity {

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 菜单路径(user_manage:list:read)
     */
    private String menuPath;

    /**
     * 路径级别
     */
    private Integer level;

    /**
     * 插件列表(字典表ID)
     */
    private Object plugIds;

    /**
     * 类型(0-系统默认,1-自定义)
     */
    private Integer type;
}