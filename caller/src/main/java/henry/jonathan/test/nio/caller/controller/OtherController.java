package henry.jonathan.test.nio.caller.controller;

import henry.jonathan.test.nio.caller.service.CallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/other")
public class OtherController {

  @Autowired
  private CallerService callerService;

  @GetMapping
  public Mono<String> get() {
    return Mono.just("success");
  }

}
