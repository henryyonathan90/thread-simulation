package henry.jonathan.thread.simulation.service;

import reactor.core.publisher.Mono;

public interface WebclientVsFeignclientService {

    Mono<String> testWebClient();

    Mono<String> testFeignClient();
}
