package henry.jonathan.test.nio.caller.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class CallerServiceImpl implements CallerService {

  @Autowired
  private WebClient slowServiceWebClient;

  @Autowired
  private SlowAppFeignClient feignClient;

  @Autowired
  private Scheduler schedulers;

  @Override
  public Mono<Boolean> testWebClient(int id) {
    return Mono.just("")
        .subscribeOn(schedulers)
        .map(s -> {
          log.info("#testWebClient start calling slow app for id {}", id);
          return s;
        })
        .flatMap(s -> slowServiceWebClient.get()
            .uri("api/slow/io?id=" + id)
            .exchange()
            .flatMap(clientResponse -> clientResponse.bodyToMono(String.class)
                .subscribeOn(schedulers))
            .map(bodyString -> {
              log.info("#testWebClient finished calling slow app for id {}", id);
              return bodyString;
            })
            .map(integer -> Boolean.TRUE));
  }

  @Override
  public Mono<Boolean> testFeignClient(int id) {
    return Mono.just("").subscribeOn(schedulers).flatMap(s -> {
      log.info("#testFeignClient start calling slow app for id {}", id);
      String result = feignClient.testSlowIO(id);
      log.info("#testFeignClient finished calling slow app for id {}", id);
      return Mono.just(result);
    }).map(string -> Boolean.TRUE);
  }

}
