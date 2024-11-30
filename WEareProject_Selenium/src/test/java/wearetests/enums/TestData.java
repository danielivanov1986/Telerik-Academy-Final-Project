package wearetests.enums;

import static wearetests.core.RandomDataGenerator.*;

public enum TestData {
    USER_PASSWORD("test123"),
    ADMIN_USERNAME(getRandomString(4)+"admin"),
    ADMIN_PASSWORD("admin123"),
    REGISTER_USERNAME(getRandomString(8)),
    REGISTER_PASSWORD("test123"),
    EMAIL(generateEmail()),
    UPDATE_EMAIL(generateEmail()),
    UPDATE_BIRTHDAY("11111990"),
    UPDATE_FIRSTNAME(getRandomString(8)),
    UPDATE_LASTNAME(getRandomString(6)),
    UPDATE_ABOUT_ME(String.format("Hello, my name is %s and I updated my info.", UPDATE_FIRSTNAME));

    private final String value;
    
    TestData(String propName) {
        value = propName;
    }

    public String getValue() {
        return value;
    }
}
