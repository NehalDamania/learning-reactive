package learning.session.reactive.rxjava.learnreactive.filter;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;

public class PositionalExampleFirstAndLast {

    public static void main(String[] args) {

        // Emit the greek alphabet, but only the first letter
        // Alpha
        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
                .first("default")
                .subscribe((letter) -> {
                    System.out.println(letter);
                });

        System.out.println();

        // Emit the greek alphabet, but only the first 4
        // Alpha, Beta, Gamma, Delta
        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
                .take(4)
                .subscribe((letter) -> {
                    System.out.println(letter);
                });

        System.out.println();

        // Emit the greek alphabet, but only the last letter
        // Omega
        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
                .last("default")
                .subscribe((letter) -> {
                    System.out.println(letter);
                });

        System.out.println();

        // Emit the greek alphabet, but this time only the last 4
        // Phi, Chi, Psi, Omeaga
        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
                .takeLast(4)
                .subscribe((letter) -> {
                    System.out.println(letter);
                });

        System.out.println();

        // firstOrDefault and lastOrDefault allows you to handle the case where there is 
        // an empty list.
        Flowable.empty()
                .first("List is empty!")
                .subscribe((letter) -> {
                    System.out.println(letter);
                });
        Flowable.empty()
                .last("List is empty!")
                .subscribe((letter) -> {
                    System.out.println(letter);
                });

        System.exit(0);
    }
}
