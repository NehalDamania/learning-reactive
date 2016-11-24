package learning.session.reactive.rxjava.learnreactive.creation;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import learning.session.reactive.rxjava.learnreactive.util.ThreadUtils;

public class ObserveOnThreadExample {

    public static void main(String[] args) {

        // Create and sync on an object that we will use to make sure we don't
        // hit the System.exit(0) call before our threads have had a chance
        // to complete.
        Object waitMonitor = new Object();
        synchronized (waitMonitor) {

            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("Creating an Observable that sprcifies an observeOn Scheduler");
            System.out.println("driving thread: " + ThreadUtils.currentThreadName());
            System.out.println("---------------------------------------------------------------------------------------");


            // ...and wrap it in an Observable
//            Flowable<Integer> flowable = Flowable.fromIterable(emitList);
            Flowable<Long> flowable = Flowable.intervalRange(1, 100, 1, 1, TimeUnit.SECONDS);

            // Dot chain on the observable...
            flowable
                    .observeOn(Schedulers.io())
                    .subscribe(
                            // onNext
                            (i) -> {
                                System.out.println("onNext thread entr: " + ThreadUtils.currentThreadName());
                                System.out.println(i);
                                System.out.println("onNext thread exit: " + ThreadUtils.currentThreadName());
                            },
                            // onError
                            (t) -> {
                                t.printStackTrace();
                            },
                            // onCompleted
                            () -> {
                                System.out.println("onCompleted()");

                                // Since we have completed...we sync on the waitMonitor
                                // and then call notify to wake up the "main" thread.
                                synchronized (waitMonitor) {
                                    waitMonitor.notify();
                                }
                            }
                    );

            // Wait until the onCompleted method wakes us up.
            ThreadUtils.wait(waitMonitor);
        }

        System.exit(0);
    }
}
