package learning.session.reactive.rxjava.learnreactive.subject;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;

public class BehaviorProcessorExample2 {

    public static void main(String[] args) {
        // Create an BehaviorSubject using its factory method
    	BehaviorProcessor<String> subject = BehaviorProcessor.createDefault("Start State");

        // We want to subscribe to this subject
        subject.subscribe(
                (letter) -> {
                    System.out.println(letter);
                }
        );
        subject.subscribe(
                (letter) -> {
                    System.out.println("Second Subscriber: " + letter);
                });


        // Next we create an observable out of the greek alphabet...
        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
                .subscribe(
                        (letter) -> {
                            // ...for each letter, we will emit an event to the subject
                            subject.onNext(letter);
                        },
                        (t) -> {
                            subject.onError(t);
                        },
                        // ...once complete...we tell the subject
                        () -> {
                            System.out.println( "onCompleted" );
                            subject.onComplete();
                        });

        // Note that we see every event on both subscribers.  Processors are 
        // an easy way to "multicast" events to multiple subscribers.
        // A Processor is both a Publisher and a Subscriber at the same time.
        
        System.exit(0);
    }
}
