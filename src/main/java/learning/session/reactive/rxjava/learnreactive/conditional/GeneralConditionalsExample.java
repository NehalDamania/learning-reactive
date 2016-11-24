package learning.session.reactive.rxjava.learnreactive.conditional;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;

public class GeneralConditionalsExample {

    public static void main(String[] args) {

        Flowable.empty()
                .defaultIfEmpty("Hello World")
                .subscribe((s) -> {
                    System.out.println(s);
                });

        System.out.println();

        Flowable.fromIterable(DataGenerator.generateFibonacciList())
                .skipWhile((i) -> {
                    return i < 8;
                })
                .subscribe((i) -> {
                    System.out.println(i);
                });

        System.out.println();

        Flowable.fromIterable(DataGenerator.generateFibonacciList())
                .takeWhile((i) -> {
                    return i < 8;
                })
                .subscribe((i) -> {
                    System.out.println(i);
                });

        System.out.println();

        System.exit(0);

    }

}
