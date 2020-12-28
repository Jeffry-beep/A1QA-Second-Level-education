package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import aquality.selenium.core.logging.Logger;
import form.UserFeedPage;
import form.UserPage;
import form.WelcomePage;
import framework.utils.VkApiUtils;
import kong.unirest.json.JSONObject;
import step.CreateWallPostCommentStep;
import step.CreateWallPostStep;
import step.EditWallPostStep;
import step.LoginStep;

public class VkApiTest extends BaseTest {

	@Test
	public void test() {
		WelcomePage welcomePage = new WelcomePage();
		assertTrue(welcomePage.state().waitForDisplayed(), "The VK welcome page isn't displayed");

		Logger.getInstance().info("Loggining");
		LoginStep.login(SUITE_CONFIGURATION.getString("user_email"), SUITE_CONFIGURATION.getString("user_password"));
		UserFeedPage userFeedPage = new UserFeedPage();
		assertTrue(userFeedPage.state().waitForDisplayed(), "The VK user feed page isn't displayed");

		Logger.getInstance().info("Go to 'my page'");
		userFeedPage.clickMyPageButton();
		UserPage userPage = new UserPage();
		assertTrue(userPage.state().waitForDisplayed(), "The VK user page isn't displayed");

		Logger.getInstance().info("Creating wall post");
		int userId = SUITE_CONFIGURATION.getInt("user_id");
		String username = SUITE_CONFIGURATION.getString("username");
		VkApiUtils vkApiUtils = new VkApiUtils(SUITE_CONFIGURATION.getString("vk_api_url"),
				SUITE_CONFIGURATION.getString("user_token"), SUITE_CONFIGURATION.getString("vk_api_version"));
		String postText = RandomStringUtils.randomAlphabetic(10);
		int createdPostId = CreateWallPostStep.createWallPost(userId, postText);
		assertEquals(userPage.getPostCreator(userId, createdPostId), username,
				"Actual post creator isn't equals expected.");
		assertEquals(userPage.getPostText(userId, createdPostId), postText, "Actual post text isn't equals expected.");

		Logger.getInstance().info("Editing wall post");
		int photoId = EditWallPostStep.saveWallPhoto(userId, SUITE_CONFIGURATION.getString("attachment_path"));
		String newMessage = RandomStringUtils.randomAlphabetic(10);
		EditWallPostStep.editWallPost(userId, createdPostId, photoId, newMessage);
		userPage.waitForPostPhotoDisplayed(userId, createdPostId);
		assertEquals(userPage.getPostText(userId, createdPostId), newMessage,
				"Actual post text isn't equals expected.");
		assertEquals(userPage.getPostPhotoId(userId, createdPostId),
				String.format("%s_%s", userId, photoId, "Actual photo (photo id) isn't equals expected"));

		Logger.getInstance().info("Creating post comment");
		String commentText = RandomStringUtils.randomAlphabetic(10);
		int createdCommentId = CreateWallPostCommentStep.createWallPostComment(userId, createdPostId, commentText);
		userPage.clickNextPostCommentsButton(userId, createdPostId);
		userPage.scrollToComment(userId, createdCommentId);
		userPage.waitForCommentTextDisplayed(userId, createdCommentId, commentText);
		assertEquals(userPage.getCommentCreator(userId, createdCommentId), username,
				"Actual comment creator isn't equals expected.");
		assertEquals(userPage.getCommentText(userId, createdCommentId), commentText,
				"Actual post text isn't equals expected.");

		Logger.getInstance().info("Liking post");
		userPage.clickPostLikeButton(userId, createdPostId);
		userPage.waitForPostLikeCountDisplayed(userId, createdPostId);
		JSONObject receivedPost = vkApiUtils.getWallPost(userId, createdPostId);
		assertTrue(vkApiUtils.isWallPostLikedByCurrentUser(receivedPost), "The post isn't liked by a current user.");

		Logger.getInstance().info("Deleting wall post");
		vkApiUtils.deleteWallPost(userId, createdPostId);
		assertTrue(userPage.waitForPostNotDisplayed(userId, createdPostId), "The post isn't deleted.");
	}
}
