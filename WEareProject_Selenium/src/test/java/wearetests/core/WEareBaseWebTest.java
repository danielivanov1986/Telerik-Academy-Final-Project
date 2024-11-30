package wearetests.core;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import testframework.DriverManager;
import testframework.PropertiesManager;
import testframework.core.BaseWebTest;
import weare.pages.*;

public class WEareBaseWebTest extends BaseWebTest {

    protected AboutUsPage aboutUsPage;
    protected HomePage homePage;
    protected LatestPostsPage latestPostsPage;
    protected RegisterPage registerPage;
    protected SignInPage signInPage;
    protected UserHomePage userHomePage;
    protected UserProfilePage userProfilePage;
    protected SearchPage searchPage;

    @BeforeEach
    public void beforeTests() {
        aboutUsPage = new AboutUsPage();
        homePage = new HomePage("");
        latestPostsPage = new LatestPostsPage();
        registerPage = new RegisterPage();
        signInPage = new SignInPage();
        userHomePage = new UserHomePage();
        userProfilePage = new UserProfilePage();
        searchPage = new SearchPage();

        driver().get(PropertiesManager.getConfigProperties().getProperty("weareBaseUrl"));
    }

    @AfterEach
    public void afterTest() {
        driver().close();
    }

    @AfterAll
    public static void afterAll() {
        DriverManager.quitDriver();
    }

}
