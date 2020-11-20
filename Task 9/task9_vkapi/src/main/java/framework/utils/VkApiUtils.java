package framework.utils;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class VkApiUtils {

	private static final Logger LOGGER = LogManager.getLogger(VkApiUtils.class);
	private static final String CREATE_POST_URL = "https://api.vk.com/method/wall.post?owner_id=%s&message=%s&access_token=%s&v=%s";
	private static final String CREATE_POST_COMMENT_URL = "https://api.vk.com/method/wall.createComment?owner_id=%s&post_id=%s&message=%s&access_token=%s&v=%s";
	private static final String EDIT_POST_URL = "https://api.vk.com/method/wall.edit?owner_id=%s&post_id=%s&message=%s&attachments=%s&access_token=%s&v=%s";
	private static final String GET_PHOTOS_WALL_UPLOAD_SERVER_URL = "https://api.vk.com/method/photos.getWallUploadServer?access_token=%s&v=%s";
	private static final String SAVE_WALL_PHOTO_URL = "https://api.vk.com/method/photos.saveWallPhoto?user_id=%s&server=%s&hash=%s&access_token=%s&v=%s";
	private static final String GET_WALL_POSTS_URL = "https://api.vk.com/method/wall.getById?posts=%s&access_token=%s&v=%s";
	private static final String DELETE_POST_URL = "https://api.vk.com/method/wall.delete?owner_id=%s&post_id=%s&access_token=%s&v=%s";

	private final String accessToken;
	private final String apiVersion;

	public VkApiUtils(String accessToken, String apiVersion) {
		this.accessToken = accessToken;
		this.apiVersion = apiVersion;
	}

	public HttpResponse<JsonNode> createWallPost(int ownerId, String message) {
		LOGGER.debug("Sending a request to create a post on the wall");
		return Unirest.post(String.format(CREATE_POST_URL, ownerId, message, accessToken, apiVersion)).asJson();
	}

	public HttpResponse<JsonNode> createPostComment(int ownerId, int postId, String message) {
		LOGGER.debug("Sending a request to create a comment on the post");
		return Unirest.post(String.format(CREATE_POST_COMMENT_URL, ownerId, postId, message, accessToken, apiVersion))
				.asJson();
	}

	public HttpResponse<JsonNode> editWallPost(int ownerId, int postId, String message, String attachments) {
		LOGGER.debug("Sending a request to edit a wall post");
		return Unirest
				.post(String.format(EDIT_POST_URL, ownerId, postId, message, attachments, accessToken, apiVersion))
				.asJson();
	}

	public HttpResponse<JsonNode> getPhotosWallUploadServer() {
		LOGGER.debug("Sending a request to get a photos wall upload server");
		return Unirest.post(String.format(GET_PHOTOS_WALL_UPLOAD_SERVER_URL, accessToken, apiVersion)).asJson();
	}

	public HttpResponse<JsonNode> uploadPhotoToServer(String uploadUrl, String photoPath) {
		LOGGER.debug("Sending a request to upload a photo to the server");
		return Unirest.post(uploadUrl).multiPartContent().field("photo", new File(photoPath)).asJson();
	}

	public HttpResponse<JsonNode> saveWallPhoto(int userId, String server, String hash, String photo) {
		LOGGER.debug("Sending a request to save wall photo");
		return Unirest.post(String.format(SAVE_WALL_PHOTO_URL, userId, server, hash, accessToken, apiVersion))
				.multiPartContent().field("photo", photo).asJson();
	}

	public JSONObject getWallPost(int ownerId, int postId) {
		LOGGER.debug("Sending a request to get a wall post");
		return Unirest.get(
				String.format(GET_WALL_POSTS_URL, String.format("%s_%s", ownerId, postId), accessToken, apiVersion))
				.asJson().getBody().getObject().getJSONArray("response").getJSONObject(0);
	}

	public HttpResponse<JsonNode> deleteWallPost(int ownerId, int postId) {
		LOGGER.debug("Sending a request to delete a wall post");
		return Unirest.post(String.format(DELETE_POST_URL, ownerId, postId, accessToken, apiVersion)).asJson();
	}

	public boolean isWallPostLikedByCurrentUser(JSONObject post) {
		LOGGER.debug("Getting the 'user_likes' value from post");
		int isLiked = post.getJSONObject("likes").getInt("user_likes");
		return isLiked == 1;
	}

}
