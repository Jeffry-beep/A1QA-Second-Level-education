package step;

import org.apache.commons.lang3.RandomStringUtils;

import aquality.selenium.elements.interfaces.ITextBox;
import form.UserPage;
import framework.utils.VkApiUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public class CreateWallPostCommentStep {

	private final VkApiUtils vkApiUtils;
	private final UserPage userPage;
	private final int userId;

	private String commentText;

	public CreateWallPostCommentStep(VkApiUtils vkApiUtils, UserPage userPage, int userId) {
		this.vkApiUtils = vkApiUtils;
		this.userPage = userPage;
		this.userId = userId;
	}

	public ITextBox createWallPostComment(ITextBox post, int postId) {
		commentText = RandomStringUtils.randomAlphabetic(10);
		HttpResponse<JsonNode> createPostCommentResponse = vkApiUtils.createPostComment(userId, postId, commentText);
		int commentId = createPostCommentResponse.getBody().getObject().getJSONObject("response").getInt("comment_id");
		userPage.clickNextPostCommentsButton(post);
		ITextBox createdComment = userPage.getPost(userId, commentId);
		createdComment.getMouseActions().moveMouseToElement();
		return createdComment;
	}

	public String getCommentText() {
		return commentText;
	}

}
