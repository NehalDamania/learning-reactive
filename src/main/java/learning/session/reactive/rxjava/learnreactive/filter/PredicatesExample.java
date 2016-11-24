package learning.session.reactive.rxjava.learnreactive.filter;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;

public class PredicatesExample {

    public static void main(String[] args) {

        // Create an observable from a big list of integers (0...199)
        // ...use a predicate (filter) to only return numbers divisible by 3 and 
        // less than 20...
        Flowable.fromIterable(DataGenerator.generateBigIntegerList())
                .filter((i) -> {
                    return ((i % 3 == 0) && (i < 20));
                })
                .subscribe((i) -> {
                    System.out.println(i);
                });

        System.exit(0);
    }
}
