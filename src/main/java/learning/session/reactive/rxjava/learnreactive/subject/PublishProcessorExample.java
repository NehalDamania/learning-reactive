package learning.session.reactive.rxjava.learnreactive.subject;

import io.reactivex.Flowable;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;
import learning.session.reactive.rxjava.learnreactive.util.ThreadUtils;

public class PublishProcessorExample {

    public static void main(String[] args) {

        Object signal = new Object();
        synchronized (signal) {
            
            // PublishProcessors are good for multicasting events to subscribers.
            // The subscribers will see only the events that happen AFTER they
            // subscribe.
            // Create an PublishProcessor using its factory method
            PublishProcessor<String> subject = PublishProcessor.create();

            // We want to subscribe to this subject
            subject.subscribe(
                    (letter) -> {
                        System.out.println("Subscriber 1: " + letter);
                        
                        ThreadUtils.sleep(500);
                        if( letter.equals( "Eta" ) ) {
                            synchronized(signal) {
                                signal.notify();
                            }
                        }
                    }
            );

            // Next we create an observable out of the greek alphabet...
            // For each letter, we will publish an event to the PublishProcessor
            Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
                    .subscribeOn(Schedulers.computation())
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
                                System.out.println("Subscriber 1: onCompleted");
                                subject.onComplete();
                                synchronized( signal ) {
                                    signal.notify();
                                }
                            });

            ThreadUtils.wait(signal);

            subject.subscribe(
                    (letter) -> {
                        System.out.println("Subscriber 2: " + letter);
                    },
                    (t) -> {
                        subject.onError(t);
                    },
                    () -> {
                        System.out.println("Subscriber 2: onCompleted");
                    }
            );
            
            ThreadUtils.wait(signal);
        }
        System.exit(0);
    }
}
