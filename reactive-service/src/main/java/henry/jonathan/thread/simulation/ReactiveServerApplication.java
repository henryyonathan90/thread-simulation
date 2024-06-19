package henry.jonathan.thread.simulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReactiveServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveServerApplication.class, args);
    }
}
