package henry.jonathan.test.nio.reactive.server.service;

import reactor.core.publisher.Mono;

public interface WebclientVsFeignclientService {

  Mono<Boolean> testWebClient(int n);

  Mono<Boolean> testFeignClient(int n);
}
