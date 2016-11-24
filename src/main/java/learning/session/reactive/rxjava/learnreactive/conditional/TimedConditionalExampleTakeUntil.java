package learning.session.reactive.rxjava.learnreactive.conditional;


import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.DataGenerator;
import learning.session.reactive.rxjava.learnreactive.util.ThreadUtils;

public class TimedConditionalExampleTakeUntil {

    public static void main(String[] args) {


    	Flowable<String> sequence1 = Flowable.zip(Flowable.interval(100, TimeUnit.MILLISECONDS), 
				Flowable.fromIterable(DataGenerator.generateEnglishAlphabet()),
				(i,alphabet) -> alphabet
				);

        // Create a skipUntil operation around the two sequences
        sequence1.takeUntil(Flowable.interval(1, TimeUnit.SECONDS))
                .subscribe((ch) -> {
                    System.out.println(ch);
                });

        ThreadUtils.sleep(6000);
    }

}
