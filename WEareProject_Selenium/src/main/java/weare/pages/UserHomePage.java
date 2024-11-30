package weare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserHomePage extends HomePage {

    public UserHomePage() {
        super("");
    }

    // Locators
    private final By personalProfileLink = By.xpath("//a[contains(text(),'Personal')]");
    private final By addNewPostLink = By.xpath("//a[contains(text(),'Add New')]");
    private final By logoutLink = By.xpath("//a[normalize-space()='LOGOUT']");
    private final By newFriendRequestsLink = By.xpath("//input[@value='New Friend Requsts']");
    private static final By approveRequestsLink = By.xpath("//input[@value='Approve Request']");
    private static final By approvedRequestMessage = By.xpath("//h3[@class='mb-3 bread']");
    private static final By youAreLoggedOutMessage = By.xpath("//i[normalize-space()='You have been logged out.']");

    // Actions
    public void clickPersonalProfile() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(personalProfileLink)).click();
    }

    public void clickAddNewPost() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(addNewPostLink)).click();
    }

    public void clickLogout() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(logoutLink)).click();
    }

    public void clickNewFriendRequests() {
        driver().scrollToElement(newFriendRequestsLink);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(newFriendRequestsLink)).click();
    }

    public void clickApproveRequests() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(approveRequestsLink)).click();
    }

    public static By getYouAreLoggedOutMessage() {
        return youAreLoggedOutMessage;
    }

    public static By getApproveRequestsLink() {
        return approvedRequestMessage;
    }
}
