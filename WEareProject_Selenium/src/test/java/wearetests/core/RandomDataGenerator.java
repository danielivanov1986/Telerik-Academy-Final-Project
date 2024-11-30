package wearetests.core;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataGenerator {

    public static String getRandomString(int length) {
        return RandomStringUtils.random(length, true, false);
    }
    public static String generateEmail() {
        String randomUsername = getRandomString(10);
        return randomUsername + "@test.com";
    }
}
