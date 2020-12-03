package framework.utils;

import java.io.File;

import aquality.selenium.core.logging.Logger;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class VkApiUtils {

	private static final String ACCESS_VERSION_URL_PART = "access_token=%s&v=%s";
	private static final String CREATE_POST_URL = "%s/method/wall.post?owner_id=%s&message=%s&%s";
	private static final String CREATE_POST_COMMENT_URL = "%s/method/wall.createComment?owner_id=%s&post_id=%s&message=%s&%s";
	private static final String EDIT_POST_URL = "%s/method/wall.edit?owner_id=%s&post_id=%s&message=%s&attachments=%s&%s";
	private static final String GET_PHOTOS_WALL_UPLOAD_SERVER_URL = "%s/method/photos.getWallUploadServer?%s";
	private static final String SAVE_WALL_PHOTO_URL = "%s/method/photos.saveWallPhoto?user_id=%s&server=%s&hash=%s&%s";
	private static final String GET_WALL_POSTS_URL = "%s/method/wall.getById?posts=%s&%s";
	private static final String DELETE_POST_URL = "%s/method/wall.delete?owner_id=%s&post_id=%s&%s";

	private final String baseApiUrl;
	private final String accessToken;
	private final String apiVersion;

	public VkApiUtils(String baseApiUrl, String accessToken, String apiVersion) {
		this.baseApiUrl = baseApiUrl;
		this.accessToken = accessToken;
		this.apiVersion = apiVersion;
	}

	public HttpResponse<JsonNode> createWallPost(int ownerId, String message) {
		Logger.getInstance().debug("Sending a request to create a post on the wall");
		return Unirest.post(String.format(CREATE_POST_URL, baseApiUrl, ownerId, message,
				String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion))).asJson();
	}

	public HttpResponse<JsonNode> createPostComment(int ownerId, int postId, String message) {
		Logger.getInstance().debug("Sending a request to create a comment on the post");
		return Unirest.post(String.format(CREATE_POST_COMMENT_URL, baseApiUrl, ownerId, postId, message,
				String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion))).asJson();
	}

	public HttpResponse<JsonNode> editWallPost(int ownerId, int postId, String message, String attachments) {
		Logger.getInstance().debug("Sending a request to edit a wall post");
		return Unirest.post(String.format(EDIT_POST_URL, baseApiUrl, ownerId, postId, message, attachments,
				String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion))).asJson();
	}

	public HttpResponse<JsonNode> getPhotosWallUploadServer() {
		Logger.getInstance().debug("Sending a request to get a photos wall upload server");
		return Unirest.post(String.format(GET_PHOTOS_WALL_UPLOAD_SERVER_URL, baseApiUrl,
				String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion))).asJson();
	}

	public HttpResponse<JsonNode> uploadPhotoToServer(String uploadUrl, String photoPath) {
		Logger.getInstance().debug("Sending a request to upload a photo to the server");
		return Unirest.post(uploadUrl).multiPartContent().field("photo", new File(photoPath)).asJson();
	}

	public HttpResponse<JsonNode> saveWallPhoto(int userId, String server, String hash, String photo) {
		Logger.getInstance().debug("Sending a request to save wall photo");
		return Unirest
				.post(String.format(SAVE_WALL_PHOTO_URL, baseApiUrl, userId, server, hash,
						String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion)))
				.multiPartContent().field("photo", photo).asJson();
	}

	public JSONObject getWallPost(int ownerId, int postId) {
		Logger.getInstance().debug("Sending a request to get a wall post");
		return Unirest
				.get(String.format(GET_WALL_POSTS_URL, baseApiUrl, String.format("%s_%s", ownerId, postId),
						String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion)))
				.asJson().getBody().getObject().getJSONArray("response").getJSONObject(0);
	}

	public HttpResponse<JsonNode> deleteWallPost(int ownerId, int postId) {
		Logger.getInstance().debug("Sending a request to delete a wall post");
		return Unirest.post(String.format(DELETE_POST_URL, baseApiUrl, ownerId, postId,
				String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion))).asJson();
	}

	public boolean isWallPostLikedByCurrentUser(JSONObject post) {
		Logger.getInstance().debug("Getting the 'user_likes' value from post");
		int isLiked = post.getJSONObject("likes").getInt("user_likes");
		return isLiked == 1;
	}

}
