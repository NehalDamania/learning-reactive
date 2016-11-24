package learning.session.reactive.rxjava.learnreactive.creation;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;

public class FutureCreationExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Flowable<List<Integer>> flowableFutureList;

        // Create a FutureTask that returns a List<Integer>
        FutureTask<List<Integer>> future = new FutureTask<>(() -> {
            return DataGenerator.generateFibonacciList();
        });
        
        // Construct an observable...note that this only creates the
        // observable wrapper around the future.  The future still needs 
        // to be executed using it's "run" method, or by scheduling it to 
        // execute.
        flowableFutureList = Flowable.fromFuture(future);

        // Schedule this future to run on the computation scheduler
        Schedulers.computation().scheduleDirect(() -> {
            future.run();   // Call the FutureTask's run method
        });

        // Subscribe to the list...when the list is ready through the 
        // future, iterate and print each element.
        flowableFutureList.subscribe((list) -> {
            list.forEach((i) -> {
                System.out.println(i);
            });
        });

        System.exit(0);
    }
}
