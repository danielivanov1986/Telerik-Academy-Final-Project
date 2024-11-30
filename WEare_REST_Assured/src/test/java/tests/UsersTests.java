package tests;

import base.BaseTestSetup;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static com.weare.Constants.*;


class UsersTests extends BaseTestSetup {

    Response response;

    @Test
    @Story("Login Test")
    @Description("Test Description: Verify login functionality")
    public void loginAndGetCookieTest() {
        ValidatableResponse responseBody = loginUser();

        int statusCode = responseBody.extract().statusCode();

        //Assert
        Assert.assertEquals(302, statusCode, "Expected status code to be 302");
    }

    @Test
    @Story("User Registration Test")
    @Description("Test Description: Verify the creation and registration of a new user")
    public void registerUserTest() {

        response = createAndRegisterUser();

        String responseUsername = response.getBody().asString().split(" ")[3];
        String responseID = response.getBody().asString().split(" ")[6];

        //Assert
        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
        Assert.assertNotNull(responseID, "Response should contain 'id'");
        Assert.assertEquals(responseUsername, RANDOM_USERNAME, "The username should match the expected value.");
        Assert.assertTrue(RANDOM_EMAIL.contains("@test.com"), "Email should contain '@test.com'");
    }

    @Test
    @Story("Update User Profile Test")
    @Description("Test Description: Verify updating user profile details")
    public void updateUserProfileTest() {
        createAndRegisterUser();
        loginUser();

        response = updateUserProfile(RANDOM_USERNAME);

        String responseBody = response.getBody().prettyPrint();
        String updatedFirstName = response.jsonPath().getString("firstName");
        String updatedLastName = response.jsonPath().getString("lastName");

        //Assert
        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
        Assert.assertEquals(updatedFirstName, RANDOM_USERNAME,
                String.format("First name should be updated to %s", updatedFirstName));
        Assert.assertEquals(updatedLastName, "randomLastName", "Last name should be updated to 'randomLastName'");
    }
}
