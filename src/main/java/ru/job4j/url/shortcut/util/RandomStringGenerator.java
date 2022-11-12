package ru.job4j.url.shortcut.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.function.Function;

public class RandomStringGenerator {

    public static String generateRandomString(Function<String, Boolean> stopCondition, int length) {
        int nrOfTries = 0;
        String str = RandomStringUtils.randomAlphanumeric(length);
        while (stopCondition.apply(str)) {
            if (nrOfTries % 10 == 0) {
                length++;
            }
            str = RandomStringUtils.randomAlphanumeric(length);
            nrOfTries++;
        }
        return str;
    }
}
