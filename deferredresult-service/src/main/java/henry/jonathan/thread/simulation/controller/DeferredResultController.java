package henry.jonathan.thread.simulation.controller;

import henry.jonathan.thread.simulation.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/")
@Slf4j
public class DeferredResultController {

    @Autowired
    private GenericService genericService;

    @GetMapping(value = "deferredresult", consumes = "application/json")
    public DeferredResult<String> deferredResult() {
        String requestId = UUID.randomUUID().toString();
        log.info("Incoming request with id {}", requestId);

        DeferredResult<String> output = new DeferredResult<>(10000L);

        CompletableFuture.runAsync(() -> {
            try {
                output.setResult(genericService.doSomething(requestId));
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        log.info("Returning deferred result for request with id {}", requestId);
        return output;
    }

}
