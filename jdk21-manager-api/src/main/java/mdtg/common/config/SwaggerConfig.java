package mdtg.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置
 */
@Configuration
public class SwaggerConfig {

//    @Bean
//    public GroupedOpenApi deviceApi() {
//        return GroupedOpenApi.builder()
//                .group("device")
//                .pathsToMatch("/device/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi agentApi() {
//        return GroupedOpenApi.builder()
//                .group("agent")
//                .pathsToMatch("/agent/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi modelApi() {
//        return GroupedOpenApi.builder()
//                .group("models")
//                .pathsToMatch("/models/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi oatApi() {
//        return GroupedOpenApi.builder()
//                .group("ota")
//                .pathsToMatch("/ota/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi timbreApi() {
//        return GroupedOpenApi.builder()
//                .group("timbre")
//                .pathsToMatch("/ttsVoice/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi sysApi() {
//        return GroupedOpenApi.builder()
//                .group("admin")
//                .pathsToMatch("/admin/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi userApi() {
//        return GroupedOpenApi.builder()
//                .group("user")
//                .pathsToMatch("/user/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi configApi() {
//        return GroupedOpenApi.builder()
//                .group("config")
//                .pathsToMatch("/config/**")
//                .build();
//    }
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI().info(new Info()
//                .title("xiaozhi-esp32-manager-api")
//                .description("xiaozhi-esp32-manager-api文档")
//                .version("3.0")
//                .termsOfService("https://127.0.0.1"));
//    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MDTG Robot Server API")
                        .version("1.0.0")
                        .description("机器人管理服务接口文档，基于 Knife4j 增强"))
                // 全局添加安全认证方案 (例如 JWT)
                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                .components(new Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme()
                                        .name("Authorization") // Header 的名称
                                        .type(SecurityScheme.Type.APIKEY) // 类型：API Key
                                        .in(SecurityScheme.In.HEADER) // 位置：Header
                                        .description("请输入 Bearer Token") // 描述
                        ));
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