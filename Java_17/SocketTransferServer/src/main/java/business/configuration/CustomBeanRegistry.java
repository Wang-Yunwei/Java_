package business.configuration;

import business.controller.dji.service.IDjiService;
import business.controller.tyjw.dto.AuthSingleton;
import business.controller.tyjw.service.ITyjwService;
import business.controller.websocket.service.IWebSocketService;
import business.util.ExecShell;
import business.util.MQClient;
import io.minio.MinioClient;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangYunwei [2024-08-10]
 */
@Slf4j
@Configuration
public class CustomBeanRegistry {

    private final IWebSocketService webSocketService;
    private final ITyjwService tyjwService;
    private final IDjiService djiService;
    private final MQClient mqClient;

    public CustomBeanRegistry(IWebSocketService webSocketService, ITyjwService tyjwService, IDjiService djiService, MQClient mqClient) {
        this.webSocketService = webSocketService;
        this.tyjwService = tyjwService;
        this.djiService = djiService;
        this.mqClient = mqClient;
    }

    private final AuthSingleton auth = AuthSingleton.getInstance();

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Bean
//    CommandLineRunner startImmediatelyExecute() {
    ApplicationRunner startImmediatelyExecute() {
        return args -> {
            log.info("================== 【START-UP SUCCESSFUL】 ==================");
            if ("Windows10".equals(ExecShell.exec("hostname"))) {
                djiService.startUdpListening();
                webSocketService.startWebListening();
            } else {
                tyjwService.getToken();
                if (null != auth.getCompanyId() && StringUtils.isNoneBlank(auth.getAccessToken())) {
                    log.info("Access token: {}", auth.getAccessToken());
                    tyjwService.startTcpConnect();
                }
                djiService.startUdpListening();
                webSocketService.startWebListening();
                mqClient.createMqClient();
            }
        };
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("SocketTransferServer").description("This is a forwarding service").version("20140712"));
    }

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
    }
}
