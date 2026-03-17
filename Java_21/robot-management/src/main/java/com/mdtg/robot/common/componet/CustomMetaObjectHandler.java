package com.mdtg.robot.common.componet;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.mdtg.robot.module.user.entity.User;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author WangYunwei [2026-03-10]
 */
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {

    public User getCurrentUser() {
        User user = new User();
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal != null && principal instanceof User) {
            user = (User) principal;
        }
        return user;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        User currentUser = getCurrentUser();
        this.strictInsertFill(metaObject, "createBy", Long.class, currentUser.getId());
        this.strictInsertFill(metaObject, "createName", String.class, currentUser.getUsername());
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateBy", Long.class, currentUser.getId());
        this.strictInsertFill(metaObject, "updateName", String.class, currentUser.getUsername());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        User currentUser = getCurrentUser();
        this.strictInsertFill(metaObject, "updateBy", Long.class, currentUser.getId());
        this.strictInsertFill(metaObject, "updateName", String.class, currentUser.getUsername());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
