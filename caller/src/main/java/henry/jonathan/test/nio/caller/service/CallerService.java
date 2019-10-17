package henry.jonathan.test.nio.caller.service;

import reactor.core.publisher.Mono;

public interface CallerService {

  Mono<Boolean> testWebClient(int n);

  Mono<Boolean> testFeignClient(int n);
}
