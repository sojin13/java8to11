package me.soreal.java8to11;

public class DefaultBar implements Bar, Baz {

    String name;

    public DefaultBar(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    // Diamond Problem : 직접 재정의하여 충돌하는 메소드 구현으로 해결.
    @Override
    public void printNameUpperCase() {

    }
}
