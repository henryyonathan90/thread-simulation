package henry.jonathan.test.nio.caller.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfig {

  @Bean
  public WebClient slowServiceWebClient() {
    WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:8081/")
        .defaultHeader(HttpHeaders.ACCEPT, "application/json")
        .build();
    return webClient;
  }
}
