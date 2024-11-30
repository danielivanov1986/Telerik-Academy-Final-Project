package com.weare;

import org.apache.commons.lang3.RandomStringUtils;



public class RandomDataGenerator {
    public static String getRandomString(int length) {
        return RandomStringUtils.random(length, true, false);
    }

    public static String getRandomEmail() {
        String randomUsername = getRandomString(5);
        return randomUsername + "@test.com";
    }
}
