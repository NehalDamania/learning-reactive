package learning.session.reactive.rxjava.learnreactive.conditional;

import io.reactivex.Flowable;

public class BooleanConditionalContainsAndExists {

    public static void main(String[] args) {

        Flowable.range(1, 200)
                .contains(100)
                .subscribe((b) -> {
                    if (b == true) {
                        System.out.println("contains evaluated to true - the number 100 is present");
                    } else {
                        System.out.println("contains evaluated to false - the number 100 is not present");
                    }
                });

        System.out.println();
        
        // Create an observable from out big list of integers
        Flowable.range(1, 200)
                .any( (i) -> { return i == 100; } )
                // Subscribe to the boolean result.
                .subscribe((b) -> {
                    if (b == true) {
                        System.out.println("exists evaluated to true - the number 100 exists in the list");
                    } else {
                        System.out.println("exists evaluated to false - the number 100 does not exist in the list");
                    }
                });

        System.out.println();
       
        System.exit(0);
    }
}
