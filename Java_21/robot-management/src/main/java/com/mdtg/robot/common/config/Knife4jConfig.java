package com.mdtg.robot.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangYunwei [2026-03-04]
 */
@Configuration
public class Knife4jConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MDTG Robot Server API")
                        .version("1.0.0")
                        .description("机器人管理服务接口文档，基于 Knife4j 增强"))
                // 全局添加安全认证方案 (例如 JWT)
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("BearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("请在下方输入 JWT Token，格式：Bearer <token>")));
    }

    // 可选：全局过滤掉某些包或路径
    @Bean
    public OpenApiCustomizer globalOpenApiCustomizer() {
        return openApi -> {
            // 这里可以动态修改 OpenAPI 对象，例如隐藏某些接口
            // openApi.getPaths().remove("/internal/health");
        };
    }
}
