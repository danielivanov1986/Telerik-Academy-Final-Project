package weare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Date;

public class UserProfilePage extends HomePage{

    public UserProfilePage() {
        super("/auth/users/41/profile");
    }

    // Locators
    private final By editProfileLink = By.xpath("//a[contains(text(),'edit')]");
    private final By firstName = By.id("nameE");
    private final By lastName = By.id("lastnameE");
    private final By birthday = By.id("birthDayE");
    private final By gender = By.id("selectE");
    private final By userEmail = By.id("emailE");
    private final By userInfo = By.id("publicinfoE");
    private final By updateMyProfileButton = By.xpath("//button[contains(text(),'Update My')]");


    // Actions
    public void clickEditProfile(){
        driver().findElement(editProfileLink).click();
    }

    public void editProfile(String fName, String lName, String date, String email, String aboutMe) {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(firstName)).clear();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fName);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(lastName)).clear();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lName);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(birthday)).sendKeys(date);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(userEmail)).clear();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(userEmail)).sendKeys(email);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(userInfo)).clear();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(userInfo)).sendKeys(aboutMe);
        driver().findElement(updateMyProfileButton).click();
    }

    public String getUserInfo() {
        WebElement userInfoElement = driver().findElement(userInfo);
        return userInfoElement.getText();
    }

}
