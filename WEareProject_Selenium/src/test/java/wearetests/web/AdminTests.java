package wearetests.web;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import testframework.DriverManager;
import weare.pages.HomePage;
import weare.pages.RegisterPage;
import weare.pages.UserHomePage;
import wearetests.core.AssertionUtils;
import wearetests.core.WEareBaseWebTest;
import wearetests.enums.TestData;
@Epic("Admin Tests")
public class AdminTests extends WEareBaseWebTest {

    @Test
    @Feature("Admin Registration")
    @Description("Tests the registration functionality for the admin user." +
            "Verifies that the admin user can successfully register and see the welcome message.")
    public void adminRegistrationTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.ADMIN_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.ADMIN_PASSWORD.getValue(), TestData.ADMIN_PASSWORD.getValue());

        //Assert
        AssertionUtils.assertElementVisible(DriverManager.getDriver(), "xpath",
                RegisterPage.getWelcomeMessage());
    }

    @Test
    @Feature("Admin Login")
    @Description("Tests the login functionality for the admin user. " +
            "Verifies that the admin can successfully log in and access the homepage.")
    public void adminLoginTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.ADMIN_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.ADMIN_PASSWORD.getValue(), TestData.ADMIN_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.ADMIN_USERNAME.getValue(), TestData.ADMIN_PASSWORD.getValue());

        //Assert
        AssertionUtils.assertElementVisible(DriverManager.getDriver(), "xpath",
                HomePage.getLogoutHomePageLocator());
    }

    @Test
    @Feature("Admin Logout")
    @Description("Tests the logout functionality for the admin user. " +
            "Verifies that the admin user can log out successfully and sees the logout confirmation message.")
    public void adminLogoutTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.ADMIN_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.ADMIN_PASSWORD.getValue(), TestData.ADMIN_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.ADMIN_USERNAME.getValue(), TestData.ADMIN_PASSWORD.getValue());
        userHomePage.clickLogout();

        //Assert
        AssertionUtils.assertElementVisible(DriverManager.getDriver(), "xpath",
                UserHomePage.getYouAreLoggedOutMessage());
    }
}
