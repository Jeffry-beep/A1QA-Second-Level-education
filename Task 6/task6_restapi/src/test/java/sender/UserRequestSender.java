package sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class UserRequestSender {

	private static final Logger LOGGER = LogManager.getLogger(UserRequestSender.class);
	private static final String GET_USERS_URL = "https://jsonplaceholder.typicode.com/users";
	private static final String GET_USER_URL = "https://jsonplaceholder.typicode.com/users/{userId}";

	public static HttpResponse<JsonNode> getUsers() {
		LOGGER.debug("Send a GET request to get all users");
		return Unirest.get(GET_USERS_URL).asJson();
	}

	public static HttpResponse<JsonNode> getUser(int userId) {
		LOGGER.debug("Send a GET request to get a specific user");
		return Unirest.get(GET_USER_URL).routeParam("userId", Integer.toString(userId)).asJson();
	}

}
