package learning.session.reactive.rxjava.learnreactive.subject;

import io.reactivex.processors.BehaviorProcessor;

public class BehaviorProcessorExample {

    public static void main(String[] args) {
        BehaviorProcessor<String> processor = BehaviorProcessor.createDefault("Start State");

        // We want to subscribe to this subject
        processor.subscribe(
                (letter) -> {
                    System.out.println(letter);
                }
        );

        // Next we create an observable out of the greek alphabet...
//        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
//                .subscribe(
//                        (letter) -> {
//                            // ...for each letter, we will emit an event to the subject
//                            processor.onNext(letter);
//                        },
//                        (t) -> {
//                            processor.onError(t);
//                        },
//                        // ...once complete...we tell the subject
//                        () -> {
//                            System.out.println( "onCompleted" );
//                            processor.onComplete();
//                        });

        processor.onNext("one");
        // Note that we see every event including the start (default) event.
        // BehaviorState is useful for ensuring that a subject always has 
        // a state.
        
        // A second subscriber will see the current event, plus any subsequent
        // events.  In this case, since the subject has reached the completed
        // state, then that is the state that the second subscriber sees.
   
        processor.onNext("two");
        processor.subscribe(
                (letter) -> {
                    System.out.println("Second Subscriber: " + letter);
                },
                (t) -> {
                    processor.onError(t);
                },
                () -> {
                    System.out.println( "Second Subscriber: onCompleted" );
                });

        processor.onComplete();
        System.exit(0);
    }
}
