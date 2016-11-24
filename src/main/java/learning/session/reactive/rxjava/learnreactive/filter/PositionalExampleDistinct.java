package learning.session.reactive.rxjava.learnreactive.filter;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;

public class PositionalExampleDistinct {
    
    public static void main(String[] args) {

        Flowable.fromIterable(DataGenerator.generateScrambledAndDuppedGreekAlphabet())
                .subscribe((letter) -> {
                    System.out.println(letter);
                });
        
        System.out.println("------------------------------------------------------------");
        
        // Emit each string value only once, even if it appears in the 
        // original list multiple times.
        Flowable.fromIterable(DataGenerator.generateScrambledAndDuppedGreekAlphabet())
                .distinct()
                .subscribe((letter) -> {
                    System.out.println(letter);
                });
        
        System.out.println("------------------------------------------------------------");
        
        System.exit(0);
    }
}
