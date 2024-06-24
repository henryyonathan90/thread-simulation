package henry.jonathan.thread.simulation.service;

import java.util.concurrent.ExecutionException;

public interface GenericService {

    String doSomethingMultiThreadpools() throws ExecutionException, InterruptedException;

    String doSomething() throws ExecutionException, InterruptedException;
}
