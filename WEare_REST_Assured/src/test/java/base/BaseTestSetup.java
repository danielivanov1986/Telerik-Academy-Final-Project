package base;

import com.weare.RandomDataGenerator;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

import static com.weare.Constants.*;
import static com.weare.Endpoints.*;
import static com.weare.JSONRequests.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BaseTestSetup {
    protected RequestSpecification request;

    @BeforeEach
    public void setUp() {
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        RestAssured.baseURI = BASE_URL;

        request = RestAssured.given()
                .filter(new AllureRestAssured())
                .header("Accept", APPLICATION_JSON)
                .header("Content-Type", APPLICATION_JSON);

    }

    protected static ValidatableResponse loginUser() {
        baseURI = BASE_URL + AUTHENTICATE_ENDPOINT;

        RestAssured.authentication = RestAssured.preemptive().basic(RANDOM_USERNAME, USER_PASSWORD);

        ValidatableResponse responseBody = given()
                .filter(new AllureRestAssured())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .queryParam("username", RANDOM_USERNAME)
                .queryParam("password", USER_PASSWORD)
                .post(baseURI)
                .then()
                .statusCode(302);

        COOKIE_VALUE = responseBody.extract().cookies().get("JSESSIONID");
        System.out.println("Extracted COOKIE: " + COOKIE_VALUE);
        return responseBody;
    }

    protected static Response createAndRegisterUser() {
        baseURI = BASE_URL + REGISTER_ENDPOINT;

        RANDOM_USERNAME = RandomDataGenerator.getRandomString(8);
        RANDOM_EMAIL = RandomDataGenerator.getRandomEmail();

        String registrationBody = String.format(REGISTRATION_BODY, USER_PASSWORD, RANDOM_EMAIL, USER_PASSWORD,
                RANDOM_USERNAME);

        Response response = given()
                .filter(new AllureRestAssured())
                .contentType(APPLICATION_JSON)
                .body(registrationBody)
                .post(baseURI);

        USER_ID = response.getBody().asString().split(" ")[6];

        return response;
    }

    protected static Response updateUserProfile(String name) {
        baseURI = BASE_URL + API_USERS_AUTH + USER_ID + "/personal";

        String profileBody = String.format(PROFILE_BODY, USER_ID, RANDOM_USERNAME, RANDOM_EMAIL, RANDOM_USERNAME);

        return given()
                .filter(new AllureRestAssured())
                .contentType(APPLICATION_JSON)
                .header("Accept", "*/*")
                .queryParam("name", name)
                .cookie("JSESSIONID", COOKIE_VALUE)
                .body(profileBody)
                .post(baseURI);
    }

    protected static Response createPost() {
        baseURI = BASE_URL + CREATE_POST_ENDPOINT;

        Response response = given()
                .filter(new AllureRestAssured())
                .contentType(APPLICATION_JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE).
                body(POST_BODY)
                .post(baseURI);

        POST_ID = response.jsonPath().getString("postId");
        System.out.println("Extracted postId: " + POST_ID);
        return response;
    }

    protected static Response showAllPosts() {
        baseURI = BASE_URL + GET_ALL_POSTS_ENDPOINT;

        return given()
                .filter(new AllureRestAssured())
                .queryParam("sorted", "true")
                .queryParam("unsorted", "false")
                .get(baseURI);
    }

    protected static Response editProfilePost() {
        baseURI = BASE_URL + EDIT_POST;

        return RestAssured.given()
                .filter(new AllureRestAssured())
                .cookies("JSESSIONID", COOKIE_VALUE)
                .baseUri(baseURI)
                .contentType(APPLICATION_JSON)
                .queryParam("postId", POST_ID)
                .body(POST_EDIT)
                .put(baseURI);
    }

    protected static Response likePost() {
        baseURI = BASE_URL + String.format(LIKE_POST, POST_ID);

        return RestAssured.given()
                .filter(new AllureRestAssured())
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(ContentType.JSON)
                .post(baseURI);
    }


    protected static Response deletePost() {
        baseURI = BASE_URL + DELETE_POSTS;

        return given()
                .filter(new AllureRestAssured())
                .cookies("JSESSIONID", COOKIE_VALUE)
                .queryParam("postId", POST_ID)
                .delete(baseURI);
    }

    protected static Response sendRequest() {
        baseURI = BASE_URL + SEND_CONNECTION_REQUEST_ENDPOINT;

        String requestBody = String.format(SEND_CONNECTION_REQ_BODY, USER_ID_RECEIVER, USERNAME_RECEIVER);

        return given()
                .filter(new AllureRestAssured())
                .contentType(APPLICATION_JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE)
                .queryParam("principal", RANDOM_USERNAME)
                .body(requestBody)
                .post(baseURI);
    }

    protected static Response approveRequest() {
        baseURI = BASE_URL + CONNECTION_REQUEST_ENDPOINT + USER_ID_RECEIVER + CONNECTION_REQUEST_APPROVE_ENDPOINT;

        return given()
                .filter(new AllureRestAssured())
                .contentType(APPLICATION_JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE_RECEIVER)
                .queryParam("requestId", CONNECTION_ID)
                .post(baseURI);
    }

    protected static void loginUserReceiver() {
        baseURI = BASE_URL + AUTHENTICATE_ENDPOINT;

        RestAssured.authentication = RestAssured.preemptive().basic(USERNAME_RECEIVER, USER_PASSWORD);

        ValidatableResponse responseBody =
                given()
                        .filter(new AllureRestAssured())
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .queryParam("username", USERNAME_RECEIVER)
                        .queryParam("password", USER_PASSWORD)
                        .post(baseURI)
                        .then()
                        .statusCode(302);

        COOKIE_VALUE_RECEIVER = responseBody.extract().cookies().get("JSESSIONID");
    }

    protected static Response createAndRegisterUserReceiver() {
        baseURI = BASE_URL + REGISTER_ENDPOINT;

        USERNAME_RECEIVER = RandomDataGenerator.getRandomString(8);
        EMAIL_RECEIVER = RandomDataGenerator.getRandomEmail();

        String registrationBody = String.format(REGISTRATION_BODY, USER_PASSWORD, EMAIL_RECEIVER, USER_PASSWORD,
                USERNAME_RECEIVER);

        Response response = given()
                .filter(new AllureRestAssured())
                .contentType(APPLICATION_JSON)
                .body(registrationBody)
                .post(baseURI);

        USER_ID_RECEIVER = response.getBody().asString().split(" ")[6];

        return response;
    }

    public void showReceivedRequests() {
        baseURI = BASE_URL + CONNECTION_REQUEST_ENDPOINT + USER_ID_RECEIVER + REQUEST;

        Response response = given()
                .filter(new AllureRestAssured())
                .contentType(APPLICATION_JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE_RECEIVER)
                .get();

        int id = response.jsonPath().getInt("[0].id");
        CONNECTION_ID = String.valueOf(id);
    }

    public static Response createComment() {

        baseURI = BASE_URL + COMMENT_ENDPOINT;

        String commentBody = String.format(COMMENT_BODY, COMMENT_DESCRIPTION, POST_ID, USER_ID);

        Response response = given()
                .filter(new AllureRestAssured())
                .contentType(APPLICATION_JSON)
                .header("Accept", "*/*")
                .cookies("JSESSIONID", COOKIE_VALUE)
                .body(commentBody)
                .post(baseURI);

        COMMENT_ID = response.jsonPath().getString("commentId");

        return response;
    }

    public static Response getComment() {
        baseURI = String.format(SHOW_CREATED_COMMENTS, COMMENT_ID);


        return given()
                .filter(new AllureRestAssured())
                .contentType(APPLICATION_JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE)
                .get(baseURI);
    }

    public static Response editComment() {
        baseURI = String.format(BASE_URL + EDITED_COMMENT, COMMENT_ID);

        return given()
                .filter(new AllureRestAssured())
                .contentType(APPLICATION_JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE)
                .put(baseURI);
    }

    public static Response deleteComment() {

        baseURI = BASE_URL + DELETE_COMMENT;

        return given()
                .filter(new AllureRestAssured())
                .header("Accept", "*/*")
                .contentType(APPLICATION_JSON)
                .cookie("JSESSIONID", COOKIE_VALUE)
                .queryParam("commentId", COMMENT_ID)
                .delete(baseURI);
    }

    protected static Response likeComment() {
        baseURI = String.format(BASE_URL + LIKED_COMMENT, COMMENT_ID);

        return given()
                .filter(new AllureRestAssured())
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(APPLICATION_JSON)
                .post(baseURI);
    }
}
