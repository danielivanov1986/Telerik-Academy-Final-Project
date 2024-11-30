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

import static weare.pages.LatestPostsPage.exploreLastPostButton;

@Epic("Posts Tests")
public class PostsTests extends WEareBaseWebTest {

    @Test
    @Feature("Create Post")
    @Description("Tests the functionality of creating a new public post. " +
            "Verifies that a user can create a post and it appears in the feed.")
    public void createNewPostTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());
        userHomePage.clickLatestPosts();
        latestPostsPage.clickNewPublicPostButton();
        latestPostsPage.createNewPublicPost();

        //Assert
        AssertionUtils.assertElementVisible(DriverManager.getDriver(), "xpath", exploreLastPostButton);
    }

    @Test
    @Feature("Update Post")
    @Description("Tests the functionality of editing an existing public post. " +
            "Verifies that a user can successfully update a post.")
    public void updatePublicPostTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());
        userHomePage.clickLatestPosts();
        latestPostsPage.clickNewPublicPostButton();
        latestPostsPage.createNewPublicPost();
        latestPostsPage.clickExploreLastPostButton();
        latestPostsPage.clickEditPostButton();
        latestPostsPage.updatePublicPost();

        //Assert
        AssertionUtils.isTextVisible(DriverManager.getDriver(), LatestPostsPage.getUpdatedPost());
    }

    @Test
    @Feature("Like Post")
    @Description("Tests the functionality of liking a post. " +
            "Verifies that a user can click the like button and the text changes to 'Dislike'.")
    public void userLikesPostTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());
        userHomePage.clickLatestPosts();
        latestPostsPage.clickOnPostLikeButton();

        String likeButtonValue = latestPostsPage.getLikeButtonValue();

        //Assert
        AssertionUtils.assertEquals("The text of the like button should be 'Dislike'", likeButtonValue,
                "Dislike");
    }

    @Test
    public void userDislikesPostTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());
        userHomePage.clickLatestPosts();
        latestPostsPage.clickOnPostLikeButton();
        latestPostsPage.clickOnPostLikeButton();
        latestPostsPage.clickOnPostLikeButton();

        String dislikeButtonValue = latestPostsPage.getLikeButtonValue();

        //Assert
        AssertionUtils.assertEquals("The text of the like button should be 'Like'", dislikeButtonValue,
                "Like");
    }

    @Test
    public void deletePostTest() {
        homePage.navigate();
        homePage.clickRegister();
        registerPage.registerUser(TestData.REGISTER_USERNAME.getValue(), TestData.EMAIL.getValue(),
                TestData.REGISTER_PASSWORD.getValue(), TestData.REGISTER_PASSWORD.getValue());
        homePage.clickSigIn();
        signInPage.signIn(TestData.REGISTER_USERNAME.getValue(), TestData.REGISTER_PASSWORD.getValue());
        userHomePage.clickLatestPosts();
        latestPostsPage.clickNewPublicPostButton();
        latestPostsPage.createNewPublicPost();
        latestPostsPage.clickExploreLastPostButton();
        latestPostsPage.deletePost();


        //Assert
        AssertionUtils.assertElementVisible(DriverManager.getDriver(), "xpath",latestPostsPage.
                getDeletePostSuccessMessage());
    }
}
