package learning.session.reactive.rxjava.learnreactive.filter;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;

public class PositionalExampleFirstAndLastWithPredicate {
    
    public static void main(String[] args) {

        // Emit the greek alphabet, but only the first letter
        // that matches our predicate
        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
        		.filter((letter) -> { return letter.equals( "Beta" ); })
                .firstElement(  )
                .subscribe((letter) -> {
                    System.out.println(letter);
                });

        System.out.println();

        System.exit(0);
    }
}
