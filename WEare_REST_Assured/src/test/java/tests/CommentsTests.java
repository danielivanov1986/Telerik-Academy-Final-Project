package tests;

import base.BaseTestSetup;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static com.weare.Constants.*;

public class CommentsTests extends BaseTestSetup {
    @Test
    @Story("Create Comment Test")
    @Description("Test Description: Verify creating a comment on a post")
    public void createCommentTest() {
        Allure.step("Creating and registering user for comment creation");
        createAndRegisterUser();
        loginUser();
        Allure.step("Creating a new post to add a comment");
        createPost();

        Allure.step("Creating a comment on the newly created post");
        Response response = createComment();

        String commentID = response.jsonPath().getString("commentId");
        String commentContent = response.jsonPath().getString("content");

        //Assert
        Allure.step("Verifying that the comment was created successfully");
        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
        Assert.assertEquals(commentContent, COMMENT_DESCRIPTION, "Comment content should match the expected value");
        Assert.assertNotNull(commentID, "Comment ID should not be null");
    }

    @Test
    @Story("Get Comments for Post Test")
    @Description("Test Description: Verify retrieving comments for a post")
    public void getCommentsForPostTest() {
        Allure.step("Creating and registering user for comment creation");
        createAndRegisterUser();
        loginUser();
        createPost();
        createComment();

        Allure.step("Retrieving all comments for the post");
        Response response = getComment();
        String createdCommentID = response.getBody().jsonPath().getString("commentId");
        System.out.println("Response body is " + response.getBody().asString());

        //Assert
        Allure.step("Verifying that the retrieved comment matches the expected ID");
        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
        Assert.assertEquals(createdCommentID, COMMENT_ID, String.format("Incorrect comment ID. Expected %s", COMMENT_ID));
    }

    @Test
    @Story("Edit Comment Test")
    @Description("Test Description: Verify editing a comment")
    public void editCommentTest() {
        Allure.step("Creating user, post, and comment for editing the comment");
        createAndRegisterUser();
        loginUser();
        createPost();
        createComment();

        Allure.step("Editing the created comment");
        Response response = editComment();

        //Assert
        Allure.step("Verifying the comment edit status and response");
        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
        Assert.assertEquals(response.getBody().asString(), "", "Expected response body should be empty");
    }

    @Test
    @Story("Delete Comment Test")
    @Description("Test Description: Verify deleting a comment")
    public void deleteCommentTest() {
        Allure.step("Creating user, post, and comment for deletion");
        createAndRegisterUser();
        loginUser();
        createPost();
        createComment();
        Allure.step("Deleting the created comment");
        Response response = deleteComment();

        //Assert
        Allure.step("Verifying that the comment was deleted successfully");
        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
        Assert.assertEquals(response.getBody().asString(), "", "Expected response body should be empty");
    }

    @Test
    @Story("Like Comment Test")
    @Description("Test Description: Verify liking a comment")
    public void likeCommentTest() {
        Allure.step("Creating user, post, and comment for liking the comment");
        createAndRegisterUser();
        loginUser();
        createPost();
        createComment();
        Allure.step("Liking the comment");
        Response response = likeComment();

        int commentIdFromResponse = response.jsonPath().getInt("commentId");
        int expectedCommentId = Integer.parseInt(COMMENT_ID);
        boolean isLiked = response.jsonPath().getBoolean("liked");

        //Assert
        Allure.step("Verifying the like status of the comment");
        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");
        Assert.assertEquals(commentIdFromResponse, expectedCommentId,
                String.format("Expected comment ID to be: %s", expectedCommentId));
        Assert.assertTrue(isLiked, "Status should be TRUE for liked comment");
    }

    @Test
    @Story("Dislike Comment Test")
    @Description("Test Description: Verify disliking a comment")
    public void dislikeCommentTest() {
        Allure.step("Creating user, post, and comment for disliking the comment");
        createAndRegisterUser();
        loginUser();
        createPost();
        createComment();

        Allure.step("Disliking the comment");
        Response response = likeComment();
        Response dislikeResponse = likeComment();

        int commentIdFromResponse = dislikeResponse.jsonPath().getInt("commentId");
        int expectedCommentId = Integer.parseInt(COMMENT_ID);
        boolean isLiked = dislikeResponse.jsonPath().getBoolean("liked");

        //Assert
        Allure.step("Verifying the dislike status of the comment");
        Assert.assertEquals(dislikeResponse.statusCode(), 200, "Expected status code to be 200");
        Assert.assertEquals(commentIdFromResponse, expectedCommentId,
                String.format("Expected comment ID to be: %s", expectedCommentId));
        Assert.assertFalse(isLiked, "Status should be FALSE for disliked comment");
    }
}
