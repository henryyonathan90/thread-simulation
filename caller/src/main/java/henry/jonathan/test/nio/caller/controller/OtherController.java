package henry.jonathan.test.nio.caller.controller;

import henry.jonathan.test.nio.caller.service.CallerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@RestController
@RequestMapping("/api/other")
@Slf4j
public class OtherController {

  @Autowired
  private CallerService callerService;

  @Autowired
  private Scheduler schedulers;

  @GetMapping
  public Mono<String> get() {
    log.info("Process other api");
    return Mono.just("success").subscribeOn(schedulers);
  }

}
