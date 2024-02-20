package com.arcariel.kafka.wikimedia;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WikiChangesProducer {

    //Code Challenge, Count how many letter are repeated in a String word, ex: "appearance"

    public static void main(String[] args) {
        String word = "appearance";
        Map<Character, Long> letterCounts = countLetters(word);
        letterCounts.forEach((letter, count) -> System.out.println(letter + ": " + count));
    }

    public static Map<Character, Long> countLetters(String word) {
        return word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
