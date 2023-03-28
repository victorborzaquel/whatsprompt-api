package com.victorborzaquel.whatsprompt.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ScoreUtils {
    private ScoreUtils() {
    }

    public static Integer generateScore(String correctAnswer, String userAnswer) {
        final Set<String> correct = createWordsSet(correctAnswer);
        final Set<String> answer = createWordsSet(userAnswer);

        return calculeScore(correct.size(), getWordsCorrects(correct, answer));
    }

    private static Set<String> createWordsSet(String words) {
        return new HashSet<>(Arrays.asList(words.toLowerCase().split(" ")));
    }

    private static int calculeScore(int numberWords, int wordsCorrects) {
        final int MULTPLY_CONST = 45;
        final double correctPercentage = wordsCorrects / (double) numberWords;
        return (int) (wordsCorrects * MULTPLY_CONST * correctPercentage);
    }

    private static int getWordsCorrects(Set<String> correct, Set<String> answer) {
        final AtomicInteger wordsCorrects = new AtomicInteger();
        answer.stream().iterator().forEachRemaining(word -> {
            if (correct.contains(word)) {
                wordsCorrects.getAndIncrement();
            }
        });
        return wordsCorrects.get();
    }
}
