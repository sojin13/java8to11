package me.soreal.java8to11;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {

    public static void main(String[] args) {

        // :: 메소드 레퍼런스
        // static 메소드
        UnaryOperator<String> hi = Greeting::hi;

        // instance 메소드
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        System.out.println(hello.apply("soreal")); // hello soreal


        Supplier<Greeting> newGreeting = Greeting::new;
        newGreeting.get();

        // sorealGreeting과 neoGreeting은 일견 같아 보이지만 서로 다른 생성자 참조함
        Function<String, Greeting> sorealGreeting = Greeting::new;
        Greeting soreal = sorealGreeting.apply("soreal");
        System.out.println(soreal.getName()); // soreal

        Supplier<Greeting> neoGreeting = Greeting::new;

        // 임의 객체의 인스턴스 메소드 참조 -> 타입::인스턴스 메소드
        String[] names = {"soreal", "jiny", "jean"};
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names)); // [jean, jiny, soreal]


        // 인터페이스 기본 메소드와 스태틱 메소드
        Bar bar = new DefaultBar("soreal");
        bar.printName(); // soreal

        Bar.printAnything(); // 스태틱 메소드 호출  // Bar


        // 자바 8 API 기본 메소드

        // Iterable 기본 메소드
        List<String> name  = new ArrayList<>();
        name.add("soreal");
        name.add("jiny");
        name.add("jean");
        name.add("sojin");

        name.forEach(System.out::println);

        System.out.println("============");
        for (String n: name) {
            System.out.println(n);
        }

        System.out.println("=============");
        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit(); // 절반으로 쪼갬
        while (spliterator.tryAdvance(System.out::println)); // jean, sojin
        System.out.println("=============");
        while (spliterator1.tryAdvance(System.out::println)); // soreal jiny

        // Stream 예제
        long k = name.stream().map(String::toUpperCase)
                    .filter(s -> s.startsWith("S"))
                    .count();
        System.out.println(k);

        name.removeIf(s -> s.startsWith("s"));

        name.forEach(System.out::println); // jiny, jean

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        name.sort(compareToIgnoreCase.reversed());






    }

}
