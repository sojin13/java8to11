package me.soreal.java8to11;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ParallelArray {

    public static void main(String[] args) {

        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();

        // 배열 랜덤한 값으로 채워주기.
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        long start = System.nanoTime();
        Arrays.sort(numbers); // 자바는 기본적으로 Quick sort 씀. 쓰레드를 하나만 씀.
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers); // 쪼개고 합치는 것을 분산 쓰레드로 처리함.
        System.out.println("parallel soring took " + (System.nanoTime() - start));
    }

    // + JAVA garbage collector 일하는 법 학습하기
    // metaspace : Native 메모리 영역으로 넘어옴. 기본적으로 제한된 크기 가지지 않는다.




}
