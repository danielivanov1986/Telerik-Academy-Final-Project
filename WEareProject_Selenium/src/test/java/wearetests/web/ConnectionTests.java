package wearetests.web;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import testframework.DriverManager;
import weare.pages.SearchPage;
import weare.pages.UserHomePage;
import wearetests.core.AssertionUtils;
import wearetests.core.RandomDataGenerator;
import wearetests.core.WEareBaseWebTest;
import wearetests.enums.TestData;

@Epic("connection Tests")
public class ConnectionTests extends WEareBaseWebTest {

    @Test
    @Feature("User Connects Other User")
    @Description("Test the functionality of a user sending a connection request to another user.")
    public void userConnectsOtherUserTest() {
        homePage.navigate();
        homePage.clickRegister();
        String username = TestData.REGISTER_USERNAME.getValue();
        String password = TestData.REGISTER_PASSWORD.getValue();
        registerPage.registerUser(username, TestData.EMAIL.getValue(),
                password, password);

        homePage.clickSigIn();
        signInPage.signIn(username, password);
        searchPage.clickSearchButton();
        userHomePage.clickLatestPosts();
        latestPostsPage.clickSeeProfileButton();

        //Assert
        AssertionUtils.assertElementVisible(DriverManager.getDriver(), "xpath", SearchPage.getConnectLink());
    }

    @Test
    @Feature("User Connect Request")
    @Description("Test the functionality of a user sending a connection request. " +
            "Verifies that the connection request button works and sends a request.")
    public void userConnectRequestTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());
        searchPage.clickSearchButton();
        userHomePage.clickLatestPosts();
        latestPostsPage.clickSeeProfileButton();
        searchPage.clickConnectLink();

        //Assert
        AssertionUtils.assertElementVisible(DriverManager.getDriver(), "xpath",
                SearchPage.getSentFriendRequest());
    }

    @Test
    @Feature("User Connect Accept")
    @Description("Test the functionality of a user accepting a connection request." +
            "Verifies that a user can accept the request and the connection is established.")
    public void userConnectAcceptTest() {
        //User1
        homePage.navigate();
        homePage.clickRegister();
        String username = RandomDataGenerator.getRandomString(6);
        String password = TestData.REGISTER_PASSWORD.getValue();

        registerPage.registerUser(username, TestData.EMAIL.getValue(), password, password);
        homePage.clickSigIn();
        signInPage.signIn(username, password);
        userHomePage.clickLogout();

        //User2
        homePage.navigate();
        homePage.clickRegister();
        String username2 = RandomDataGenerator.getRandomString(6);

        registerPage.registerUser(username2, TestData.EMAIL.getValue(), password, password);
        homePage.clickSigIn();
        signInPage.signIn(username2, password);
        searchPage.clickSearchButton();
        searchPage.clickSecondProfileLink();
        searchPage.clickConnectLink();
        userHomePage.clickLogout();

        //User 1 again
        homePage.navigate();
        homePage.clickSigIn();
        signInPage.signIn(username, password);
        userHomePage.clickPersonalProfile();
        userHomePage.clickNewFriendRequests();
        userHomePage.clickApproveRequests();

        //Assert
        AssertionUtils.assertElementVisible(DriverManager.getDriver(), "xpath",
                UserHomePage.getApproveRequestsLink());
    }

    @Test
    @Feature("User Disconnect")
    @Description("Test the functionality of disconnecting from a user. " +
            "Verifies that a user can disconnect from another user.")
    public void userDisconnectsTest() {
        //User 1
        homePage.navigate();
        homePage.clickRegister();
        String username = RandomDataGenerator.getRandomString(6);

        String password = TestData.REGISTER_PASSWORD.getValue();
        registerPage.registerUser(username, TestData.EMAIL.getValue(), password, password);
        homePage.clickSigIn();
        signInPage.signIn(username, password);
        userHomePage.clickLogout();

        //User 2
        homePage.navigate();
        homePage.clickRegister();

        String username2 = RandomDataGenerator.getRandomString(6);

        registerPage.registerUser(username2, TestData.EMAIL.getValue(), password, password);
        homePage.clickSigIn();
        signInPage.signIn(username2, password);
        searchPage.clickSearchButton();
        searchPage.clickSecondProfileLink();
        searchPage.clickConnectLink();
        userHomePage.clickLogout();

        //User 1 again
        homePage.navigate();
        homePage.clickSigIn();
        signInPage.signIn(username, password);
        userHomePage.clickPersonalProfile();
        userHomePage.clickNewFriendRequests();
        userHomePage.clickApproveRequests();

        homePage.navigate();
        searchPage.clickSearchButton();
        searchPage.clickUserProfile();
        searchPage.clickDisconnect();

        //Assert
        AssertionUtils.assertElementVisible(DriverManager.getDriver(), "xpath",
                SearchPage.getConnectLink());
    }
}
