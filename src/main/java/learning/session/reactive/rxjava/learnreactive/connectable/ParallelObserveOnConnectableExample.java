package learning.session.reactive.rxjava.learnreactive.connectable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.schedulers.Schedulers;
import learning.session.reactive.rxjava.learnreactive.util.ThreadUtils;

public class ParallelObserveOnConnectableExample {

    public static void main(String[] args) {

        ConnectableFlowable<Long> connectable = 
        		Flowable.interval(100, TimeUnit.MILLISECONDS).publish();

        // Next we do our subscription....
        connectable
                // We want to run this on a different thread than the ticker thread
                .observeOn(Schedulers.computation())
                .subscribe(
                        (t) -> {
                            System.out.println("Tick: " + ThreadUtils.currentThreadName() + " " + t);
                        }
                );
        
        connectable
                // We want to run this on a different thread than the ticker thread
                .observeOn(Schedulers.computation())
                .subscribe(
                        (t) -> {
                            System.out.println("Tick2: " + ThreadUtils.currentThreadName() + " " + t);
                        }
                );

        // But notice how for 3 seconds nothing happens until we call "connect"
        System.out.println("Sleeping for 3 seconds...");
        ThreadUtils.sleep(3000);

        // Now we call "connect" on the connectable observable...and things start happening...
        System.out.println("Connecting...");
        connectable.connect();

        // Let the ticker do its thing for 3 seconds so we can see events
        // flowing...
        // Notice that we are now getting parallel behavior between the observer
        // threads.
        ThreadUtils.sleep(3000);
        System.out.println("Three seconds are up!");
        
        // Stop the ticker and kill the example's VM
        System.exit(0);
    }
}
