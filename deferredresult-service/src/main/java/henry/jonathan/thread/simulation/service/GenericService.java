package henry.jonathan.thread.simulation.service;

import java.util.concurrent.ExecutionException;

public interface GenericService {

    String doSomething(String requestId) throws ExecutionException, InterruptedException;

}
