package learning.session.reactive.rxjava.learnreactive.transformation;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;

public class MapExample {

    public static void main(String[] args) {

        // Simple map example...transform every greek letter string
        // into upper case.
        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
                .map((letterString) -> {
                    return letterString.toUpperCase();
                })
                .subscribe((letterString) -> {
                    System.out.println(letterString);
                });

        System.out.println("--------------------------------------------------");

        // flatMap -> Each greek letter is emitted as all upper and 
        // all lower case...doubling the output.  One item in the origin
        // list generates multiple items.
        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
                .flatMap((letterString) -> {
                    String[] returnStrings = {letterString.toUpperCase(),
                        letterString.toLowerCase()
                    };
                    return Flowable.fromArray(returnStrings);
                })
                .subscribe((letterString) -> {
                    System.out.println(letterString);
                });
        System.out.println("--------------------------------------------------");
        
        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
        .concatMap((letterString) -> {
        	String[] returnStrings = {letterString.toUpperCase(),
        			letterString.toLowerCase()
        	};
        	return Flowable.fromArray(returnStrings);
        })
        .subscribe((letterString) -> {
        	System.out.println(letterString);
        });

        System.exit(0);
    }
}
