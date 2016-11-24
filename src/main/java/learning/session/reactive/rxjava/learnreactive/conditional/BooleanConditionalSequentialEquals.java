package learning.session.reactive.rxjava.learnreactive.conditional;

import io.reactivex.Flowable;

public class BooleanConditionalSequentialEquals {

    public static void main(String[] args) {

        // Create two equivelent observables...
        Flowable<Integer> compareList = Flowable.range(1, 100);
        Flowable<Integer> compareList2 = Flowable.range(1, 100);
        
        Flowable.sequenceEqual(compareList, compareList2)
                .subscribe((b) -> {
                    if (b == true) {
                        System.out.println("sequenceEqual is true - both sequences match");
                    } else {
                        System.out.println("sequenceEqual is false - sequences do not match");
                    }
                });

        System.out.println();

        compareList2 = Flowable.range(1, 200);
        
        // See if the sequences are equals
        Flowable.sequenceEqual(compareList, compareList2)
                .subscribe((b) -> {
                    if (b == true) {
                        System.out.println("sequenceEqual is true - both sequences match");
                    } else {
                        System.out.println("sequenceEqual is false - sequences do not match");
                    }
                });


        System.exit(0);
    }
}
