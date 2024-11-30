package wearetests.web;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import testframework.DriverManager;
import weare.pages.LatestPostsPage;
import wearetests.core.AssertionUtils;
import wearetests.core.WEareBaseWebTest;
import wearetests.enums.TestData;

@Epic("Comments Tests")
public class CommentsTests extends WEareBaseWebTest {

    @Test
    @Feature("Create Comment")
    @Description("Test the ability of a user to create a comment on a public post. " +
            "Ensures that the comment is visible.")
    public void createCommentOnPostTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());
        userHomePage.clickLatestPosts();
        latestPostsPage.clickPublicPostsButton();
        latestPostsPage.clickExploreLastPostButton();
        latestPostsPage.writeComment();
        latestPostsPage.clickShowCommentsButton();

        //Assert
        AssertionUtils.isTextVisible(DriverManager.getDriver(), LatestPostsPage.getComment());
    }

    @Test
    @Feature("Like Comment")
    @Description("Test the functionality of liking a comment. " +
            "Verifies that a user can like a comment, and the button changes to 'Dislike'.")
    public void likeCommentOnPostTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());
        userHomePage.clickLatestPosts();
        latestPostsPage.clickPublicPostsButton();
        latestPostsPage.clickExploreLastPostButton();
        latestPostsPage.writeComment();
        latestPostsPage.clickShowCommentsButton();
        latestPostsPage.clickOnCommentsLikeButton();

        String likeButtonValue = latestPostsPage.getCommentLikeButtonValue();

        //Assert
        AssertionUtils.assertEquals("The text of the like button should be 'Dislike'",
                likeButtonValue, "Dislike");
    }

    @Test
    @Feature("Dislike Comment")
    @Description("Test the functionality of disliking a comment. " +
            "Verifies that a user can dislike a previously liked comment and the button changes to 'Like'.")
    public void dislikeCommentOnPostTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());
        userHomePage.clickLatestPosts();
        latestPostsPage.clickPublicPostsButton();
        latestPostsPage.clickExploreLastPostButton();
        latestPostsPage.writeComment();
        latestPostsPage.clickShowCommentsButton();
        latestPostsPage.clickOnCommentsLikeButton();
        latestPostsPage.clickOnCommentsLikeButton();

        String likeButtonValue = latestPostsPage.getCommentLikeButtonValue();

        //Assert
        AssertionUtils.assertEquals("The text of the like button should be 'Like'", likeButtonValue,
                "Like");
    }

    @Test
    @Feature("Edit Comment")
    @Description("Test the functionality of editing a comment on a post. " +
            "Verifies that a user can edit their comment and the updated comment is visible.")
    public void editCommentOnPostTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());
        userHomePage.clickLatestPosts();
        latestPostsPage.clickPublicPostsButton();
        latestPostsPage.clickExploreLastPostButton();
        latestPostsPage.writeComment();
        latestPostsPage.clickShowCommentsButton();
        latestPostsPage.editComment();
        latestPostsPage.clickOnEditedCommentButton();

        //Assert
        AssertionUtils.isTextVisible(DriverManager.getDriver(), LatestPostsPage.getEditedComment());
    }

    @Test
    @Feature("Delete Comment")
    @Description("Test the ability of a user to delete their comment from a post. " +
            "Verifies that the comment is successfully deleted and the success message is visible.")
    public void deleteCommentOnPostTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());
        userHomePage.clickLatestPosts();
        latestPostsPage.clickPublicPostsButton();
        latestPostsPage.clickExploreLastPostButton();
        latestPostsPage.writeComment();
        latestPostsPage.clickShowCommentsButton();
        latestPostsPage.deleteComment();

        //Assert
        AssertionUtils.assertElementVisible(DriverManager.getDriver(), "xpath",latestPostsPage.
                getDeleteCommentSuccessMessage());
    }
}
