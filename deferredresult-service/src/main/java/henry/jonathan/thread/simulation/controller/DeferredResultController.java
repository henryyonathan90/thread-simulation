package henry.jonathan.thread.simulation.controller;

import henry.jonathan.thread.simulation.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/api/")
@Slf4j
public class DeferredResultController {

    @Autowired
    private GenericService genericService;

    @Autowired
    private ExecutorService executorServiceCommon;

    @GetMapping(value = "deferredresult", consumes = "application/json")
    public DeferredResult<String> deferredResult() {
        log.info("Start processing a request");

        DeferredResult<String> output = new DeferredResult<>(10000L);

        CompletableFuture.runAsync(() -> {
            try {
                output.setResult(genericService.doSomethingMultiThreadpools());
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, executorServiceCommon);

        log.info("Returning a deferred result for the request");
        return output;
    }

}
