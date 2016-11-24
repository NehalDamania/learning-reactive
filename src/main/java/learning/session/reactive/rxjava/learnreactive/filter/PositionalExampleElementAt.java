package learning.session.reactive.rxjava.learnreactive.filter;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;

public class PositionalExampleElementAt {
    
    public static void main(String[] args) {

        // Emit the 3rd letter in the greek alphabet
        // Gamma
        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
                .elementAt(2)
                .subscribe((letter) -> {
                    System.out.println(letter);
                });

        System.out.println();
        
        // Emit the 50th letter in the greek alphabet
        // ...there isn't a 50th letter, so we want to get "Unknown"
        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
                .elementAt(50)
                .defaultIfEmpty("Default")
                .subscribe((letter) -> {
                    System.out.println(letter);
                });

        System.out.println();
        
        
        
        System.exit(0);
    }
}
