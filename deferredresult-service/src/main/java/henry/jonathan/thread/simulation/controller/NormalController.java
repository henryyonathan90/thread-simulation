package henry.jonathan.thread.simulation.controller;

import henry.jonathan.thread.simulation.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/api/")
@Slf4j
public class NormalController {

    @Autowired
    private GenericService genericService;

    @Autowired
    private ExecutorService executorServiceCommon;

    @GetMapping(value = "normal", consumes = "application/json")
    public String deferredResult() throws ExecutionException, InterruptedException {
        log.info("Start processing a request");

        return genericService.doSomething();
    }

}
