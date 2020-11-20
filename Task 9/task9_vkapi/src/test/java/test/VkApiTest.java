package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import aquality.selenium.elements.interfaces.ITextBox;
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

	private static final Logger LOGGER = LogManager.getLogger(VkApiTest.class);

	@Test
	public void test() {
		WelcomePage welcomePage = new WelcomePage();
		assertTrue(welcomePage.state().waitForDisplayed(), "The VK welcome page isn't displayed");

		LOGGER.info("Loggining");
		LoginStep loginStep = new LoginStep(suiteConfiguration.getString("user_email"),
				suiteConfiguration.getString("user_password"), welcomePage);
		loginStep.login();
		UserFeedPage userFeedPage = new UserFeedPage();
		assertTrue(userFeedPage.state().waitForDisplayed(), "The VK user feed page isn't displayed");

		LOGGER.info("Go to 'my page'");
		userFeedPage.clickMyPageButtonAndWait();
		UserPage userPage = new UserPage();
		assertTrue(userPage.state().waitForDisplayed(), "The VK user page isn't displayed");

		LOGGER.info("Creating wall post");
		int userId = suiteConfiguration.getInt("user_id");
		String username = suiteConfiguration.getString("username");
		VkApiUtils vkApiUtils = new VkApiUtils(suiteConfiguration.getString("user_token"),
				suiteConfiguration.getString("vk_api_version"));
		CreateWallPostStep createPostStep = new CreateWallPostStep(userId, vkApiUtils, userPage);
		ITextBox post = createPostStep.createWallPost();
		assertEquals(userPage.getPostCreator(post), username, "Actual post creator isn't equals expected.");
		assertEquals(userPage.getPostText(post), createPostStep.getCreatedPostText(),
				"Actual post text isn't equals expected.");

		LOGGER.info("Editing wall post");
		EditWallPostStep editPostStep = new EditWallPostStep(userId, createPostStep.getCreatedPostId(), vkApiUtils);
		editPostStep.editWallPost("B:\\upload.png");
		assertEquals(userPage.getPostText(post), editPostStep.getNewMessage(),
				"Actual post text isn't equals expected.");
		assertEquals(userPage.getPostPhotoId(post), String.format("%s_%s", userId, editPostStep.getPhotoId()),
				"Actual photo (photo id) isn't equals expected");

		LOGGER.info("Creating post comment");
		CreateWallPostCommentStep createPostComment = new CreateWallPostCommentStep(vkApiUtils, userPage, userId);
		ITextBox createdComment = createPostComment.createWallPostComment(post, createPostStep.getCreatedPostId());
		assertEquals(userPage.getCommentCreator(createdComment), username,
				"Actual comment creator isn't equals expected.");
		assertEquals(userPage.getCommentText(createdComment), createPostComment.getCommentText(),
				"Actual post text isn't equals expected.");

		LOGGER.info("Liking post");
		userPage.clickPostLikeButtonAndWait(post);
		JSONObject receivedPost = vkApiUtils.getWallPost(userId, createPostStep.getCreatedPostId());
		assertTrue(vkApiUtils.isWallPostLikedByCurrentUser(receivedPost), "The post isn't liked by a current user.");

		LOGGER.info("Deleting wall post");
		vkApiUtils.deleteWallPost(userId, createPostStep.getCreatedPostId());
		assertTrue(userPage.getPost(userId, createPostStep.getCreatedPostId()).state().waitForNotDisplayed(),
				"The post isn't deleted.");
	}
}
