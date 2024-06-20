package henry.jonathan.thread.simulation.config;

import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
public class BeanConfig {

    @Bean
    public WebClient slowServiceWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8081/")
                .build();
    }

    @Bean
    public Scheduler scheduler1() {
        return Schedulers.newParallel("testScheduler1", 1);
    }

    @Bean
    public Scheduler scheduler2() {
        return Schedulers.newBoundedElastic(1, 10000, "testScheduler2");
    }

    @Bean
    public Scheduler scheduler3() {
        return Schedulers.newBoundedElastic(1, 10000, "testScheduler3");
    }

    @Bean
    public Decoder feignDecoder() {

        ObjectFactory<HttpMessageConverters> messageConverters = () -> {
            HttpMessageConverters converters = new HttpMessageConverters();
            return converters;
        };
        return new SpringDecoder(messageConverters);
    }
}
