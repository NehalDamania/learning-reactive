package learning.session.reactive.rxjava.learnreactive.filter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import learning.session.reactive.rxjava.learnreactive.util.ThreadUtils;

public class TimeBasedExampleTimeout {

	public static void main(String[] args) {

		Flowable.intervalRange(1, 10, 1, 4, TimeUnit.SECONDS)
			.timeout(3, TimeUnit.SECONDS)
			.subscribe(
				// onNext
				(t) -> {
					// Every second, we will emit the current value
					// of System.currentTimeMillis()
					System.out.println(ThreadUtils.currentThreadName() + ":Tick: " + t);
				},
				// onError
				(exception) -> {
					System.out.println(ThreadUtils.currentThreadName() + ": TIMEOUT!");
					exception.printStackTrace();
				});

		System.out.println("This is printed first.");

		ThreadUtils.sleep(5000);
		System.exit(0);

	}
}
