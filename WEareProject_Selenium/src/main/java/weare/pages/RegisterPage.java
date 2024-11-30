package weare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends HomePage {

    public RegisterPage() {
        super("/register");
    }

    // Locators
    private final By usernameField = By.id("name");
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By confirmPasswordField = By.id("confirm");
    private final By registerButton = By.xpath("//input[@value='Register']");
    private static final By welcomeMessage = By.xpath("//h1[@class='mb-3 bread']");

    //Actions
    public void registerUser(String username, String email, String password, String confirmPassword) {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField)).sendKeys(confirmPassword);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(registerButton)).click();
    }

    public static By getWelcomeMessage() {
        return welcomeMessage;
    }

}
