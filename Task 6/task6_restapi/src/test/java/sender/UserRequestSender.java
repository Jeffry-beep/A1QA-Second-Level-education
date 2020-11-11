package sender;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entities.User;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class UserRequestSender {

	private static final Logger LOGGER = LogManager.getLogger(UserRequestSender.class);
	private static final String GET_ALL_USERS = "%s/users";
	private static final String GET_USER_BY_ID = "%s/users/{userId}";

	public static HttpResponse<List<User>> getUsers(String baseUrl) {
		LOGGER.debug("Send a GET request to get all users");
		return Unirest.get(String.format(GET_ALL_USERS, baseUrl)).asObject(new GenericType<List<User>>() {
		});
	}

	public static HttpResponse<User> getUser(String baseUrl, int userId) {
		LOGGER.debug("Send a GET request to get a specific user");
		return Unirest.get(String.format(GET_USER_BY_ID, baseUrl)).routeParam("userId", Integer.toString(userId))
				.asObject(User.class);
	}

}
