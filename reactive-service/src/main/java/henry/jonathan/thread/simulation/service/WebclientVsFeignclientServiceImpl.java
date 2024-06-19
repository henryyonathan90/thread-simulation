package henry.jonathan.thread.simulation.service;

import henry.jonathan.thread.simulation.outbound.SlowAppFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@Slf4j
public class WebclientVsFeignclientServiceImpl implements WebclientVsFeignclientService {

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
                        .exchangeToMono(clientResponse -> clientResponse.releaseBody().subscribeOn(schedulers))
                        .map(voidBody -> {
                            log.info("#testWebClient finished calling slow app for id {}", id);
                            return Boolean.TRUE;
                        }));
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
