package me.soreal.java8to11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CFApp<pivate> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // CompletabelFuture

        // forkjoinpool Dqueue

        // return type이 void인 경우 runAsync
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            System.out.println("Hello" + Thread.currentThread().getName());
//        });
//        future.get();

        // return type이 있는 경우 supplyAsync
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("Hello" + Thread.currentThread().getName());
//            return "Hello";
//        });
//        System.out.println(future.get());


        // thenApply
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("Hello" + Thread.currentThread().getName());
//            return "Hello";
//        }).thenApply((s) -> { // Get 호출 전에 정의가 가능하다.
//            System.out.println(Thread.currentThread().getName());
//            return s.toUpperCase();
//        });
//        System.out.println(future.get()); // get을 호출해줘야 함. 호출하지 않으면 아무 일도 실행되지 않음.


        // thenAccept
//        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("Hello" + Thread.currentThread().getName());
//            return "Hello";
//        }).thenAccept((s) -> {
//            System.out.println(Thread.currentThread().getName());
//            System.out.println(s.toUpperCase()); // consumer
//        });
//        System.out.println(future.get());

        // thenRun
//        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("Hello" + Thread.currentThread().getName());
//            return "Hello";
//        }).thenRun(() -> {
//            System.out.println(Thread.currentThread().getName());
//        });
//        System.out.println(future.get());


        // 연결 하는 방법 1: 의존성이 있음.
//        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
//            System.out.println("Hello" + Thread.currentThread().getName());
//            return "Hello";
//        });
//
//        // thenCompose 뒤이어 추가적인 작업 할 수 있음.
//        CompletableFuture<String> future = hello.thenCompose(CFApp::getWorld);
//
//        System.out.println(future.get());


        // 연결하는 방법 2: 의존하지 않음. 바이펑션.
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World" + Thread.currentThread().getName());
            return "World";
        });

//        CompletableFuture<String> future = hello.thenCombine(world, (h, w) -> h + " " + w);
//        System.out.println(future.get());


        // 연결하는 방법3 : allOf
//        List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
//        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);
//
//
//        CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray)
//                .thenApply(v -> {
//                    return futures.stream()
//                            .map(CompletableFuture::join)
//                            .collect(Collectors.toList());
//                });
//        results.get().forEach(System.out::println);


        // 아무거나 빨리 끝나는거 받아서
        CompletableFuture<Void> future = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
        future.get();

        // 예외처리
        boolean throwError = true;

        CompletableFuture<String> errorHello = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello" + Thread.currentThread().getName());
            return "errorHello";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error!";
        });

        System.out.println(errorHello.get());


        // 좀더 general한 처리
        CompletableFuture<String> generalHello = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello" + Thread.currentThread().getName());
            return "generalHello";
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println(ex);
                return "ERROR!";
            }
            return result;
        });

        System.out.println(generalHello.get());


    }


    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World" + Thread.currentThread().getName());
            return message + "World";
        });
    }

}
