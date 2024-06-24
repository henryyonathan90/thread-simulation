package henry.jonathan.thread.simulation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/slow/io")
@Slf4j
public class SlowController {

    @GetMapping(produces = "application/json")
    public String slowIO(@RequestParam String requestId) {
        try {
            log.info("#slowIO processing request id {}, sleep for few second", requestId);
            Thread.sleep(5000);
            log.info("#slowIO finished processing request id {}", requestId);
        } catch (Exception ex) {
            log.error(null, ex);
        }

        return "success";
    }
}
