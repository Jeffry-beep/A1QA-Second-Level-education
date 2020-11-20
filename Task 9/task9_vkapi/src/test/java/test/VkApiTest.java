package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import aquality.selenium.elements.interfaces.ITextBox;
import form.UserFeedPage;
import form.UserPage;
import form.WelcomePage;
import framework.utils.VkApiUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import step.CreateWallPostStep;
import step.EditWallPostStep;
import step.LoginStep;

public class VkApiTest extends BaseTest {

	@Test
	public void test() {
		WelcomePage welcomePage = new WelcomePage();
		assertTrue(welcomePage.state().waitForDisplayed(), "The VK welcome page isn't displayed");

		LoginStep loginStep = new LoginStep(suiteConfiguration.getString("user_email"),
				suiteConfiguration.getString("user_password"), welcomePage);
		loginStep.login();
		UserFeedPage userFeedPage = new UserFeedPage();
		assertTrue(userFeedPage.state().waitForDisplayed(), "The VK user feed page isn't displayed");

		userFeedPage.clickMyPageButtonAndWait();
		UserPage userPage = new UserPage();
		assertTrue(userPage.state().waitForDisplayed(), "The VK user page isn't displayed");

		int userId = suiteConfiguration.getInt("user_id");
		VkApiUtils vkApiUtils = new VkApiUtils(suiteConfiguration.getString("user_token"),
				suiteConfiguration.getString("vk_api_version"));
		CreateWallPostStep createPostStep = new CreateWallPostStep(userId, vkApiUtils, userPage);
		ITextBox post = createPostStep.createWallPost();
		assertEquals(userPage.getPostCreator(post), suiteConfiguration.getString("username"),
				"Actual post creator isn't equals expected.");
		assertEquals(userPage.getPostText(post), createPostStep.getCreatedPostText(),
				"Actual post text isn't equals expected.");

		EditWallPostStep editPostStep = new EditWallPostStep(userId, createPostStep.getCreatedPostId(), vkApiUtils);
		editPostStep.editWallPost("B:\\upload.png");
		assertEquals(userPage.getPostText(post), editPostStep.getNewMessage(),
				"Actual post text isn't equals expected.");
		assertEquals(userPage.getPostPhotoId(post), String.format("%s_%s", userId, editPostStep.getPhotoId()),
				"Actual photo (photo id) isn't equals expected");

		String commentText = RandomStringUtils.randomAlphabetic(10);
		HttpResponse<JsonNode> createPostCommentResponse = vkApiUtils.createPostComment(userId,
				createPostStep.getCreatedPostId(), commentText);
		int commentId = createPostCommentResponse.getBody().getObject().getJSONObject("response").getInt("comment_id");
		userPage.clickNextPostCommentsButton(post);
		ITextBox createdComment = userPage.getPost(userId, commentId);
		createdComment.getMouseActions().moveMouseToElement();
		assertEquals(userPage.getCommentCreator(createdComment), suiteConfiguration.getString("username"),
				"Actual comment creator isn't equals expected.");
		assertEquals(userPage.getCommentText(createdComment), commentText, "Actual post text isn't equals expected.");

		userPage.clickPostLikeButtonAndWait(post);
		JSONObject receivedPost = vkApiUtils.getWallPost(userId, createPostStep.getCreatedPostId());
		assertTrue(vkApiUtils.isWallPostLikedByCurrentUser(receivedPost));

		vkApiUtils.deleteWallPost(userId, createPostStep.getCreatedPostId());
		assertTrue(userPage.getPost(userId, createPostStep.getCreatedPostId()).state().waitForNotDisplayed());
	}
}
