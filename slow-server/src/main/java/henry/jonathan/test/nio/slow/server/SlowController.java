package henry.jonathan.test.nio.slow.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slow/io")
@Slf4j
public class SlowController {

  @GetMapping
  public String slowIO(@RequestParam int id) {
    try {
      log.info("#slowIO processing request id {}, sleep for 5 second", id);
      Thread.sleep(10000);
      log.info("#slowIO finished processing request id {}", id);
    } catch (Exception ex) {
      log.error(null, ex);
    }

    return "success";
  }
}
