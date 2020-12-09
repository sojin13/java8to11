package me.soreal.java8to11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };

        Callable<String> soreal = () -> {
            Thread.sleep(1000L);
            return "Soreal";
        };

        // invokeAll : 모두 다 끝날 때까지 기다림
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, soreal));
        for (Future<String> f : futures) {
            System.out.println(f.get());
        }

        executorService.shutdown();

    }
}
