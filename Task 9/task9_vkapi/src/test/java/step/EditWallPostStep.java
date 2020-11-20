package step;

import org.apache.commons.lang3.RandomStringUtils;

import framework.utils.VkApiUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public class EditWallPostStep {

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
		String server = getResponseBodyAttribute(uploadPhotoResponse, "server");
		String hash = getResponseBodyAttribute(uploadPhotoResponse, "hash");
		String photo = getResponseBodyAttribute(uploadPhotoResponse, "photo");
		HttpResponse<JsonNode> savePhotoResponse = vkApiUtils.saveWallPhoto(userId, server, hash, photo);
		photoId = savePhotoResponse.getBody().getObject().getJSONArray("response").getJSONObject(0).getInt("id");
		newMessage = RandomStringUtils.randomAlphabetic(10);
		return vkApiUtils.editWallPost(userId, postId, newMessage, String.format("photo%d_%d", userId, photoId));
	}

	private String getUploadUrl() {
		HttpResponse<JsonNode> photosResponse = vkApiUtils.getPhotosWallUploadServer();
		return photosResponse.getBody().getObject().getJSONObject("response").getString("upload_url");
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
