package sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class PostRequestSender {

	private static final Logger LOGGER = LogManager.getLogger(PostRequestSender.class);
	private static final String GET_POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
	private static final String GET_POST_URL = "https://jsonplaceholder.typicode.com/posts/{postId}";
	private static final String POST_POST_URL = "https://jsonplaceholder.typicode.com/posts";

	public static HttpResponse<JsonNode> getPosts() {
		LOGGER.debug("Send a GET request to get all posts");
		return Unirest.get(GET_POSTS_URL).asJson();
	}

	public static HttpResponse<JsonNode> getPost(int postId) {
		LOGGER.debug("Send a GET request to get a specific post");
		return Unirest.get(GET_POST_URL).routeParam("postId", Integer.toString(postId)).asJson();
	}

	public static HttpResponse<JsonNode> postPost(JSONObject post) {
		LOGGER.debug("Send a POST request to create a post");
		return Unirest.post(POST_POST_URL).header("accept", "application/json")
				.header("Content-Type", "application/json").body(post).asJson();
	}

}
