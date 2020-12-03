package me.soreal.java8to11;

import java.util.Arrays;
import java.util.Comparator;
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



    }

}
