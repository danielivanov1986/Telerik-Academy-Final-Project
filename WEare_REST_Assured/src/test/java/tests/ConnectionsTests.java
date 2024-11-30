package tests;

import base.BaseTestSetup;
import com.weare.Constants;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static com.weare.Constants.*;
import static com.weare.JSONRequests.*;


public class ConnectionsTests extends BaseTestSetup {

    @Test
    @Story("Send Connection Request Test")
    @Description("Test Description: Verify sending a connection request")
    public void sendConnectionTest() {
        Allure.step("Creating and registering user receiver");
        createAndRegisterUserReceiver();
        Allure.step("Creating and registering user sender");
        createAndRegisterUser();
        Allure.step("Logging in with sender user credentials");
        loginUser();

        Allure.step("Sending connection request from sender to receiver");
        Response response = sendRequest();

        //Assert
        Allure.step("Verifying the response of the connection request");
        Assert.assertNotNull(USER_ID_RECEIVER, "USER_ID_RECEIVER should not be null");
        Assert.assertNotNull(USERNAME_RECEIVER, "USERNAME_RECEIVER should not be null");
        Assert.assertNotNull(CONNECTION_ID, "CONNECTION_ID should not be null");

        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
        Assert.assertEquals(response.getBody().asString(), RANDOM_USERNAME + " send friend request to "
                + USERNAME_RECEIVER, "Expected message");
    }

    @Test
    @Story("Accept Connection Request Test")
    @Description("Test Description: Verify accepting a connection request")
    public void acceptConnectionTest() {
        Allure.step("Creating and registering user receiver and sender users");
        createAndRegisterUserReceiver();
        createAndRegisterUser();
        Allure.step("Logging in with sender user credentials and sending request");
        loginUser();
        sendRequest();
        Allure.step("Logging in with receiver credentials to accept the request");
        loginUserReceiver();
        Allure.step("Showing received connection requests");
        showReceivedRequests();

        Allure.step("Accepting the connection request from sender to receiver");
        Response response = approveRequest();

        //Assert
        Allure.step("Verifying the acceptance of the connection request");
        Assert.assertNotNull(COOKIE_VALUE_RECEIVER, "COOKIE_VALUE_RECEIVER should be present");
        Assert.assertNotNull(USER_ID_RECEIVER, "USER_ID_RECEIVER should not be null");
        Assert.assertTrue(EMAIL_RECEIVER.contains("@test.com"), "EMAIL_RECEIVER should contain '@test.com'");
        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
        Assert.assertEquals(response.getBody().asString(), USERNAME_RECEIVER + " approved request of "
                + RANDOM_USERNAME, "Expected message");
    }
}
