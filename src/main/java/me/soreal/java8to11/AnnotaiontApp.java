package me.soreal.java8to11;

import java.util.Arrays;
import java.util.List;

@Chicken("양념")
@Chicken("마늘간장")
public class AnnotaiontApp {

    public static void main(String[] args) {

//        List<@Chicken String> names = Arrays.asList("soreal");

//        Chicken[] chickens = App.class.getAnnotationsByType(Chicken.class);
//        Arrays.stream(chickens).forEach(c -> {
//            System.out.println(c.value());
//        });

        // todo 왜 nullpointexception??
        ChickenContainer chickenContainer = App.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> {
            System.out.println(c.value());
        });

    }


    //    static class FeelsLikeChicken<@Chicken T> {
//
//        public static <@Chicken C> void print(@Chicken C c) {
//            System.out.println(c);
//
//        }
//    }


}
