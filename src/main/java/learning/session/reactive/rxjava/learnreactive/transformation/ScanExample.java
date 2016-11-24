package learning.session.reactive.rxjava.learnreactive.transformation;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;

public class ScanExample {

    public static void main(String[] args) {

        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
                .scan(new StringBuilder(), (accumBuffer, nextLetter) -> {
                    return accumBuffer.append(nextLetter);
                })
                .subscribe((total) -> {
                    System.out.println("Scan Event: " + total.toString());
                });

        System.out.println("--------------------------------------------------");
       
        Flowable.fromIterable(DataGenerator.generateGreekAlphabet())
                .reduce(new StringBuilder(), (accumBuffer, nextLetter) -> {
                    return accumBuffer.append(nextLetter);
                })
                .subscribe((total) -> {
                    System.out.println("Reduce Event: " + total.toString());
                });

        System.out.println("--------------------------------------------------");

        Flowable.range(1, 10)
        	.scan(1, (acc, nextValue) -> {
        		return acc * nextValue;
        	})
        	.subscribe((factorial) -> {
        		System.out.println("Factorial: " + factorial);
        	});
        System.exit(0);
    }
}
