package step;

import aquality.selenium.core.logging.Logger;
import framework.utils.VkApiUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import test.BaseTest;

public class EditWallPostStep {

	private static final VkApiUtils VK_API_UTILS = new VkApiUtils(BaseTest.SUITE_CONFIGURATION.getString("vk_api_url"),
			BaseTest.SUITE_CONFIGURATION.getString("user_token"),
			BaseTest.SUITE_CONFIGURATION.getString("vk_api_version"));
	private static final String RESPONSE_KEY = "response";

	public static int saveWallPhoto(int userId, String attachmentPath) {
		String uploadUrl = getUploadUrl();
		HttpResponse<JsonNode> uploadPhotoResponse = VK_API_UTILS.uploadPhotoToServer(uploadUrl, attachmentPath);
		Logger.getInstance().debug("Getting a server from the response");
		String server = getResponseBodyAttribute(uploadPhotoResponse, "server");
		Logger.getInstance().debug("Getting a hash from the response");
		String hash = getResponseBodyAttribute(uploadPhotoResponse, "hash");
		Logger.getInstance().debug("Getting a photo from the response");
		String photo = getResponseBodyAttribute(uploadPhotoResponse, "photo");
		HttpResponse<JsonNode> savePhotoResponse = VK_API_UTILS.saveWallPhoto(userId, server, hash, photo);
		Logger.getInstance().debug("Getting a photo id from the response");
		return savePhotoResponse.getBody().getObject().getJSONArray(RESPONSE_KEY).getJSONObject(0).getInt("id");
	}

	public static HttpResponse<JsonNode> editWallPost(int userId, int postId, int photoId, String newMessage) {
		return VK_API_UTILS.editWallPost(userId, postId, newMessage, String.format("photo%d_%d", userId, photoId));
	}

	private static String getUploadUrl() {
		HttpResponse<JsonNode> photosResponse = VK_API_UTILS.getPhotosWallUploadServer();
		return photosResponse.getBody().getObject().getJSONObject(RESPONSE_KEY).getString("upload_url");
	}

	private static String getResponseBodyAttribute(HttpResponse<JsonNode> uploadPhotoResponse, String attribute) {
		return uploadPhotoResponse.getBody().getObject().getString(attribute);
	}

}
