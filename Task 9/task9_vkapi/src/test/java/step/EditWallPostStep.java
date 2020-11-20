package step;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import form.UserPage;
import framework.utils.VkApiUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public class EditWallPostStep {

	private static final Logger LOGGER = LogManager.getLogger(UserPage.class);
	private static final String RESPONSE_KEY = "response";

	private final int userId;
	private final int postId;
	private final VkApiUtils vkApiUtils;

	private int photoId;
	private String newMessage;

	public EditWallPostStep(int userId, int postId, VkApiUtils vkApiUtils) {
		this.userId = userId;
		this.postId = postId;
		this.vkApiUtils = vkApiUtils;
	}

	public HttpResponse<JsonNode> editWallPost(String attachmentPath) {
		String uploadUrl = getUploadUrl();
		HttpResponse<JsonNode> uploadPhotoResponse = vkApiUtils.uploadPhotoToServer(uploadUrl, attachmentPath);
		LOGGER.debug("Getting a server from the response");
		String server = getResponseBodyAttribute(uploadPhotoResponse, "server");
		LOGGER.debug("Getting a hash from the response");
		String hash = getResponseBodyAttribute(uploadPhotoResponse, "hash");
		LOGGER.debug("Getting a photo from the response");
		String photo = getResponseBodyAttribute(uploadPhotoResponse, "photo");
		HttpResponse<JsonNode> savePhotoResponse = vkApiUtils.saveWallPhoto(userId, server, hash, photo);
		LOGGER.debug("Getting a photo id from the response");
		photoId = savePhotoResponse.getBody().getObject().getJSONArray(RESPONSE_KEY).getJSONObject(0).getInt("id");
		newMessage = RandomStringUtils.randomAlphabetic(10);
		return vkApiUtils.editWallPost(userId, postId, newMessage, String.format("photo%d_%d", userId, photoId));
	}

	private String getUploadUrl() {
		HttpResponse<JsonNode> photosResponse = vkApiUtils.getPhotosWallUploadServer();
		return photosResponse.getBody().getObject().getJSONObject(RESPONSE_KEY).getString("upload_url");
	}

	private String getResponseBodyAttribute(HttpResponse<JsonNode> uploadPhotoResponse, String attribute) {
		return uploadPhotoResponse.getBody().getObject().getString(attribute);
	}

	public int getPhotoId() {
		return photoId;
	}

	public String getNewMessage() {
		return newMessage;
	}

}
