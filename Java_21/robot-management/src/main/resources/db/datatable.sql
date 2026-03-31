-- 用户
DROP TABLE IF EXISTS `mdtg_user`;
CREATE TABLE `mdtg_user` (
    `id`              BIGINT       NOT NULL         COMMENT '主键',
    `username`        VARCHAR(50)  NOT NULL         COMMENT '用户名',
    `password`        VARCHAR(100) NOT NULL         COMMENT '密码',
    `gender`          TINYINT      DEFAULT 0        COMMENT '性别(0-未知,1-男,2-女)',
    `identity_card`   VARCHAR(50)  DEFAULT NULL     COMMENT '居民身份证',
    `phone`           VARCHAR(20)  NOT NULL         COMMENT '手机号',
    `email`           VARCHAR(20)  DEFAULT NULL     COMMENT '邮箱',
    `address`         VARCHAR(300) DEFAULT NULL     COMMENT '住址',
    `role_ids`        JSON         DEFAULT NULL     COMMENT '角色列表',
    `type`            TINYINT      DEFAULT 0        COMMENT '类型(0-系统默认,1-自定义)',
    `status`          TINYINT      DEFAULT 0        COMMENT '状态(0-正常,1-锁定)',
    `sys_user_id`     BIGINT       NOT NULL         COMMENT '状态(0-正常,1-锁定)',
    `update_by`       BIGINT       DEFAULT NULL     COMMENT '更新者ID',
    `update_name`     VARCHAR(50)  DEFAULT NULL     COMMENT '更新者名',
    `update_date`     DATETIME     DEFAULT NOW()    COMMENT '更新时间',
    `create_by`       BIGINT       DEFAULT NULL     COMMENT '创建者ID',
    `create_name`     VARCHAR(50)  DEFAULT NULL     COMMENT '创建者名',
    `create_date`     DATETIME     DEFAULT NOW()    COMMENT '创建时间',
    `company_code`    VARCHAR(50)  DEFAULT NULL     COMMENT '单位编码',
    `company_name`    VARCHAR(100) DEFAULT NULL     COMMENT '单位简称',
    `second_org_code` VARCHAR(50)  DEFAULT NULL     COMMENT '二级组织编码',
    `second_org_name` VARCHAR(100) DEFAULT NULL     COMMENT '二级组织简称',
    `org_code`        VARCHAR(50)  DEFAULT NULL     COMMENT '组织编码',
    `org_name`        VARCHAR(100) DEFAULT NULL     COMMENT '组织简称',
    `delete_flag`     TINYINT      DEFAULT 0        COMMENT '删除标识(0-未删除,1-已删除)',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_mdtg_user_phone` (`phone`) COMMENT '创建手机号唯一索引',
    UNIQUE KEY `udx_mdtg_user_sys_user_id` (`sys_user_id`) COMMENT '创建系统用户ID唯一索引'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

-- 角色
DROP TABLE IF EXISTS `mdtg_role`;
CREATE TABLE `mdtg_role` (
    `id`              BIGINT       NOT NULL         COMMENT '主键',
    `code`            VARCHAR(50)  DEFAULT NULL     COMMENT '角色编码(如: admin)',
    `description`     VARCHAR(200) DEFAULT NULL     COMMENT '描述',
    `permission_ids`  JSON         DEFAULT NULL     COMMENT '权限列表',
    `type`            TINYINT      DEFAULT 0        COMMENT '类型(0-系统默认,1-自定义)',
    `update_by`       BIGINT       DEFAULT NULL     COMMENT '更新者ID',
    `update_name`     VARCHAR(50)  DEFAULT NULL     COMMENT '更新者名',
    `update_date`     DATETIME     DEFAULT NOW()    COMMENT '更新时间',
    `create_by`       BIGINT       DEFAULT NULL     COMMENT '创建者ID',
    `create_name`     VARCHAR(50)  DEFAULT NULL     COMMENT '创建者名',
    `create_date`     DATETIME     DEFAULT NOW()    COMMENT '创建时间',
    `company_code`    VARCHAR(50)  DEFAULT NULL     COMMENT '单位编码',
    `company_name`    VARCHAR(100) DEFAULT NULL     COMMENT '单位简称',
    `second_org_code` VARCHAR(50)  DEFAULT NULL     COMMENT '二级组织编码',
    `second_org_name` VARCHAR(100) DEFAULT NULL     COMMENT '二级组织简称',
    `org_code`        VARCHAR(50)  DEFAULT NULL     COMMENT '组织编码',
    `org_name`        VARCHAR(100) DEFAULT NULL     COMMENT '组织简称',
    `delete_flag`     TINYINT      DEFAULT 0        COMMENT '删除标识(0-未删除,1-已删除)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';

-- 权限
DROP TABLE IF EXISTS `mdtg_permission`;
CREATE TABLE `mdtg_permission` (
    `id`              BIGINT       NOT NULL         COMMENT '主键',
    `parent_id`       BIGINT       DEFAULT NULL     COMMENT '父级ID',
    `menu_path`       VARCHAR(100) NOT NULL         COMMENT '菜单路径(user_manage:list:read)',
    `level`           TINYINT      NOT NULL         COMMENT '路径级别',
    `plug_ids`        JSON         DEFAULT NULL     COMMENT '插件列表(字典表ID)',
    `type`            TINYINT      DEFAULT 0        COMMENT '类型(0-系统默认,1-自定义)',
    `update_by`       BIGINT       DEFAULT NULL     COMMENT '更新者ID',
    `update_name`     VARCHAR(50)  DEFAULT NULL     COMMENT '更新者名',
    `update_date`     DATETIME     DEFAULT NOW()    COMMENT '更新时间',
    `create_by`       BIGINT       DEFAULT NULL     COMMENT '创建者ID',
    `create_name`     VARCHAR(50)  DEFAULT NULL     COMMENT '创建者名',
    `create_date`     DATETIME     DEFAULT NOW()    COMMENT '创建时间',
    `company_code`    VARCHAR(50)  DEFAULT NULL     COMMENT '单位编码',
    `company_name`    VARCHAR(100) DEFAULT NULL     COMMENT '单位简称',
    `second_org_code` VARCHAR(50)  DEFAULT NULL     COMMENT '二级组织编码',
    `second_org_name` VARCHAR(100) DEFAULT NULL     COMMENT '二级组织简称',
    `org_code`        VARCHAR(50)  DEFAULT NULL     COMMENT '组织编码',
    `org_name`        VARCHAR(100) DEFAULT NULL     COMMENT '组织简称',
    `delete_flag`     TINYINT      DEFAULT 0        COMMENT '删除标识(0-未删除,1-已删除)',
    PRIMARY KEY (`id`),
    KEY `idx_mdtg_permission_parent_id` (`parent_id`) COMMENT '创建父级ID普通索引'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='权限表';

-- 附件表
DROP TABLE IF EXISTS `mdtg_attach`;
CREATE TABLE `mdtg_attach` (
    `id`            BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `business_id`   BIGINT      NOT NULL        COMMENT '业务关联Id: 声音克隆Id、知识库Id',
    `business_type` TINYINT     DEFAULT 0       COMMENT '附件业务类别: 0-声音克隆,1-知识库,2-...',
    `content_type`  VARCHAR(20) DEFAULT NULL    COMMENT '附件类型:audio/way',
    `file_name`     VARCHAR(50) DEFAULT NULL    COMMENT '附件名称',
    `file_size`     BIGINT      DEFAULT NULL    COMMENT '文件大小',
    `object_name`   VARCHAR(50) DEFAULT NULL    COMMENT 'minio的key',
    `status`        TINYINT     DEFAULT 0       COMMENT '训练状态(0-审核中,1-待付费,2-训练中,3-训练成功,4-训练失败)',
    `create_by`     BIGINT      DEFAULT NULL    COMMENT '创建者ID',
    `create_name`   VARCHAR(50) DEFAULT NULL    COMMENT '创建者名',
    `create_date`   DATETIME    DEFAULT NOW()   COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='附件表';

-- 设备信息
DROP TABLE IF EXISTS `mdtg_device`;
CREATE TABLE `mdtg_device` (
    `id`                BIGINT          NOT NULL        COMMENT '主键',
    `alias`             VARCHAR(64)     DEFAULT NULL    COMMENT '设备别名',
    `board`             VARCHAR(50)     DEFAULT NULL    COMMENT '硬件型号',
    `mac_address`       VARCHAR(50)     NOT NULL        COMMENT 'MAC地址',
    `app_version`       VARCHAR(20)     DEFAULT NULL    COMMENT '固件版本号',
    `last_connected_at` DATETIME        DEFAULT NULL    COMMENT '最后连接时间',
    `auto_update`       TINYINT         DEFAULT '0'     COMMENT '自动更新开关(0-关闭,1-开启)',
    `agent_id`          VARCHAR(32)     DEFAULT NULL    COMMENT '智能体 ID',
    `type`              TINYINT         DEFAULT 0       COMMENT '类型(0-头,1-工牌,2-小车)',
    `update_by`         BIGINT          DEFAULT NULL    COMMENT '更新者ID',
    `update_name`       VARCHAR(50)     DEFAULT NULL    COMMENT '更新者名',
    `update_date`       DATETIME        DEFAULT NOW()   COMMENT '更新时间',
    `create_by`         BIGINT          DEFAULT NULL    COMMENT '创建者ID',
    `create_name`       VARCHAR(50)     DEFAULT NULL    COMMENT '创建者名',
    `create_date`       DATETIME        DEFAULT NOW()   COMMENT '创建时间',
    `company_code`      VARCHAR(50)     DEFAULT NULL    COMMENT '单位编码',
    `company_name`      VARCHAR(100)    DEFAULT NULL    COMMENT '单位简称',
    `second_org_code`   VARCHAR(50)     DEFAULT NULL    COMMENT '二级组织编码',
    `second_org_name`   VARCHAR(100)    DEFAULT NULL    COMMENT '二级组织简称',
    `org_code`          VARCHAR(50)     DEFAULT NULL    COMMENT '组织编码',
    `org_name`          VARCHAR(100)    DEFAULT NULL    COMMENT '组织简称',
    `delete_flag`       TINYINT         DEFAULT 0       COMMENT '删除标识(0-未删除,1-已删除)',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_mdtg_device_mac_address` (`mac_address`) COMMENT '创建mac地址唯一索引'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='设备信息表';

-- 设备绑定表
DROP TABLE IF EXISTS `mdtg_device_bind`;
CREATE TABLE `mdtg_device_bind`
(
    `id`          BIGINT      NOT NULL COMMENT '主键',
    `device_id`   BIGINT      NOT NULL COMMENT '设备ID',
    `user_id`     BIGINT      NOT NULL COMMENT '用户ID',
    `update_by`         BIGINT          DEFAULT NULL    COMMENT '更新者ID',
    `update_name`       VARCHAR(50)     DEFAULT NULL    COMMENT '更新者名',
    `update_date`       DATETIME        DEFAULT NOW()   COMMENT '更新时间',
    `create_by`         BIGINT          DEFAULT NULL    COMMENT '创建者ID',
    `create_name`       VARCHAR(50)     DEFAULT NULL    COMMENT '创建者名',
    `create_date`       DATETIME        DEFAULT NOW()   COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_mdtg_device_relevance_device_id_user_id` (`device_id`, `user_id`) COMMENT '设备用户唯一索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='设备绑定表';

-- 固件信息
DROP TABLE IF EXISTS `mdtg_ota`;
CREATE TABLE `mdtg_ota`
(
    `id`            BIGINT NOT NULL COMMENT '主键',
    `firmware_name` VARCHAR(100) DEFAULT NULL COMMENT '名称',
    `type`          VARCHAR(50)  DEFAULT NULL COMMENT '类型',
    `version`       VARCHAR(50)  DEFAULT NULL COMMENT '版本号',
    `size`          BIGINT       DEFAULT NULL COMMENT '文件大小(字节)',
    `remark`        VARCHAR(255) DEFAULT NULL COMMENT '备注/说明',
    `firmware_path` VARCHAR(255) DEFAULT NULL COMMENT '固件路径',
    `create_by`     BIGINT       DEFAULT NULL COMMENT '创建者ID',
    `create_name`   VARCHAR(50)  DEFAULT NULL COMMENT '创建者名字',
    `create_date`   DATETIME     DEFAULT NOW() COMMENT '创建时间',
    `update_by`     BIGINT       DEFAULT NULL COMMENT '更新者ID',
    `update_name`   VARCHAR(50)  DEFAULT NULL COMMENT '更新者名字',
    `update_date`   DATETIME     DEFAULT NOW() COMMENT '更新时间',
    `delete_flag`   TINYINT      DEFAULT 0 COMMENT '删除标识(0-未删除,1-已删除)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='固件信息表';

-- 智能体配置
DROP TABLE IF EXISTS `mdtg_agent`;
CREATE TABLE `mdtg_agent`
(
    `id`                BIGINT NOT NULL COMMENT '主键',
    `code`              VARCHAR(64) DEFAULT NULL COMMENT '编码',
    `name`              VARCHAR(64) DEFAULT NULL COMMENT '名称',
    `vad_model_id`      VARCHAR(64) DEFAULT NULL COMMENT '语音活动检测',
    `asr_model_id`      VARCHAR(32) DEFAULT NULL COMMENT '语音识别',
    `llm_model_id`      VARCHAR(32) DEFAULT NULL COMMENT '大语言模型',
    `vllm_model_id`     VARCHAR(32) DEFAULT NULL COMMENT '视觉模型',
    `intent_model_id`   VARCHAR(32) DEFAULT NULL COMMENT '意图模型',
    `tts_model_id`      VARCHAR(32) DEFAULT NULL COMMENT '语音合成模型',
    `tts_voice_id`      VARCHAR(32) DEFAULT NULL COMMENT '声音音色',
    `system_prompt`     TEXT        DEFAULT NULL COMMENT '角色设定',
    `mem_model`         VARCHAR(32) DEFAULT NULL COMMENT '记忆模式',
    `summary_memory`    TEXT        DEFAULT NULL COMMENT '总结记忆',
    `chat_history_conf` TINYINT     DEFAULT 0 COMMENT '聊天记录配置(0-不记录,1-仅记录文本,2-记录文本和语音)',
    `lang_code`         VARCHAR(10) DEFAULT NULL COMMENT '语言编码',
    `language`          VARCHAR(10) DEFAULT NULL COMMENT '交互语种',
    `voiceprint_ids`    JSON        DEFAULT NULL COMMENT '声纹列表',
    `org_code`          VARCHAR(50) DEFAULT NULL COMMENT '机构标识',
    `create_by`         BIGINT      DEFAULT NULL COMMENT '创建者ID',
    `create_name`       VARCHAR(50) DEFAULT NULL COMMENT '创建者名字',
    `create_date`       DATETIME    DEFAULT NOW() COMMENT '创建时间',
    `update_by`         BIGINT      DEFAULT NULL COMMENT '更新者ID',
    `update_name`       VARCHAR(50) DEFAULT NULL COMMENT '更新者名字',
    `update_date`       DATETIME    DEFAULT NOW() COMMENT '更新时间',
    `delete_flag`       TINYINT     DEFAULT 0 COMMENT '删除标识(0-未删除,1-已删除)',
    PRIMARY KEY (`id`),
    KEY `idx_mdtg_agent_create_by` (`create_by`) COMMENT '创建用户ID普通索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='智能体配置表';

-- 声纹识别
DROP TABLE IF EXISTS `mdtg_voiceprint`;
CREATE TABLE `mdtg_voiceprint`
(
    `id`               BIGINT NOT NULL COMMENT '主键',
    `name`             VARCHAR(64)  DEFAULT NULL COMMENT '声纹名称',
    `description`      VARCHAR(255) DEFAULT NULL COMMENT '声纹描述',
    `embedding`        LONGTEXT     DEFAULT NULL COMMENT '声纹特征向量(JSON 数组格式)',
    `chat_history_ids` JSON         DEFAULT NULL COMMENT '关联记忆数据',
    `permission_ids`   JSON         DEFAULT NULL COMMENT '权限列表',
    `create_by`        BIGINT       DEFAULT NULL COMMENT '创建者ID',
    `create_name`      VARCHAR(50)  DEFAULT NULL COMMENT '创建者名字',
    `create_date`      DATETIME     DEFAULT NOW() COMMENT '创建时间',
    `update_by`        BIGINT       DEFAULT NULL COMMENT '更新者ID',
    `update_name`      VARCHAR(50)  DEFAULT NULL COMMENT '更新者名字',
    `update_date`      DATETIME     DEFAULT NOW() COMMENT '更新时间',
    `delete_flag`      TINYINT      DEFAULT 0 COMMENT '删除标识(0-未删除,1-已删除)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='声纹识别表';

-- 声音克隆
DROP TABLE IF EXISTS `mdtg_voice_clone`;
CREATE TABLE `mdtg_voice_clone`
(
    `id`          BIGINT NOT NULL COMMENT '主键',
    `name`        VARCHAR(64)  DEFAULT NULL COMMENT '声音名称',
    `model_id`    BIGINT       DEFAULT NULL COMMENT '模型ID',
    `voice_id`    BIGINT       DEFAULT NULL COMMENT '声音ID',
    `voice`       LONGBLOB     DEFAULT NULL COMMENT '声音',
    `status`      TINYINT      DEFAULT 0 COMMENT '训练状态(0-审核中,1-待付费,2-训练中,3-训练成功,4-训练失败)',
    `train_error` VARCHAR(255) DEFAULT NULL COMMENT '训练错误原因',
    `create_by`   BIGINT       DEFAULT NULL COMMENT '创建者ID',
    `create_name` VARCHAR(50)  DEFAULT NULL COMMENT '创建者名字',
    `create_date` DATETIME     DEFAULT NOW() COMMENT '创建时间',
    `update_by`   BIGINT       DEFAULT NULL COMMENT '更新者ID',
    `update_name` VARCHAR(50)  DEFAULT NULL COMMENT '更新者名字',
    `update_date` DATETIME     DEFAULT NOW() COMMENT '更新时间',
    `delete_flag` TINYINT      DEFAULT 0 COMMENT '删除标识(0-未删除,1-已删除)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='声音克隆表';

-- 模型信息
DROP TABLE IF EXISTS `mdtg_model_info`;
CREATE TABLE `mdtg_model_info`
(
    `id`          BIGINT NOT NULL COMMENT '主键',
    `code`        VARCHAR(50)  DEFAULT NULL COMMENT '编码(如: AliLLM、DoubaoTTS)',
    `name`        VARCHAR(50)  DEFAULT NULL COMMENT '名称',
    `type`        VARCHAR(20)  DEFAULT NULL COMMENT '模型类型(Memory/ASR/VAD/LLM/TTS)',
    `is_default`  TINYINT      DEFAULT 0 COMMENT '是否默认配置(0-否,1-是)',
    `is_enabled`  TINYINT      DEFAULT 0 COMMENT '是否启用(0-否,1-是)',
    `config_json` JSON         DEFAULT NULL COMMENT '模型配置(JSON格式)',
    `doc_link`    VARCHAR(200) DEFAULT NULL COMMENT '官方文档链接',
    `remark`      TEXT         DEFAULT NULL COMMENT '备注',
    `create_by`   BIGINT       DEFAULT NULL COMMENT '创建者ID',
    `create_name` VARCHAR(50)  DEFAULT NULL COMMENT '创建者名字',
    `create_date` DATETIME     DEFAULT NOW() COMMENT '创建时间',
    `update_by`   BIGINT       DEFAULT NULL COMMENT '更新者ID',
    `update_name` VARCHAR(50)  DEFAULT NULL COMMENT '更新者名字',
    `update_date` DATETIME     DEFAULT NOW() COMMENT '更新时间',
    `delete_flag` TINYINT      DEFAULT 0 COMMENT '删除标识(0-未删除,1-已删除)',
    PRIMARY KEY (`id`),
    KEY `idx_mdtg_model_info_type` (`type`) COMMENT '创建类型普通索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='模型信息表';

-- 知识库
DROP TABLE IF EXISTS `mdtg_knowledge_base`;
CREATE TABLE `mdtg_knowledge_base`
(
    `id`           BIGINT       NOT NULL COMMENT '主键',
    `name`         VARCHAR(100) NOT NULL COMMENT '名称',
    `rag_model_id` VARCHAR(64) DEFAULT NULL COMMENT 'RAG模型配置ID',
    `description`  TEXT        DEFAULT NULL COMMENT '描述',
    `attach_urls`  JSON        DEFAULT NULL COMMENT '附件列表(JSON格式)',
    `status`       TINYINT     DEFAULT 0 COMMENT '状态(0-停用,1-启用)',
    `create_by`    BIGINT      DEFAULT NULL COMMENT '创建者ID',
    `create_name`  VARCHAR(50) DEFAULT NULL COMMENT '创建者名字',
    `create_date`  DATETIME    DEFAULT NOW() COMMENT '创建时间',
    `update_by`    BIGINT      DEFAULT NULL COMMENT '更新者ID',
    `update_name`  VARCHAR(50) DEFAULT NULL COMMENT '更新者名字',
    `update_date`  DATETIME    DEFAULT NOW() COMMENT '更新时间',
    `delete_flag`  TINYINT     DEFAULT 0 COMMENT '删除标识(0-未删除,1-已删除)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='知识库表';

-- 字典数据
DROP TABLE IF EXISTS `mdtg_dict_data`;
CREATE TABLE `mdtg_dict_data`
(
    `id`          BIGINT       NOT NULL COMMENT 'id',
    `parent_id`   BIGINT       DEFAULT NULL COMMENT '父级ID',
    `label`       VARCHAR(255) NOT NULL COMMENT '标签名',
    `key`         VARCHAR(255) DEFAULT NULL COMMENT '键',
    `value`       VARCHAR(255) DEFAULT NULL COMMENT '值',
    `remark`      VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `status`      TINYINT      DEFAULT 1 COMMENT '状态(0-停用,1-启用)',
    `create_by`   BIGINT       DEFAULT NULL COMMENT '创建者ID',
    `create_name` VARCHAR(50)  DEFAULT NULL COMMENT '创建者名字',
    `create_date` DATETIME     DEFAULT NOW() COMMENT '创建时间',
    `update_by`   BIGINT       DEFAULT NULL COMMENT '更新者ID',
    `update_name` VARCHAR(50)  DEFAULT NULL COMMENT '更新者名字',
    `update_date` DATETIME     DEFAULT NOW() COMMENT '更新时间',
    `delete_flag` TINYINT      DEFAULT 0 COMMENT '删除标识(0-未删除,1-已删除)',
    PRIMARY KEY (`id`),
    KEY `idx_mdtg_dict_data_parent_id` (`parent_id`) COMMENT '创建父级ID普通索引',
    KEY `idx_mdtg_dict_data_label` (`parent_id`) COMMENT '创建标签名普通索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='字典数据表';

-- TTS 音色
DROP TABLE IF EXISTS `mdtg_tts_voice`;
CREATE TABLE `mdtg_tts_voice`
(
    `id`              BIGINT NOT NULL COMMENT '主键',
    `voice_clone_id`  BIGINT NOT NULL COMMENT '声音克隆Id',
    `name`            VARCHAR(20)  DEFAULT NULL COMMENT '音色名称',
    `tts_model_id`    VARCHAR(32)  DEFAULT NULL COMMENT 'TTS模型',
    `tts_voice`       VARCHAR(50)  DEFAULT NULL COMMENT '音色编码',
    `languages`       VARCHAR(50)  DEFAULT NULL COMMENT '语言',
    `voice_demo`      VARCHAR(500) DEFAULT NULL COMMENT '音色 Demo',
    `remark`          VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `reference_audio` VARCHAR(500) DEFAULT NULL COMMENT '参考音频路径',
    `reference_text`  VARCHAR(500) DEFAULT NULL COMMENT '参考文本',
    `create_by`       BIGINT       DEFAULT NULL COMMENT '创建者ID',
    `create_name`     VARCHAR(50)  DEFAULT NULL COMMENT '创建者名字',
    `create_date`     DATETIME     DEFAULT NOW() COMMENT '创建时间',
    `update_by`       BIGINT       DEFAULT NULL COMMENT '更新者ID',
    `update_name`     VARCHAR(50)  DEFAULT NULL COMMENT '更新者名字',
    `update_date`     DATETIME     DEFAULT NOW() COMMENT '更新时间',
    `delete_flag`     TINYINT      DEFAULT 0 COMMENT '删除标识(0-未删除,1-已删除)',
    PRIMARY KEY (`id`),
    KEY `idx_mdtg_tts_voice_tts_model_id` (`tts_model_id`) COMMENT '创建TTS模型ID的普通索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='TTS 音色表';

-- 智能体聊天记录
CREATE TABLE `mdtg_agent_chat_history`
(
    `id`            BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `agent_id`      BIGINT      DEFAULT NULL COMMENT '智能体ID',
    `voiceprint_id` BIGINT      DEFAULT NULL COMMENT '声纹ID',
    `session_id`    VARCHAR(50) DEFAULT NULL COMMENT '会话ID',
    `mac_address`   VARCHAR(50) DEFAULT NULL COMMENT 'MAC地址',
    `chat_type`     TINYINT     DEFAULT 0 COMMENT '消息类型: 0-未知,1-文本,2-音频',
    `chat_history`  JSON        DEFAULT NULL COMMENT '聊天内容[{"content": "xxx", "audioFileUrl": "xx","time":"xx-xx-xx","reply":[{"content":"xxx","audioFileUrl": "xx"}]}]',
    `create_date`   DATE        DEFAULT CURRENT_DATE COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_mdtg_agent_chat_history_voiceprint_id` (`voiceprint_id`) COMMENT '创建声纹ID的普通索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 2730
  DEFAULT CHARSET = utf8mb4 COMMENT ='智能体聊天记录表';
-- 将下一个自增设置为 5000: ALTER TABLE mdtg_agent_chat_history AUTO_INCREMENT = 5000;

-- 智能体聊天音频数据
# CREATE TABLE `ai_agent_chat_audio`(
#     `id`    varchar(32) NOT NULL COMMENT '主键ID',
#     `audio` longblob COMMENT '音频opus数据',
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='智能体聊天音频数据表';

-- Agent与插件的唯一映射
# CREATE TABLE `ai_agent_plugin_mapping`
# (
#     `id`         bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
#     `agent_id`   varchar(32) NOT NULL COMMENT '智能体ID',
#     `plugin_id`  varchar(32) NOT NULL COMMENT '插件ID',
#     `param_info` json        NOT NULL COMMENT '参数信息',
#     PRIMARY KEY (`id`),
#     UNIQUE KEY `uk_agent_provider` (`agent_id`, `plugin_id`)
# ) ENGINE = InnoDB
#   AUTO_INCREMENT = 2018941308941463557
#   DEFAULT CHARSET = utf8mb4 COMMENT ='Agent与插件的唯一映射表';

-- 参数管理
# CREATE TABLE `sys_params`
# (
#     `id`          bigint NOT NULL COMMENT 'id',
#     `param_code`  varchar(100)     DEFAULT NULL COMMENT '参数编码',
#     `param_value` varchar(2000)    DEFAULT NULL COMMENT '参数值',
#     `value_type`  varchar(20)      DEFAULT 'string' COMMENT '值类型：string-字符串，number-数字，boolean-布尔，array-数组',
#     `param_type`  tinyint unsigned DEFAULT '1' COMMENT '类型   0：系统参数   1：非系统参数',
#     `remark`      varchar(200)     DEFAULT NULL COMMENT '备注',
#     `creator`     bigint           DEFAULT NULL COMMENT '创建者',
#     `create_date` datetime         DEFAULT NULL COMMENT '创建时间',
#     `updater`     bigint           DEFAULT NULL COMMENT '更新者',
#     `update_date` datetime         DEFAULT NULL COMMENT '更新时间',
#     PRIMARY KEY (`id`),
#     UNIQUE KEY `uk_param_code` (`param_code`)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4 COMMENT ='参数管理表';

-- 字典类型
# CREATE TABLE `sys_dict_type`
# (
#     `id`          BIGINT       NOT NULL COMMENT 'id',
#     `parent_id`   BIGINT       DEFAULT NULL COMMENT '父级ID',
#     `dict_type`   VARCHAR(100) NOT NULL COMMENT '字典类型',
#     `dict_name`   VARCHAR(255) NOT NULL COMMENT '字典名称',
#     `remark`      VARCHAR(255) DEFAULT NULL COMMENT '备注',
#     `create_by`   BIGINT       DEFAULT NULL COMMENT '创建者ID',
#     `create_name` VARCHAR(50)  DEFAULT NULL COMMENT '创建者名字',
#     `create_date` DATETIME     DEFAULT NOW() COMMENT '创建时间',
#     `update_by`   BIGINT       DEFAULT NULL COMMENT '更新者ID',
#     `update_name` VARCHAR(50)  DEFAULT NULL COMMENT '更新者名字',
#     `update_date` DATETIME     DEFAULT NOW() COMMENT '更新时间',
#     `delete_flag` TINYINT      DEFAULT 0 COMMENT '删除标识(0-正常,1-删除)',
#     PRIMARY KEY (`id`),
#     UNIQUE KEY `dict_type` (`dict_type`)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4 COMMENT ='字典类型表';

-- 模型配置
# CREATE TABLE `ai_model_provider` (
#     `id`                varchar(32)     NOT NULL        COMMENT '主键',
#     `model_type`        varchar(20)     DEFAULT NULL    COMMENT '模型类型(Memory/ASR/VAD/LLM/TTS)',
#     `provider_code`     varchar(50)     DEFAULT NULL    COMMENT '供应器类型',
#     `name`              varchar(50)     DEFAULT NULL    COMMENT '供应器名称',
#     `fields`            json            DEFAULT NULL    COMMENT '供应器字段列表(JSON格式)',
#     `sort`              int unsigned    DEFAULT '0'     COMMENT '排序',
#     `create_by`         BIGINT          DEFAULT NULL    COMMENT '创建者ID',
#     `create_name`       VARCHAR(50)     DEFAULT NULL    COMMENT '创建者名字',
#     `create_date`       DATETIME        DEFAULT NOW()   COMMENT '创建时间',
#     `update_by`         BIGINT          DEFAULT NULL    COMMENT '更新者ID',
#     `update_name`       VARCHAR(50)     DEFAULT NULL    COMMENT '更新者名字',
#     `update_date`       DATETIME        DEFAULT NOW()   COMMENT '更新时间',
#     `delete_flag`       TINYINT         DEFAULT 0       COMMENT '删除标识(0-正常,1-删除)',
#     PRIMARY KEY (`id`),
#     KEY `idx_ai_model_provider_model_type` (`model_type`) COMMENT '创建模型类型的索引，用于快速查找特定类型下的所有供应器信息'
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模型配置表';

-- 智能体声纹
# CREATE TABLE `ai_agent_voice_print` (
#     `id` varchar(32) NOT NULL COMMENT '声纹ID',
#     `agent_id` varchar(32) NOT NULL COMMENT '关联的智能体ID',
#     `source_name` varchar(50) NOT NULL COMMENT '声纹来源的人的姓名',
#     `introduce` varchar(200) DEFAULT NULL COMMENT '描述声纹来源的这个人',
#     `create_date` datetime DEFAULT NULL COMMENT '创建时间',
#     `creator` bigint DEFAULT NULL COMMENT '创建者',
#     `update_date` datetime DEFAULT NULL COMMENT '修改时间',
#     `updater` bigint DEFAULT NULL COMMENT '修改者',
#     `audio_id` varchar(32) NOT NULL COMMENT '音频ID',
#     PRIMARY KEY (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='智能体声纹表';

-- 智能体上下文源配置
# CREATE TABLE `ai_agent_context_provider` (
#     `id` varchar(32) NOT NULL COMMENT '主键',
#     `agent_id` varchar(32) NOT NULL COMMENT '智能体ID',
#     `context_providers` json DEFAULT NULL COMMENT '上下文源配置',
#     `creator` bigint DEFAULT NULL COMMENT '创建者',
#     `created_at` datetime DEFAULT NULL COMMENT '创建时间',
#     `updater` bigint DEFAULT NULL COMMENT '更新者',
#     `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
#     PRIMARY KEY (`id`),
#     KEY `idx_agent_id` (`agent_id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='智能体上下文源配置表';

-- 智能体配置模板
# CREATE TABLE `ai_agent_template` (
#     `id` varchar(32) NOT NULL COMMENT '智能体唯一标识',
#     `agent_code` varchar(36) DEFAULT NULL COMMENT '智能体编码',
#     `agent_name` varchar(64) DEFAULT NULL COMMENT '智能体名称',
#     `asr_model_id` varchar(32) DEFAULT NULL COMMENT '语音识别模型标识',
#     `vad_model_id` varchar(64) DEFAULT NULL COMMENT '语音活动检测标识',
#     `llm_model_id` varchar(32) DEFAULT NULL COMMENT '大语言模型标识',
#     `vllm_model_id` varchar(32) DEFAULT 'VLLM_ChatGLMVLLM' COMMENT '视觉模型标识',
#     `tts_model_id` varchar(32) DEFAULT NULL COMMENT '语音合成模型标识',
#     `tts_voice_id` varchar(32) DEFAULT NULL COMMENT '音色标识',
#     `mem_model_id` varchar(32) DEFAULT NULL COMMENT '记忆模型标识',
#     `intent_model_id` varchar(32) DEFAULT NULL COMMENT '意图模型标识',
#     `system_prompt` text COMMENT '角色设定参数',
#     `summary_memory` text COMMENT '总结记忆',
#     `chat_history_conf` tinyint NOT NULL DEFAULT '0' COMMENT '聊天记录配置（0不记录 1仅记录文本 2记录文本和语音）',
#     `lang_code` varchar(10) DEFAULT NULL COMMENT '语言编码',
#     `language` varchar(10) DEFAULT NULL COMMENT '交互语种',
#     `sort` int unsigned DEFAULT '0' COMMENT '排序权重',
#     `creator` bigint DEFAULT NULL COMMENT '创建者 ID',
#     `created_at` datetime DEFAULT NULL COMMENT '创建时间',
#     `updater` bigint DEFAULT NULL COMMENT '更新者 ID',
#     `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
#     PRIMARY KEY (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='智能体配置模板表';