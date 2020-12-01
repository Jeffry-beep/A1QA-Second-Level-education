package step;

import framework.utils.VkApiUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import test.BaseTest;

public class CreateWallPostCommentStep {

	private static final VkApiUtils VK_API_UTILS = new VkApiUtils(BaseTest.SUITE_CONFIGURATION.getString("vk_api_url"),
			BaseTest.SUITE_CONFIGURATION.getString("user_token"),
			BaseTest.SUITE_CONFIGURATION.getString("vk_api_version"));

	public static int createWallPostComment(int userId, int postId, String commentText) {
		HttpResponse<JsonNode> createPostCommentResponse = VK_API_UTILS.createPostComment(userId, postId, commentText);
		return createPostCommentResponse.getBody().getObject().getJSONObject("response").getInt("comment_id");
	}

}
