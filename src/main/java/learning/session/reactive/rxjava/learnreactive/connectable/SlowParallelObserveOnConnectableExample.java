package learning.session.reactive.rxjava.learnreactive.connectable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.schedulers.Schedulers;
import learning.session.reactive.rxjava.learnreactive.util.ThreadUtils;

public class SlowParallelObserveOnConnectableExample {

    public static void main(String[] args) {

        Flowable<Long> ticker = Flowable.intervalRange(1, 10, 500, 500, TimeUnit.MILLISECONDS);
		ConnectableFlowable<Long> connectable = 
        		ticker.publish();

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
                            ThreadUtils.sleep(1000);
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
        // One of our observers is slow...see what happens...
        ThreadUtils.sleep(10000);
        
        System.out.println( "Notice how the second observer continued to process its scheduled work...");
        System.exit(0);
    }
}
