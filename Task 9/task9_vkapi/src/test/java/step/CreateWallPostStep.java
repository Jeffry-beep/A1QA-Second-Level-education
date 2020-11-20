package step;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import aquality.selenium.elements.interfaces.ITextBox;
import form.UserPage;
import framework.utils.VkApiUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public class CreateWallPostStep {

	private static final Logger LOGGER = LogManager.getLogger(CreateWallPostStep.class);

	private final int userId;
	private final VkApiUtils vkApiUtils;
	private final UserPage userPage;

	private String createdPostText;
	private int createdPostId;

	public CreateWallPostStep(int userId, VkApiUtils vkApiUtils, UserPage userPage) {
		this.userId = userId;
		this.vkApiUtils = vkApiUtils;
		this.userPage = userPage;
	}

	public ITextBox createWallPost() {
		createdPostText = RandomStringUtils.randomAlphabetic(10);
		HttpResponse<JsonNode> response = vkApiUtils.createWallPost(userId, createdPostText);
		LOGGER.debug("Getting a 'post_id' value from  the response of the request");
		createdPostId = response.getBody().getObject().getJSONObject("response").getInt("post_id");
		return userPage.getPost(userId, createdPostId);
	}

	public String getCreatedPostText() {
		return createdPostText;
	}

	public int getCreatedPostId() {
		return createdPostId;
	}

}
