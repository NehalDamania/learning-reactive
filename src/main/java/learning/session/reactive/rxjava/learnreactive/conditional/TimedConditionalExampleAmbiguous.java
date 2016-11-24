package learning.session.reactive.rxjava.learnreactive.conditional;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;
import learning.session.reactive.rxjava.learnreactive.util.ThreadUtils;

public class TimedConditionalExampleAmbiguous {

    public static void main(String[] args) {

    	Flowable<String> sequence1 = Flowable.zip(Flowable.interval(1, TimeUnit.SECONDS), 
	    				Flowable.fromIterable(DataGenerator.generateGreekAlphabet()),
	    				(i,alphabet) -> alphabet
    				);
    	Flowable<String> sequence2 = Flowable.zip(Flowable.interval(500, TimeUnit.MILLISECONDS), 
    			Flowable.fromIterable(DataGenerator.generateEnglishAlphabet()),
    			(i,alphabet) -> alphabet
    			);

        // Create an "ambiguous" observable from the two sequences
        Flowable.ambArray(sequence1, sequence2)
                .subscribe((s) -> {
                    System.out.println(s);
                });


        // Wait for 4 seconds while things run...we should see Greek letters
        // being emitted at 50ms intervals for 4 seconds since the first
        // sequence to emit events will be selected by the amb operator.
        ThreadUtils.sleep(4000);
        
        
        System.exit(0);
    }

}
