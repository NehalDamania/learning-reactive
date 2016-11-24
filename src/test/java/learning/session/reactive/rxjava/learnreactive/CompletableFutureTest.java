package learning.session.reactive.rxjava.learnreactive;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;

public class CompletableFutureTest {
	@Test
	public void learnCompletableFuture() {
		
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
				sleep(1000);
				return "Hello" + Thread.currentThread().getName();
			});
		completableFuture.thenAccept(System.out::println);
		System.out.println("Hi this one printed first");
		sleep(2000);
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) { }
	}

}
