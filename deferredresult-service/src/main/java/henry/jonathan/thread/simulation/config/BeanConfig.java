package henry.jonathan.thread.simulation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class BeanConfig {

    @Bean
    public ExecutorService executorService1() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Bean
    public ExecutorService executorService2() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    public ExecutorService executorService3() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Bean
    public ExecutorService executorServiceCommon() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
