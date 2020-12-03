package step;

import aquality.selenium.core.logging.Logger;
import framework.utils.VkApiUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import test.BaseTest;

public class CreateWallPostStep {

	private static final VkApiUtils VK_API_UTILS = new VkApiUtils(BaseTest.SUITE_CONFIGURATION.getString("vk_api_url"),
			BaseTest.SUITE_CONFIGURATION.getString("user_token"),
			BaseTest.SUITE_CONFIGURATION.getString("vk_api_version"));

	public static int createWallPost(int userId, String postText) {
		HttpResponse<JsonNode> response = VK_API_UTILS.createWallPost(userId, postText);
		Logger.getInstance().debug("Getting a 'post_id' value from  the response of the request");
		return response.getBody().getObject().getJSONObject("response").getInt("post_id");
	}

}
