package com.weare;

import static com.weare.Constants.*;

public class JSONRequests {
    public static final String REGISTRATION_BODY = "{\n" +
            "    \"category\": {\n" +
            "        \"id\": 100,\n" +
            "        \"name\": \"All\"\n" +
            "    },\n" +
            "    \"confirmPassword\": \"%s\",\n" +
            "    \"email\": \"%s\",\n" +
            "    \"password\": \"%s\",\n" +
            "    \"username\": \"%s\"\n" +
            "}";

    public static final String POST_BODY = String.format("{\n" +
            " \"content\": \"%s\",\n" +
            " \"picture\": \"No picture\",\n" +
            " \"public\": true\n" +
            "}", POST_DESCRIPTION);

    public static final String COMMENT_BODY = "{\n" +
            "  \"content\": \"%s\",\n" +
            "  \"postId\": %s,\n" +
            "  \"userId\": %s\n" +
            "}";


    public static final String PROFILE_POST = "{\n" +
            "  \"index\": 0,\n" +
            "  \"next\": true,\n" +
            "  \"searchParam1\": \"\",\n" +
            "  \"searchParam2\": \"\",\n" +
            "  \"size\": 10\n" +
            "}";

    public static final String PROFILE_BODY = "{\n" +
            "    \"id\": %s,\n" +
            "    \"username\": \"%s\",\n" +
            "    \"email\": \"%s\",\n" +
            "    \"firstName\": \"%s\",\n" +
            "    \"lastName\": \"randomLastName\",\n" +
            "    \"sex\": \"MALE\",\n" +
            "    \"birthYear\": \"2024-11-11\",\n" +
            "    \"personalReview\": \"randomPhrase\",\n" +
            "    \"location\": {\n" +
            "        \"city\": {\n" +
            "            \"id\": 1,\n" +
            "            \"city\": \"Sofia\",\n" +
            "            \"country\": {}\n" +
            "            \n" +
            "        }\n" +
            "    }\n" +
            "}";

    public static final String SEND_CONNECTION_REQ_BODY = "{\"id\": %s, \"username\": \"%s\"}";

    public static final String POST_EDIT = String.format(
            "{\n"
                    + "\"content\": \"%s\",\n"
                    + "\"picture\": \"No picture\",\n"
                    + "\"public\": true\n"
                    + "}", EDIT_COMMENT);
}
