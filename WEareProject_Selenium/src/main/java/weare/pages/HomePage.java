package weare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends BaseWEarePage {

    public HomePage(String s) {
        super("");
    }

    // Locators
    private final By registerLink = By.xpath("//li[@class='nav-item cta mr-md-1']" +
            "//a[@class='nav-link'][normalize-space()='REGISTER']");
    private final By signInLink = By.xpath("//a[contains(text(),'SIGN')]");
    private final By homeLink = By.xpath("//a[normalize-space()='Home']");
    private final By latestPostsLink = By.xpath("//a[normalize-space()='Latest Posts']");
    private final By aboutUsLink = By.xpath("//a[normalize-space()='About us']");
    private final By addNewPostButton = By.xpath("//a[contains(text(),'Add New')]");
    private final By navbarLink = By.xpath("//a[@class='navbar-brand']");
    private static final By logoutHomePage = By.xpath("//a[normalize-space()='LOGOUT']");
    private final By searchButton = By.xpath("//button[@type='submit']");


    // Actions
    public void clickRegister() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(registerLink)).click();
    }

    public void clickSigIn() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(signInLink)).click();
    }

    public void clickHome() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(homeLink)).click();
    }

    public void clickLatestPosts() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(latestPostsLink)).click();
    }

    public void clickAboutUs() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(aboutUsLink)).click();
    }

    public void clickNavBar() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(navbarLink)).click();
    }

    public void clickAddNewPostButton() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(addNewPostButton)).click();
    }
    public void clickSearchButton() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(searchButton)).click();
    }

    public static By getLogoutHomePageLocator() {
        return logoutHomePage;
    }

}
