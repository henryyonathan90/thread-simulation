package henry.jonathan.thread.simulation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class BeanConfig {

    @Bean
    public ExecutorService executorService1() {
        return Executors.newFixedThreadPool(32);
    }

    @Bean
    public ExecutorService executorService2() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    public ExecutorService executorService3() {
        return Executors.newFixedThreadPool(32);
    }
}
