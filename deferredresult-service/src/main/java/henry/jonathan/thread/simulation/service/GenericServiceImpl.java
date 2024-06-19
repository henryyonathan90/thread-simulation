package henry.jonathan.thread.simulation.service;

import henry.jonathan.thread.simulation.outbound.SlowAppFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

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
    public String doSomething(String requestId) throws ExecutionException, InterruptedException {
        log.info("Starting async request process for requestId {}", requestId);

        return CompletableFuture.supplyAsync(() -> process1(requestId), executorService1)
                .thenApplyAsync(s -> process2(requestId), executorService2)
                .thenApplyAsync(s -> process3(requestId), executorService3)
                .get();
    }

    private String process1(String requestId) {
        log.info("Starting process 1 for requestId {}", requestId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "true";
    }

    private String process2(String requestId) {
        log.info("Starting process 2 for requestId {}", requestId);
        return feignClient.testSlowIO(requestId);
    }

    private String process3(String requestId) {
        log.info("Starting process 3 for requestId {}", requestId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "true";
    }
}
