package henry.jonathan.thread.simulation.service;

import reactor.core.publisher.Mono;

public interface WebclientVsFeignclientService {

    Mono<Boolean> testWebClient(int n);

    Mono<Boolean> testFeignClient(int n);
}
