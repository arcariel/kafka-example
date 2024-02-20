package com.arcariel.kafka.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test {

    private static Integer[] elements = {2,4,6,8,9};

    public static void main(String[] args) {
        System.out.println(getPairs());
        System.out.println(filterByNumber(5));
        System.out.println(filterByNumberOrThrow(5));
    }

    private static List<Integer> getPairs() {
        return Arrays.stream(elements).filter(number -> number%2 == 0)
                .collect(Collectors.toList());
    }

    private static Optional<Integer> filterByNumber(Integer x) {
        return Arrays.stream(elements).filter(element -> element == x)
                .findFirst();
    }
    private static Integer filterByNumberOrThrow(Integer x) {
        return Arrays.stream(elements).filter(element -> element == x)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Invalid data"));
    }
}
