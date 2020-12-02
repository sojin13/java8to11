package me.soreal.java8to11;

@FunctionalInterface
public interface RunSomething {

    void doIt();

    static void printName() {
        System.out.println("Sojin");
    }

    default void printAge() {
        System.out.println("29");
    }

}
