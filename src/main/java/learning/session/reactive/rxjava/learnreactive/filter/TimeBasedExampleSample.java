package learning.session.reactive.rxjava.learnreactive.filter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.ThreadUtils;

public class TimeBasedExampleSample {

	public static void main(String[] args) {

		Flowable.intervalRange(1, 10, 1, 1, TimeUnit.SECONDS)
				.sample(2, TimeUnit.SECONDS).subscribe((t) -> {
					System.out.println("Tick: " + t);
				});

		// We do this for 10 seconds...
		ThreadUtils.sleep(10000);
		System.exit(0);

	}
}
