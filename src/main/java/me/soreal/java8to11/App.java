package me.soreal.java8to11;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        String[] name = {"soreal", "jiny", "jean"};
        Arrays.sort(name, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(name)); // [jean, jiny, soreal]


        // 인터페이스 기본 메소드와 스태틱 메소드
        Bar bar = new DefaultBar("soreal");
        bar.printName(); // soreal

        Bar.printAnything(); // 스태틱 메소드 호출  // Bar


        // 자바 8 API 기본 메소드

        // Iterable 기본 메소드
        List<String> names  = new ArrayList<>();
        names.add("soreal");
        names.add("jiny");
        names.add("jean");
        names.add("sojin");

        names.forEach(System.out::println);

        System.out.println("============");
        for (String n: name) {
            System.out.println(n);
        }

//        System.out.println("=============");
//        Spliterator<String> spliterator = name.spliterator();
//        Spliterator<String> spliterator1 = spliterator.trySplit(); // 절반으로 쪼갬
//        while (spliterator.tryAdvance(System.out::println)); // jean, sojin
//        System.out.println("=============");
//        while (spliterator1.tryAdvance(System.out::println)); // soreal jiny

        // Stream 예제
        long k = names.stream().map(String::toUpperCase)
                    .filter(s -> s.startsWith("S"))
                    .count();
        System.out.println(k);

//        name.removeIf(s -> s.startsWith("s"));
//        name.forEach(System.out::println); // jiny, jean

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed());

        // 스트림 API :: sequence of elements supproting sequential and parallel aggregate operations
        // Functional in nature. 스트림이 처리하는 데이터 소스를 바꾸지 않는다. name은 그대로 소문자.
        System.out.println("--------------------------");
        names.stream().map(String::toUpperCase);
        System.out.println(name); // soreal, sojin, jiny, jean

        Stream<String> stringStream = names.stream().map(String::toUpperCase);
        System.out.println("--------------------------");


        // 스트림 파이프라인: 0 또는 다수의 중개 오퍼레이션과 한 개의 종료 오퍼레이션으로 구성.
        // 스트림의 데이터 소스는 터미널 오퍼네이션 실행 때만 처리됨.
        // 중개 오퍼레이터 : Stream 을 리턴한다. 기본적으로 lazy하다. filter, map, limit, skip, sorted...
        // 종료 오퍼레이터 : stream 리턴하지 않는다. collect, allMatch, count, forEach, min, max..
        List<String> collect = names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("--------------------------");
        names.forEach(System.out::println); // soreal sojin jiny jean

        // 병렬처리
        // 아래는 병렬 처리가 어려움
        for(String thing : names) {
            if(thing.startsWith("S")) {
                System.out.println(thing.toUpperCase());
            }
        }

        // 병렬처리 하는 parallelSteam
        // 병렬 쓰레드가 무조건 유용하고 빠른게 아님. 컨텍스트 스위칭에 비용이 더 걸릴 수도 있음.
        List<String> collect1 = names.parallelStream().map((s) -> {
                    System.out.println(s + " " + Thread.currentThread().getName());
                    return s.toUpperCase();
                }).collect(Collectors.toList());
        collect1.forEach(System.out::println);


        // Steam API 간단한 예제
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spirng으로 시작하는 수업");
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("close 되지 않은 수업");
        // 방법1
        springClasses.stream()
                .filter(oc -> !oc.isColsed())
                .forEach(oc -> System.out.println(oc.getId()));

        // 방법2
        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isColsed))
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);


        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));


        List<List<OnlineClass>> sorealEvents = new ArrayList<>();
        sorealEvents.add(springClasses);
        sorealEvents.add(javaClasses);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        sorealEvents.stream().flatMap(Collection::stream)
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스림 중에서 앞에 10개 빼고 최대 10개까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);

        System.out.println("스프링 수업 중에 제목이 spring이 들어간 제목만 모아서 List로 만들기");
        List<String> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect((Collectors.toList()));
        spring.forEach(System.out::println);


    }

}
