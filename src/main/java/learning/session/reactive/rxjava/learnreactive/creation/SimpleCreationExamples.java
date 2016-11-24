package learning.session.reactive.rxjava.learnreactive.creation;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;

public class SimpleCreationExamples {

    public static void main(String[] args) {
        Flowable<Integer> observable = null;

        System.out.println("---------------------------------------------");
        System.out.println("Observable creation from a single value");
        System.out.println("---------------------------------------------");
        observable = Flowable.just(Integer.valueOf(42));
        observable.subscribe((i) -> {
            System.out.println(i);
        });

        System.out.println("---------------------------------------------");
        System.out.println("Observable creation from an Iterable");
        System.out.println("---------------------------------------------");
        observable = Flowable.fromIterable(DataGenerator.generateFibonacciList());
        observable.subscribe((i) -> {
            System.out.println(i);
        });

        System.out.println("---------------------------------------------");
        System.out.println("Observable creation from an Array");
        System.out.println("---------------------------------------------");
        observable = Flowable.fromArray(DataGenerator.generateFibonacciArray());
        observable.subscribe((i) -> {
            System.out.println(i);
        });

        System.exit(0);
    }
}
