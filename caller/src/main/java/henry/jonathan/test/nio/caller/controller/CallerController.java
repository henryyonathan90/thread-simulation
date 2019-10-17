package henry.jonathan.test.nio.caller.controller;

import henry.jonathan.test.nio.caller.service.CallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/test/")
public class CallerController {

  @Autowired
  private CallerService callerService;

  @GetMapping("webclient")
  public Mono<Boolean> webclient(@RequestParam int id) {
    return callerService.testWebClient(id);
  }

  @GetMapping("feignclient")
  public Mono<Boolean> feignclient(@RequestParam int id) {
    return callerService.testFeignClient(id);
  }

}
