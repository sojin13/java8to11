package me.soreal.java8to11;


import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE) // 좀 더 자유롭게 쓰고 싶을 때
public @interface ChickenContainer {

    Chicken[] value();
}
