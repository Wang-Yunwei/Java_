-- 用户
DROP TABLE IF EXISTS `mdtg_user`;
CREATE TABLE `mdtg_user` (
    `id`            BIGINT          NOT NULL        COMMENT '用户ID',
    `username`      VARCHAR(50)     NOT NULL        COMMENT '用户名',
    `password`      VARCHAR(100)    NOT NULL        COMMENT '密码',
    `gender`        TINYINT         DEFAULT 0       COMMENT '性别(0-未知,1-男,2-女)',
    `identity_card` DATETIME        DEFAULT NULL    COMMENT '居民身份证',
    `phone`         VARCHAR(20)     DEFAULT NULL    COMMENT '手机号',
    `email`         VARCHAR(20)     DEFAULT NULL    COMMENT '邮箱',
    `address`       VARCHAR(300)    DEFAULT NULL    COMMENT '住址',
    `role_ids`      VARCHAR(250)    DEFAULT NULL    COMMENT '角色ID',
    `status`        TINYINT         DEFAULT 0       COMMENT '状态(0:系统默认,1:自定义)',
    `create_by`     BIGINT          DEFAULT NULL    COMMENT '创建者ID',
    `create_name`   VARCHAR(50)     DEFAULT NULL    COMMENT '创建者名字',
    `create_date`   DATETIME        DEFAULT NOW()   COMMENT '创建时间',
    `update_by`     BIGINT          DEFAULT NULL    COMMENT '更新者ID',
    `update_name`   VARCHAR(50)     DEFAULT NULL    COMMENT '更新者名字',
    `update_date`   DATETIME        DEFAULT NOW()   COMMENT '更新时间',
    `delete_flag`   TINYINT         DEFAULT 0       COMMENT '删除标识(0-正常,1-删除)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 角色
DROP TABLE IF EXISTS `mdtg_role`;
CREATE TABLE `mdtg_role` (
    `id`                BIGINT          NOT NULL        COMMENT '角色ID',
    `code`              VARCHAR(50)     DEFAULT NULL    COMMENT '角色编码 (如: admin)',
    `description`       VARCHAR(200)    DEFAULT NULL    COMMENT '描述',
    `permission_ids`    VARCHAR(250)    DEFAULT NULL    COMMENT '权限ID',
    `status`            TINYINT         DEFAULT 0       COMMENT '状态(0:系统默认,1:自定义)',
    `create_by`         BIGINT          DEFAULT NULL    COMMENT '创建者ID',
    `create_name`       VARCHAR(50)     DEFAULT NULL    COMMENT '创建者名字',
    `create_date`       DATETIMR        DEFAULT NOW()   COMMENT '创建时间',
    `update_by`         BIGINT          DEFAULT NULL    COMMENT '更新者ID',
    `update_name`       VARCHAR(50)     DEFAULT NULL    COMMENT '更新者名字',
    `update_date`       DATETIME        DEFAULT NOW()   COMMENT '更新时间',
    `delete_flag`       TINYINT         DEFAULT 0       COMMENT '删除标识(0-正常,1-删除)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 权限
DROP TABLE IF EXISTS `mdtg_permission`;
CREATE TABLE `mdtg_permission` (
    `id`            BIGINT          NOT NULL        COMMENT '权限ID',
    `parent_id`     BIGINT          DEFAULT NULL    COMMENT '父级ID',
    `code`          VARCHAR(100)    NOT NULL        COMMENT '编码 (如: user_manage:list:create/read/update/delete)',
    `level`         TINYINT         NOT NULL        COMMENT '级别',
    `menu_path`     VARCHAR(100)    NOT NULL        COMMENT '菜单路径 (user_manage:list:read)',
    `status`        TINYINT         DEFAULT 0       COMMENT '状态(0:系统默认,1:自定义)',
    `create_by`     BIGINT          DEFAULT NULL    COMMENT '创建者ID',
    `create_name`   VARCHAR(50)     DEFAULT NULL    COMMENT '创建者名字',
    `create_date`   DATETIMR        DEFAULT NOW()   COMMENT '创建时间',
    `update_by`     BIGINT          DEFAULT NULL    COMMENT '更新者ID',
    `update_name`   VARCHAR(50)     DEFAULT NULL    COMMENT '更新者名字',
    `update_date`   DATETIMR        DEFAULT NOW()   COMMENT '更新时间',
    `delete_flag`   TINYINT         DEFAULT 0       COMMENT '删除标识(0-正常,1-删除)',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code` (`permission_code`) -- 确保编码唯一
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 固件信息
CREATE TABLE `mdtg_ota` (
    `id` varchar(32) NOT NULL COMMENT 'ID',
    `firmware_name` varchar(100) DEFAULT NULL COMMENT '固件名称',
    `type` varchar(50) DEFAULT NULL COMMENT '固件类型',
    `version` varchar(50) DEFAULT NULL COMMENT '版本号',
    `size` bigint DEFAULT NULL COMMENT '文件大小(字节)',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注/说明',
    `firmware_path` varchar(255) DEFAULT NULL COMMENT '固件路径',
    `sort` int unsigned DEFAULT '0' COMMENT '排序',
    `updater` bigint DEFAULT NULL COMMENT '更新者',
    `update_date` datetime DEFAULT NULL COMMENT '更新时间',
    `creator` bigint DEFAULT NULL COMMENT '创建者',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='固件信息表';

-- 知识库
CREATE TABLE `ai_rag_dataset` (
    `id` varchar(32) NOT NULL COMMENT '唯一标识',
    `dataset_id` varchar(64) NOT NULL COMMENT '知识库ID',
    `rag_model_id` varchar(64) DEFAULT NULL COMMENT 'RAG模型配置ID',
    `name` varchar(100) NOT NULL COMMENT '知识库名称',
    `description` text COMMENT '知识库描述',
    `status` tinyint(1) DEFAULT '1' COMMENT '状态：0停用 1启用',
    `creator` bigint DEFAULT NULL COMMENT '创建者',
    `created_at` datetime DEFAULT NULL COMMENT '创建时间',
    `updater` bigint DEFAULT NULL COMMENT '更新者',
    `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_dataset_id` (`dataset_id`),
    KEY `idx_ai_rag_dataset_status` (`status`),
    KEY `idx_ai_rag_dataset_creator` (`creator`),
    KEY `idx_ai_rag_dataset_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='知识库表';

-- 声纹识别
CREATE TABLE `ai_voiceprint` (
    `id` varchar(32) NOT NULL COMMENT '声纹唯一标识',
    `name` varchar(64) DEFAULT NULL COMMENT '声纹名称',
    `user_id` bigint DEFAULT NULL COMMENT '用户 ID（关联用户表）',
    `agent_id` varchar(32) DEFAULT NULL COMMENT '关联智能体 ID',
    `agent_code` varchar(36) DEFAULT NULL COMMENT '关联智能体编码',
    `agent_name` varchar(36) DEFAULT NULL COMMENT '关联智能体名称',
    `description` varchar(255) DEFAULT NULL COMMENT '声纹描述',
    `embedding` longtext COMMENT '声纹特征向量（JSON 数组格式）',
    `memory` text COMMENT '关联记忆数据',
    `sort` int unsigned DEFAULT '0' COMMENT '排序权重',
    `creator` bigint DEFAULT NULL COMMENT '创建者 ID',
    `created_at` datetime DEFAULT NULL COMMENT '创建时间',
    `updater` bigint DEFAULT NULL COMMENT '更新者 ID',
    `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='声纹识别表';


-- 声音克隆
CREATE TABLE `ai_voice_clone` (
    `id` varchar(32) NOT NULL COMMENT '唯一标识',
    `name` varchar(64) DEFAULT NULL COMMENT '声音名称',
    `model_id` varchar(32) DEFAULT NULL COMMENT '模型id',
    `voice_id` varchar(32) DEFAULT NULL COMMENT '声音id',
    `user_id` bigint DEFAULT NULL COMMENT '用户 ID（关联用户表）',
    `voice` longblob COMMENT '声音',
    `train_status` tinyint(1) DEFAULT '0' COMMENT '训练状态：0待训练 1训练中 2训练成功 3训练失败',
    `train_error` varchar(255) DEFAULT NULL COMMENT '训练错误原因',
    `creator` bigint DEFAULT NULL COMMENT '创建者 ID',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_ai_voice_clone_user_id_model_id_train_status` (`model_id`,`user_id`,`train_status`),
    KEY `idx_ai_voice_clone_voice_id` (`voice_id`),
    KEY `idx_ai_voice_clone_user_id` (`user_id`),
    KEY `idx_ai_voice_clone_model_id_voice_id` (`model_id`,`voice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='声音克隆表';

-- 智能体声纹
CREATE TABLE `ai_agent_voice_print` (
    `id` varchar(32) NOT NULL COMMENT '声纹ID',
    `agent_id` varchar(32) NOT NULL COMMENT '关联的智能体ID',
    `source_name` varchar(50) NOT NULL COMMENT '声纹来源的人的姓名',
    `introduce` varchar(200) DEFAULT NULL COMMENT '描述声纹来源的这个人',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `creator` bigint DEFAULT NULL COMMENT '创建者',
    `update_date` datetime DEFAULT NULL COMMENT '修改时间',
    `updater` bigint DEFAULT NULL COMMENT '修改者',
    `audio_id` varchar(32) NOT NULL COMMENT '音频ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='智能体声纹表';


-- 字典类型
CREATE TABLE `sys_dict_type` (
    `id` bigint NOT NULL COMMENT 'id',
    `dict_type` varchar(100) NOT NULL COMMENT '字典类型',
    `dict_name` varchar(255) NOT NULL COMMENT '字典名称',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    `sort` int unsigned DEFAULT NULL COMMENT '排序',
    `creator` bigint DEFAULT NULL COMMENT '创建者',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `updater` bigint DEFAULT NULL COMMENT '更新者',
    `update_date` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典类型表';

-- 智能体上下文源配置
CREATE TABLE `ai_agent_context_provider` (
    `id` varchar(32) NOT NULL COMMENT '主键',
    `agent_id` varchar(32) NOT NULL COMMENT '智能体ID',
    `context_providers` json DEFAULT NULL COMMENT '上下文源配置',
    `creator` bigint DEFAULT NULL COMMENT '创建者',
    `created_at` datetime DEFAULT NULL COMMENT '创建时间',
    `updater` bigint DEFAULT NULL COMMENT '更新者',
    `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_agent_id` (`agent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='智能体上下文源配置表';

-- 智能体配置
CREATE TABLE `ai_agent` (
    `id` varchar(32) NOT NULL COMMENT '智能体唯一标识',
    `user_id` bigint DEFAULT NULL COMMENT '所属用户 ID',
    `agent_code` varchar(36) DEFAULT NULL COMMENT '智能体编码',
    `agent_name` varchar(64) DEFAULT NULL COMMENT '智能体名称',
    `asr_model_id` varchar(32) DEFAULT NULL COMMENT '语音识别模型标识',
    `vad_model_id` varchar(64) DEFAULT NULL COMMENT '语音活动检测标识',
    `llm_model_id` varchar(32) DEFAULT NULL COMMENT '大语言模型标识',
    `vllm_model_id` varchar(32) DEFAULT 'VLLM_ChatGLMVLLM' COMMENT '视觉模型标识',
    `tts_model_id` varchar(32) DEFAULT NULL COMMENT '语音合成模型标识',
    `tts_voice_id` varchar(32) DEFAULT NULL COMMENT '音色标识',
    `mem_model_id` varchar(32) DEFAULT NULL COMMENT '记忆模型标识',
    `intent_model_id` varchar(32) DEFAULT NULL COMMENT '意图模型标识',
    `system_prompt` text COMMENT '角色设定参数',
    `summary_memory` text COMMENT '总结记忆',
    `chat_history_conf` tinyint NOT NULL DEFAULT '0' COMMENT '聊天记录配置（0不记录 1仅记录文本 2记录文本和语音）',
    `lang_code` varchar(10) DEFAULT NULL COMMENT '语言编码',
    `language` varchar(10) DEFAULT NULL COMMENT '交互语种',
    `sort` int unsigned DEFAULT '0' COMMENT '排序权重',
    `creator` bigint DEFAULT NULL COMMENT '创建者 ID',
    `created_at` datetime DEFAULT NULL COMMENT '创建时间',
    `updater` bigint DEFAULT NULL COMMENT '更新者 ID',
    `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_ai_agent_user_id` (`user_id`) COMMENT '创建用户的索引，用于快速查找用户下的智能体信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='智能体配置表';

-- 设备信息
CREATE TABLE `ai_device` (
    `id` varchar(32) NOT NULL COMMENT '设备唯一标识',
    `user_id` bigint DEFAULT NULL COMMENT '关联用户 ID',
    `mac_address` varchar(50) DEFAULT NULL COMMENT 'MAC 地址',
    `last_connected_at` datetime DEFAULT NULL COMMENT '最后连接时间',
    `auto_update` tinyint unsigned DEFAULT '0' COMMENT '自动更新开关(0 关闭/1 开启)',
    `board` varchar(50) DEFAULT NULL COMMENT '设备硬件型号',
    `alias` varchar(64) DEFAULT NULL COMMENT '设备别名',
    `agent_id` varchar(32) DEFAULT NULL COMMENT '智能体 ID',
    `app_version` varchar(20) DEFAULT NULL COMMENT '固件版本号',
    `sort` int unsigned DEFAULT '0' COMMENT '排序',
    `creator` bigint DEFAULT NULL COMMENT '创建者',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `updater` bigint DEFAULT NULL COMMENT '更新者',
    `update_date` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_ai_device_created_at` (`mac_address`) COMMENT '创建mac的索引，用于快速查找设备信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备信息表';

-- 智能体配置模板
CREATE TABLE `ai_agent_template` (
    `id` varchar(32) NOT NULL COMMENT '智能体唯一标识',
    `agent_code` varchar(36) DEFAULT NULL COMMENT '智能体编码',
    `agent_name` varchar(64) DEFAULT NULL COMMENT '智能体名称',
    `asr_model_id` varchar(32) DEFAULT NULL COMMENT '语音识别模型标识',
    `vad_model_id` varchar(64) DEFAULT NULL COMMENT '语音活动检测标识',
    `llm_model_id` varchar(32) DEFAULT NULL COMMENT '大语言模型标识',
    `vllm_model_id` varchar(32) DEFAULT 'VLLM_ChatGLMVLLM' COMMENT '视觉模型标识',
    `tts_model_id` varchar(32) DEFAULT NULL COMMENT '语音合成模型标识',
    `tts_voice_id` varchar(32) DEFAULT NULL COMMENT '音色标识',
    `mem_model_id` varchar(32) DEFAULT NULL COMMENT '记忆模型标识',
    `intent_model_id` varchar(32) DEFAULT NULL COMMENT '意图模型标识',
    `system_prompt` text COMMENT '角色设定参数',
    `summary_memory` text COMMENT '总结记忆',
    `chat_history_conf` tinyint NOT NULL DEFAULT '0' COMMENT '聊天记录配置（0不记录 1仅记录文本 2记录文本和语音）',
    `lang_code` varchar(10) DEFAULT NULL COMMENT '语言编码',
    `language` varchar(10) DEFAULT NULL COMMENT '交互语种',
    `sort` int unsigned DEFAULT '0' COMMENT '排序权重',
    `creator` bigint DEFAULT NULL COMMENT '创建者 ID',
    `created_at` datetime DEFAULT NULL COMMENT '创建时间',
    `updater` bigint DEFAULT NULL COMMENT '更新者 ID',
    `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='智能体配置模板表';

-- Agent与插件的唯一映射
CREATE TABLE `ai_agent_plugin_mapping` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `agent_id` varchar(32) NOT NULL COMMENT '智能体ID',
    `plugin_id` varchar(32) NOT NULL COMMENT '插件ID',
    `param_info` json NOT NULL COMMENT '参数信息',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_agent_provider` (`agent_id`,`plugin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2018941308941463557 DEFAULT CHARSET=utf8mb4 COMMENT='Agent与插件的唯一映射表';

-- 参数管理
CREATE TABLE `sys_params` (
    `id` bigint NOT NULL COMMENT 'id',
    `param_code` varchar(100) DEFAULT NULL COMMENT '参数编码',
    `param_value` varchar(2000) DEFAULT NULL COMMENT '参数值',
    `value_type` varchar(20) DEFAULT 'string' COMMENT '值类型：string-字符串，number-数字，boolean-布尔，array-数组',
    `param_type` tinyint unsigned DEFAULT '1' COMMENT '类型   0：系统参数   1：非系统参数',
    `remark` varchar(200) DEFAULT NULL COMMENT '备注',
    `creator` bigint DEFAULT NULL COMMENT '创建者',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `updater` bigint DEFAULT NULL COMMENT '更新者',
    `update_date` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_param_code` (`param_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='参数管理表';

-- 模型配置
CREATE TABLE `ai_model_provider` (
    `id` varchar(32) NOT NULL COMMENT '主键',
    `model_type` varchar(20) DEFAULT NULL COMMENT '模型类型(Memory/ASR/VAD/LLM/TTS)',
    `provider_code` varchar(50) DEFAULT NULL COMMENT '供应器类型',
    `name` varchar(50) DEFAULT NULL COMMENT '供应器名称',
    `fields` json DEFAULT NULL COMMENT '供应器字段列表(JSON格式)',
    `sort` int unsigned DEFAULT '0' COMMENT '排序',
    `creator` bigint DEFAULT NULL COMMENT '创建者',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `updater` bigint DEFAULT NULL COMMENT '更新者',
    `update_date` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_ai_model_provider_model_type` (`model_type`) COMMENT '创建模型类型的索引，用于快速查找特定类型下的所有供应器信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模型配置表';

-- 模型配置
CREATE TABLE `ai_model_config` (
    `id` varchar(32) NOT NULL COMMENT '主键',
    `model_type` varchar(20) DEFAULT NULL COMMENT '模型类型(Memory/ASR/VAD/LLM/TTS)',
    `model_code` varchar(50) DEFAULT NULL COMMENT '模型编码(如AliLLM、DoubaoTTS)',
    `model_name` varchar(50) DEFAULT NULL COMMENT '模型名称',
    `is_default` tinyint(1) DEFAULT '0' COMMENT '是否默认配置(0否 1是)',
    `is_enabled` tinyint(1) DEFAULT '0' COMMENT '是否启用',
    `config_json` json DEFAULT NULL COMMENT '模型配置(JSON格式)',
    `doc_link` varchar(200) DEFAULT NULL COMMENT '官方文档链接',
    `remark` text COMMENT '备注',
    `sort` int unsigned DEFAULT '0' COMMENT '排序',
    `creator` bigint DEFAULT NULL COMMENT '创建者',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `updater` bigint DEFAULT NULL COMMENT '更新者',
    `update_date` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_ai_model_config_model_type` (`model_type`) COMMENT '创建模型类型的索引，用于快速查找特定类型下的所有配置信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模型配置表';

-- 字典数据
CREATE TABLE `sys_dict_data` (
    `id` bigint NOT NULL COMMENT 'id',
    `dict_type_id` bigint NOT NULL COMMENT '字典类型ID',
    `dict_label` varchar(255) NOT NULL COMMENT '字典标签',
    `dict_value` varchar(255) DEFAULT NULL COMMENT '字典值',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    `sort` int unsigned DEFAULT NULL COMMENT '排序',
    `creator` bigint DEFAULT NULL COMMENT '创建者',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `updater` bigint DEFAULT NULL COMMENT '更新者',
    `update_date` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_dict_type_value` (`dict_type_id`,`dict_value`),
    KEY `idx_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';

-- TTS 音色
CREATE TABLE `ai_tts_voice` (
    `id` varchar(32) NOT NULL COMMENT '主键',
    `tts_model_id` varchar(32) DEFAULT NULL COMMENT '对应 TTS 模型主键',
    `name` varchar(20) DEFAULT NULL COMMENT '音色名称',
    `tts_voice` varchar(50) DEFAULT NULL COMMENT '音色编码',
    `languages` varchar(50) DEFAULT NULL COMMENT '语言',
    `voice_demo` varchar(500) DEFAULT NULL COMMENT '音色 Demo',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    `reference_audio` varchar(500) DEFAULT NULL COMMENT '参考音频路径',
    `reference_text` varchar(500) DEFAULT NULL COMMENT '参考文本',
    `sort` int unsigned DEFAULT '0' COMMENT '排序',
    `creator` bigint DEFAULT NULL COMMENT '创建者',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `updater` bigint DEFAULT NULL COMMENT '更新者',
    `update_date` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_ai_tts_voice_tts_model_id` (`tts_model_id`) COMMENT '创建 TTS 模型主键的索引，用于快速查找对应模型的音色信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='TTS 音色表';

-- 智能体聊天音频数据
CREATE TABLE `ai_agent_chat_audio` (
    `id` varchar(32) NOT NULL COMMENT '主键ID',
    `audio` longblob COMMENT '音频opus数据',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='智能体聊天音频数据表';

-- 智能体聊天记录
CREATE TABLE `ai_agent_chat_history` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `mac_address` varchar(50) DEFAULT NULL COMMENT 'MAC地址',
    `agent_id` varchar(32) DEFAULT NULL COMMENT '智能体id',
    `session_id` varchar(50) DEFAULT NULL COMMENT '会话ID',
    `chat_type` tinyint DEFAULT NULL COMMENT '消息类型: 1-用户, 2-智能体',
    `content` varchar(1024) DEFAULT NULL COMMENT '聊天内容',
    `audio_id` varchar(32) DEFAULT NULL COMMENT '音频ID',
    `created_at` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    `updated_at` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_ai_agent_chat_history_mac` (`mac_address`),
    KEY `idx_ai_agent_chat_history_session_id` (`session_id`),
    KEY `idx_ai_agent_chat_history_agent_id` (`agent_id`),
    KEY `idx_ai_agent_chat_history_agent_session_created` (`agent_id`,`session_id`,`created_at`),
    KEY `idx_ai_agent_chat_history_audio_id` (`audio_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2730 DEFAULT CHARSET=utf8mb4 COMMENT='智能体聊天记录表';