package henry.jonathan.thread.simulation.service;

import henry.jonathan.thread.simulation.outbound.SlowAppFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.UUID;

@Service
@Slf4j
public class WebclientVsFeignclientServiceImpl implements WebclientVsFeignclientService {

    @Autowired
    private WebClient slowServiceWebClient;

    @Autowired
    private SlowAppFeignClient feignClient;

    @Autowired
    private Scheduler scheduler1;
    @Autowired
    private Scheduler scheduler2;
    @Autowired
    private Scheduler scheduler3;

    @Override
    public Mono<String> testWebClient() {
        return Mono.fromCallable(this::generateRequestId).subscribeOn(scheduler1)
                .publishOn(scheduler2).flatMap(this::nonBlockingIOCall)
                .publishOn(scheduler3).map(this::loggingResponse);
    }

    @Override
    public Mono<String> testFeignClient() {
        return Mono.fromCallable(this::generateRequestId).subscribeOn(scheduler1)
                .publishOn(scheduler2).map(this::blockingIOCall)
                .publishOn(scheduler3).map(this::loggingResponse);
    }

    private String generateRequestId() {
        log.info("Generating requestId");
        String uuid = UUID.randomUUID().toString();
        log.info("Generated requestId: {}", uuid);
        return uuid;
    }

    private Mono<String> nonBlockingIOCall(String requestId) {
        log.info("Running nonBlockingIOCall for requestId {}", requestId);

        return slowServiceWebClient
                .get()
                .uri("api/slow/io?requestId=" + requestId)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class));
    }

    private String blockingIOCall(String requestId) {
        log.info("Running blockingIOCall for requestId {}", requestId);
        return feignClient.testSlowIO(requestId);
    }

    private String loggingResponse(String response) {
        log.info("Logging response: {}", response);

        return "success";
    }
}
