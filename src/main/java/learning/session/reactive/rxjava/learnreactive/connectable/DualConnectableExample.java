package learning.session.reactive.rxjava.learnreactive.connectable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;
import learning.session.reactive.rxjava.learnreactive.util.ThreadUtils;

public class DualConnectableExample {

    public static void main(String[] args) {

        ConnectableFlowable<Long> connectable = 
        		Flowable.interval(100, TimeUnit.MILLISECONDS).publish();

        // Next we do our subscription....
        connectable
                .subscribe(
                        (t) -> {
                            System.out.println("Tick: " + ThreadUtils.currentThreadName() + " " + t);
                        }
                );
        
        // ...and then another subscription!  This is new!  I thought you could 
        // only do one?  You can do as many as necessary, even against a normal
        // observable.  ConnectableObservable gives you control over when the 
        // underlying observable begins to emit events.
        connectable
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
        // In this scenario, we are running totally on the ticker's thread.
        // If our observer code is slow, then we will be holding up the 
        // ticker!
        ThreadUtils.sleep(3000);
        System.out.println("Three seconds are up!");
        
        System.exit(0);
    }
}
