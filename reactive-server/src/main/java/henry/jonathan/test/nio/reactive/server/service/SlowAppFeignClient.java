package henry.jonathan.test.nio.reactive.server.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SlowAppFeignClient", url = "http://localhost:8081")
public interface SlowAppFeignClient {

  @RequestMapping(method = RequestMethod.GET, value = "api/slow/io")
  String testSlowIO(@RequestParam int id);

}
