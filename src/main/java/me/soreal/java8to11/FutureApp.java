package me.soreal.java8to11;

import java.util.concurrent.*;

public class FutureApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Runnable : return void.
        // Callable : Runnable과 같지만 return 값을 줄 수 있다.

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> helloFuture = executorService.submit(hello);
        System.out.println(helloFuture.isDone());
        System.out.println("Started!");

//        helloFuture.get(); // blocking
//        helloFuture.cancel(false);


        System.out.println(helloFuture.isDone());
        System.out.println("End!!");
        executorService.shutdown();

    }



}
