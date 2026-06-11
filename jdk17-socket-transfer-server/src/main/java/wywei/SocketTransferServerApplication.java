package wywei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author WangYunwei [2026-03-02]
 */
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class SocketTransferServerApplication {
    public static void main(String[] args) {

        SpringApplication.run(SocketTransferServerApplication.class, args);
    }
}
