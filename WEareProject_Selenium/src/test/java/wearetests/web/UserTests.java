package wearetests.web;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import testframework.DriverManager;
import weare.pages.*;
import wearetests.core.AssertionUtils;
import wearetests.core.WEareBaseWebTest;
import wearetests.enums.TestData;

@Epic("User Tests")
public class UserTests extends WEareBaseWebTest {

    @Test
    @Feature("User Registration")
    @Description("Tests the registration functionality with valid input data. " +
            "Verifies that a user can register successfully and see the welcome message.")
    public void userRegisterTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());

        //Assert
        AssertionUtils.assertElementVisible(DriverManager.getDriver(), "xpath",
                RegisterPage.getWelcomeMessage());
    }

    @Test
    @Feature("User Login")
    @Description("Tests the login functionality with correct credentials. " +
            "Verifies that a user can log in successfully and access the homepage.")
    public void userLoginTest() {

        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());

        //Assert
        AssertionUtils.assertElementVisible(DriverManager.getDriver(), "xpath",
                HomePage.getLogoutHomePageLocator());
    }

    @Test
    @Feature("User Logout")
    @Description("Tests the logout functionality after a successful login." +
            " Verifies that a user can log out and sees the logout confirmation message.")
    public void userLogoutTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());
        userHomePage.clickLogout();

        //Assert
        AssertionUtils.assertElementVisible(DriverManager.getDriver(), "xpath",
                UserHomePage.getYouAreLoggedOutMessage());
        System.out.println("Logout user: " + TestData.REGISTER_USERNAME.getValue());
    }

    @Test
    @Feature("User Profile Update")
    @Description("Tests the profile update functionality. " +
            "Verifies that a user can successfully update their profile information and see the success message.")
    public void updateUserProfileTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());
        userHomePage.clickPersonalProfile();
        userProfilePage.clickEditProfile();
        userProfilePage.editProfile(TestData.UPDATE_FIRSTNAME.getValue(), TestData.UPDATE_LASTNAME.getValue(),
                TestData.UPDATE_BIRTHDAY.getValue(), TestData.UPDATE_EMAIL.getValue(), TestData.UPDATE_ABOUT_ME.getValue());
        //Assert
        AssertionUtils.isTextVisible(DriverManager.getDriver(), "Profile updated successfully.");
    }
}
