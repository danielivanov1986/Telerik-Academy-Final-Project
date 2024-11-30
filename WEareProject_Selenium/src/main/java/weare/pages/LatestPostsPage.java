package weare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LatestPostsPage extends HomePage {

    public LatestPostsPage() {
        super("/posts");
    }

    //Locators
    private final By seeUserProfileButton = By.xpath("//body//section[@class='ftco-section ftco-candidates ftco-candidates-2 bg-light']" +
            "//div[@class='row']//div[@class='row']//div[1]//div[1]//div[2]//p[3]//a[2]");
    //Posts locators
    private final By publicPostsBrowseButton = By.xpath("//div[@class='col-lg-4 sidebar']//div[1]//form[1]//input[1]");
    private final By postVisibilityField = By.xpath("//select[@id='StringListId' and @name='public']");
    private final By publicVisibilityField = By.xpath("//option[@value='true' and text()='Public post']");
    private final By postTextArea = By.xpath("//textarea[@id='message']");
    private final By savePostButton = By.xpath("//input[@value='Save post']");
    private final By newPostButton = By.xpath("//a[normalize-space()='New post']");
    private static final By lastPostLikeButton = By.xpath("(//input[contains(@id, 'submit-val') and contains(@value, '')])[1]");
    public static final By exploreLastPostButton = By.xpath("//body//section[@class='" +
            "ftco-section ftco-candidates ftco-candidates-2 bg-light']//div[@class='row']//div[@class='row']" +
            "//div[1]//div[1]//div[2]//p[3]//a[1]");
    private final By editPostButton = By.xpath("//a[normalize-space()='Edit post']");
    private final By deletePostButtonNavigation = By.xpath("//a[normalize-space()='Delete post']");
    private final By deletePostField = By.xpath("//select[@id='StringListId']");
    private final By deletePostFieldOption = By.xpath("//option[@value='true' and text()='Delete post']");
    private final By deletePostButton = By.xpath("//input[@value='Submit']");
    private final By deleteSuccessMessage = By.xpath("//h1[@class='mb-3 bread']");
    //Comments locators
    private final By commentField = By.id("message");
    private final By postCommentButton = By.xpath("//input[@value='Post Comment']");
    private final By showComments = By.className("show-comments");
    private final By likeLastCommentButton = By.xpath("//input[contains(@id, 'submit-val') and contains(@value, '')]");
    private final By editCommentButton = By.xpath("//a[normalize-space()='Edit comment']");
    private final By postEditCommentButton = By.xpath("//input[@value='Edit Comment']");
    private final By deleteCommentButton = By.xpath("//a[normalize-space()='Delete comment']");
    private final By deleteCommentField = By.xpath("//select[@id='StringListId']");
    private final By deleteCommentOption = By.xpath("//option[@value='true' and text()='Delete comment']");
    private final By deleteButtonSubmitButton = By.xpath("//input[@value='Submit']");
    private final By deleteCommentSuccessMessage = By.xpath("//h1[@class='mb-3 bread']");
    public static String comment = "This is a new comment";
    public static String editedComment = "This is edited comment";
    public static String publicPost = "This is a public post";
    public static String updatedPost = "This is a updated post";
    //optional;
    private final By registerButton = By.xpath("//a[normalize-space()='REGISTER']");

    //Actions
    //Users
    public void clickSeeProfileButton() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(seeUserProfileButton)).click();
    }

    //Posts
    public void clickNewPublicPostButton() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(newPostButton)).click();
    }

    public void createNewPublicPost() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(postVisibilityField)).click();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(publicVisibilityField)).click();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(postTextArea)).sendKeys(publicPost);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(savePostButton)).click();
    }

    public void updatePublicPost() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(postVisibilityField)).click();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(publicVisibilityField)).click();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(postTextArea)).sendKeys(updatedPost);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(savePostButton)).click();
    }

    public void clickPublicPostsButton() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(publicPostsBrowseButton)).click();
    }

    public void clickEditPostButton() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(editPostButton)).click();
    }

    public void clickExploreLastPostButton() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(exploreLastPostButton)).click();
    }

    public void clickOnPostLikeButton() {
        driver().scrollToElement(lastPostLikeButton);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(lastPostLikeButton)).click();
    }

    public void deletePost() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(deletePostButtonNavigation)).click();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(deletePostField)).click();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(deletePostFieldOption)).click();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(deletePostButton)).click();
    }

    public By getDeletePostSuccessMessage() {
        return deleteSuccessMessage;
    }

    public By getDeleteCommentSuccessMessage() {
        return deleteCommentSuccessMessage;
    }

    //Comments
    public void writeComment() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(commentField)).sendKeys(comment);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(postCommentButton)).click();
    }

    public void clickShowCommentsButton() {
        driver().scrollToElement(showComments);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(showComments)).click();
    }

    public void clickOnCommentsLikeButton() {
        driver().scrollToElement(likeLastCommentButton);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(likeLastCommentButton)).click();
    }

    public void editComment() {
        driver().scrollToElement(editCommentButton);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(editCommentButton)).click();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(commentField)).sendKeys(editedComment);
    }

    public void clickOnEditedCommentButton() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(postEditCommentButton)).click();

    }

    public void deleteComment() {
        driver().scrollToElement(deleteCommentButton);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(deleteCommentButton)).click();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(deleteCommentField)).click();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(deleteCommentOption)).click();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(deleteButtonSubmitButton)).click();

    }

    public static String getUpdatedPost() {
        return updatedPost;
    }

    public static String getComment() {
        return comment;
    }

    public static String getEditedComment() {
        return editedComment;
    }

    public String getLikeButtonValue() {
        WebElement lastLikeButton = driverWait().until(ExpectedConditions.visibilityOfElementLocated(lastPostLikeButton));
        driver().scrollToElement(lastPostLikeButton);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(lastPostLikeButton));
        String buttonValue = lastLikeButton.getAttribute("value");

        return getButtonValue(buttonValue);
    }

    public String getCommentLikeButtonValue() {
        WebElement lastLikeButton = driverWait().until(ExpectedConditions.visibilityOfElementLocated(likeLastCommentButton));
        driver().scrollToElement(likeLastCommentButton);
        String buttonValue = lastLikeButton.getAttribute("value");

        return getButtonValue(buttonValue);
    }

    private String getButtonValue(String buttonValue) {
        String buttonState = null;
        if ("Dislike".equals(buttonValue)) {
            buttonState = "Dislike";
        } else if ("Like".equals(buttonValue)) {
            buttonState = "Like";
        }
        return buttonState;
    }

}

