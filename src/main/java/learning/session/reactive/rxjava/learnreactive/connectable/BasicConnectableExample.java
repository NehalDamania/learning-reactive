package learning.session.reactive.rxjava.learnreactive.connectable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;
import learning.session.reactive.rxjava.learnreactive.util.ThreadUtils;

public class BasicConnectableExample {

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

        // But notice how for 3 seconds nothing happens until we call "connect"
        System.out.println("Sleeping for 3 seconds...");
        ThreadUtils.sleep(3000);

        // Now we call "connect" on the connectable observable...and things start happening...
        System.out.println("Connecting...");
        connectable.connect();

        // Let the ticker do its thing for 3 seconds so we can see events
        // flowing...
        ThreadUtils.sleep(3000);
        System.out.println("Three seconds are up!");
        
        System.exit(0);
    }
}
