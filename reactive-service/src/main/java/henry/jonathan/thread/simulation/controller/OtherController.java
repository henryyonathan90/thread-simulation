package henry.jonathan.thread.simulation.controller;

import henry.jonathan.thread.simulation.service.WebclientVsFeignclientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@RestController
@RequestMapping("/api/")
@Slf4j
public class OtherController {

    @Autowired
    private WebclientVsFeignclientService webclientVsFeignclientService;

    @Autowired
    private Scheduler scheduler2;

    @GetMapping("other")
    public Mono<String> get() {
        log.info("Process other api");
        return Mono.just("success").subscribeOn(scheduler2);
    }

}
