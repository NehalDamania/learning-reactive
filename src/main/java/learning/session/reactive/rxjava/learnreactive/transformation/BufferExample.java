package learning.session.reactive.rxjava.learnreactive.transformation;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.ThreadUtils;

public class BufferExample {

    public static void main(String[] args) {


        Flowable.interval(100, TimeUnit.MILLISECONDS)
                // We want to buffer and emit once every second...
                .buffer(1, TimeUnit.SECONDS)
                .subscribe(
                        // Each second, we will get a list of longs that were 
                        // emitted.
                        (list) -> {
                            System.out.println("----------------------------");

                            // We will write things out in a way that we can
                            // see what it happening.
                            int count = 1;
                            int size = list.size();
                            for (int i = 0; i < size; i++) {
                                System.out.println("" + (count++) + ": " + list.get(i));
                            }
                        }
                );

        // Do this for 5 seconds so we can see the effect.
        ThreadUtils.sleep(5000);
        

        System.exit(0);
    }

}
