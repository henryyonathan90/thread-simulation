package henry.jonathan.test.nio.reactive.server.controller;

import henry.jonathan.test.nio.reactive.server.service.WebclientVsFeignclientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestController {

    @Autowired
    private WebclientVsFeignclientService webclientVsFeignclientService;

    @Autowired
    private Scheduler schedulers;

    @GetMapping("mono")
    public Mono<Integer> mono() {
        return Mono.just(0)
                .publishOn(Schedulers.elastic())
                .map(n -> {
                    log.info("First map");
                    return n + 1;
                }).map(n -> {
                    log.info("Second map");
                    return n + 2;
                });
    }

    @GetMapping("flux")
    public Mono<String> flux() {
        return Flux.range(0, 100)
                .publishOn(Schedulers.elastic())
                .map(n -> {
                    log.info("Log from " + n);
                    return n;
                }).then(Mono.just("Success"));
    }

    @GetMapping("parallelflux")
    public Mono<Long> parallelflux() {
        return Flux.range(0, 100)
                .parallel(4)
                .runOn(Schedulers.parallel())
                .map(n -> {
                    log.info("Log from " + n);
                    return n;
                })
                .sequential()
                .count();
    }

}
