package henry.jonathan.thread.simulation.service;

import henry.jonathan.thread.simulation.outbound.SlowAppFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class GenericServiceImpl implements GenericService {

    @Autowired
    private SlowAppFeignClient feignClient;

    @Autowired
    private ExecutorService executorService1;
    @Autowired
    private ExecutorService executorService2;
    @Autowired
    private ExecutorService executorService3;

    @Override
    public String doSomethingMultiThreadpools() throws ExecutionException, InterruptedException {
        log.info("Starting async request process");

        return CompletableFuture.supplyAsync(this::generateRequestId, executorService1)
                .thenApplyAsync(this::callDownstream, executorService2)
                .thenApplyAsync(this::loggingResponse, executorService3)
                .get();
    }

    @Override
    public String doSomething() throws ExecutionException, InterruptedException {
        log.info("Starting request process");

        return Stream.of(generateRequestId())
                .map(this::callDownstream)
                .map(this::loggingResponse)
                .collect(Collectors.joining());
    }

    private String generateRequestId() {
        log.info("Generating requestId");
        String uuid = UUID.randomUUID().toString();
        log.info("Generated requestId: {}", uuid);
        return uuid;
    }

    private String callDownstream(String requestId) {
        log.info("Calling downstream for requestId {}", requestId);
        return feignClient.testSlowIO(requestId);
    }

    private String loggingResponse(String response) {
        log.info("Logging response: {}", response);
        return response;
    }
}
