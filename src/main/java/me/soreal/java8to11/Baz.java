package me.soreal.java8to11;

public interface Baz {


    default void printNameUpperCase() { // default method
        System.out.println("BAZ");
    }

}
