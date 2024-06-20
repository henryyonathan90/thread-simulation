package henry.jonathan.thread.simulation.service;

import java.util.concurrent.ExecutionException;

public interface GenericService {

    String doSomething() throws ExecutionException, InterruptedException;

}
