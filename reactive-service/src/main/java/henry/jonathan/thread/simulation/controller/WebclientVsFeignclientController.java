package henry.jonathan.thread.simulation.controller;

import henry.jonathan.thread.simulation.service.WebclientVsFeignclientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/")
public class WebclientVsFeignclientController {

    @Autowired
    private WebclientVsFeignclientService webclientVsFeignclientService;

    @GetMapping("webclient")
    public Mono<String> webclient() {
        return webclientVsFeignclientService.testWebClient();
    }

    @GetMapping("feignclient")
    public Mono<String> feignclient() {
        return webclientVsFeignclientService.testFeignClient();
    }

}
