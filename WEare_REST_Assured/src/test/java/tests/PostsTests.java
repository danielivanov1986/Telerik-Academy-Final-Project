package tests;

import base.BaseTestSetup;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.eclipse.sisu.PreDestroy;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static com.weare.Constants.*;


public class PostsTests extends BaseTestSetup {

    @Test
    @Story("Create Post Test")
    @Description("Test Description: Verify creating a public post")
    public void createPublicPostTest() {
        Allure.step("Creating and registering user for post creation");
        createAndRegisterUser();
        loginUser();

        Allure.step("Creating a new public post");
        Response response = createPost();

        String postID = response.jsonPath().getString("postId");
        String postContent = response.jsonPath().getString("content");

        //Assert
        Allure.step("Verifying post creation success and content");
        Assert.assertEquals(200, response.statusCode(), "Expected status code to be 200");
        Allure.step("Verifying post ID is not null");
        Assert.assertNotNull(postID, "Post ID should not be null");
        Allure.step("Verifying post content is correct");
        Assert.assertEquals(postContent, POST_DESCRIPTION,
                String.format("Post content should be %s", POST_DESCRIPTION));
    }

    @Test
    @Story("Get All Posts Test")
    @Description("Test Description: Verify retrieving all posts")
    public void getAllPostsTest() {
        Allure.step("Getting all user posts");
        Response response = showAllPosts();

        Allure.step("Verifying that the status code is 200");
        Assert.assertEquals(200, response.statusCode(), "Expected status code should be 200");
    }

    @Test
    @Story("Update Post Test")
    @Description("Test Description: Verify updating a public post")
    public void updatePublicPostTest() {
        Allure.step("Creating and registering a user, then logging in");

        createAndRegisterUser();
        loginUser();
        Allure.step("Creating a new public post");
        createPost();
        Allure.step("Editing the public post");

        Response response = editProfilePost();

        Allure.step("Verifying that the status code is 200 after editing the post");
        Assert.assertEquals(response.statusCode(), 200, "Expected status code to be 200");

    }

    @Test
    @Story("Like Public Post Test")
    @Description("Test Description: Verify liking a public post")
    public void likeAPublicPostTest() {
        Allure.step("Creating and registering a user, then logging in");
        createAndRegisterUser();
        loginUser();

        Allure.step("Creating a new public post");
        createPost();

        Allure.step("Editing the public post");
        editProfilePost();

        Allure.step("Liking the public post");
        Response response = likePost();
        boolean liked = response.jsonPath().getBoolean("liked");

        String responseBody = response.getBody().prettyPrint();
        System.out.println("Response body: " + responseBody);

        Allure.step("Verifying that the status code is 200 after liking the post");
        Assert.assertEquals(response.statusCode(), 200, "Expected statuc code to be 200");
        Allure.step("Verifying that the post is liked");
        Assert.assertTrue(liked, "Expected status should be true for liked post");

    }

    @Test
    @Story("Delete Post Test")
    @Description("Test Description: Verify deleting a public post")
    public void deletePublicPostTest() {
        Allure.step("Creating and registering a user, then logging in");
        createAndRegisterUser();
        loginUser();

        Allure.step("Creating a new public post");
        createPost();

        Allure.step("Editing the public post");
        editProfilePost();

        Allure.step("Deleting the public post");
        Response response = deletePost();

        String responseBody = response.getBody().prettyPrint();

        Allure.step("Verifying that the status code is 200 after deleting the post");
        Assert.assertEquals(200, response.statusCode(), "Expected status code to be 200");
        Allure.step("Verifying that the response body is empty after deleting the post");
        Assert.assertEquals(responseBody, "", "Response body should be empty");
    }
}