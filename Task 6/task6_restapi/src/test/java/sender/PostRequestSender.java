package sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entities.Post;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class PostRequestSender {

	private static final Logger LOGGER = LogManager.getLogger(PostRequestSender.class);
	private static final String GET_ALL_POSTS = "%s/posts";
	private static final String GET_POST_BY_ID = "%s/posts/{postId}";

	public static HttpResponse<String> getPostsAsString(String baseUrl) {
		LOGGER.debug("Send a GET request to get all posts");
		return Unirest.get(String.format(GET_ALL_POSTS, baseUrl)).asString();
	}

	public static HttpResponse<JsonNode> getPostsAsJSON(String baseUrl) {
		LOGGER.debug("Send a GET request to get all posts");
		return Unirest.get(String.format(GET_ALL_POSTS, baseUrl)).asJson();
	}

	public static HttpResponse<JsonNode> getPostAsJSON(String baseUrl, int postId) {
		LOGGER.debug("Send a GET request to get a specific post");
		return Unirest.get(String.format(GET_POST_BY_ID, baseUrl)).routeParam("postId", Integer.toString(postId))
				.asJson();
	}

	public static HttpResponse<Post> getPost(String baseUrl, int postId) {
		LOGGER.debug("Send a GET request to get a specific post");
		return Unirest.get(String.format(GET_POST_BY_ID, baseUrl)).routeParam("postId", Integer.toString(postId))
				.asObject(Post.class);
	}

	public static HttpResponse<Post> createPost(String baseUrl, Post post) {
		LOGGER.debug("Send a POST request to create a post");
		return Unirest.post(String.format(GET_ALL_POSTS, baseUrl)).header("accept", "application/json")
				.header("Content-Type", "application/json").body(post).asObject(Post.class);
	}

}
