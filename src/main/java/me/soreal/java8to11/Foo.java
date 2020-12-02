package me.soreal.java8to11;

import java.util.function.*;

public class Foo {
    public static void main(String[] args) {

        // 익명 내부 클래스를 람다 표현식으로
        RunSomething runSomething = () -> System.out.println("Hello");
        runSomething.doIt();

        // 함수형 인터페이스

        // Function<T,R>
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1)); // 11

        Function<Integer, Integer> functionalPlus10 = (i) -> i + 10;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;
        System.out.println(functionalPlus10.apply(1)); // 11

        // compose() 된 값을 입력 값으로 사용하여 다시 결과값 뱉어냄
        Function<Integer, Integer> multiplay2AndPlus10 = functionalPlus10.compose(multiply2);
        System.out.println(multiplay2AndPlus10.apply(2)); // 14

        // andThen() function 뒤에다가 붙임
        System.out.println(functionalPlus10.andThen(multiply2).apply(2)); // 24

        // BiFunction<T,U,R>

        // Consumer<T>
        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10);

        // Supplier<T>
        // 무조건 10 리턴하는 함수임
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10); // 10

        // Predicate<T>
        // T/F 리턴하는 함수
        Predicate<String> startsWithSojin = (s) -> s.startsWith("sojin");
        Predicate<Integer> isEven = (i) -> i%2 == 0;

        // UnaryOperator
        UnaryOperator<Integer> plus100 = (i) -> i + 100;


    }
}
