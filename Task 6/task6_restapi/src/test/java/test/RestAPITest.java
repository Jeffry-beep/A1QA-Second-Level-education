package test;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.hasProperty;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import entities.Address;
import entities.Company;
import entities.Geo;
import entities.Post;
import entities.User;
import framework.loader.PropertiesConfigurationLoader;
import framework.utils.json.JSONUtils;
import kong.unirest.HttpResponse;
import kong.unirest.HttpStatus;
import kong.unirest.JsonNode;
import sender.PostRequestSender;
import sender.UserRequestSender;
import utils.PostUtils;

public class RestAPITest {

	private static final Logger LOGGER = LogManager.getLogger(RestAPITest.class);
	private static final Configuration TEST_CONFIGURATION = PropertiesConfigurationLoader
			.getConfiguration("src/test/resources/config.properties");
	private static final User EXPECTED_USER = new User("Chelsey Dietrich", "Kamren", "Lucio_Hettinger@annie.ca",
			new Address("Skiles Walks", "Suite 351", "Roscoeview", "33263", new Geo(-31.8129, 62.5342)),
			"(254)954-1289",
			new Company("Keebler LLC", "User-centric fault-tolerant solution", "revolutionize end-to-end systems"));

	@Parameters({ "second_step_userId", "second_step_id", "third_step_post", "sixth_step_user" })
	@Test
	public void test(int secondStepUserId, int secondStepId, int thirdStepPost, int sixthStepUser) {
		String baseUrl = TEST_CONFIGURATION.getString("url");

		LOGGER.info("Try to send a GET request to get all posts");
		HttpResponse<String> postsResponse = PostRequestSender.getPostsAsString(baseUrl);
		assertEquals(HttpStatus.OK, postsResponse.getStatus(), "Actual status doesn't match the expected status");
		assertTrue(JSONUtils.isJSON(postsResponse.getBody()), "Actual array doesn't match the expected array type");
		assertTrue(PostUtils.isSortedAscendingOrder(postsResponse.getBody()), "Array is not sorted in ascending order");

		LOGGER.info("Try to send a GET request to get a specific post");
		HttpResponse<Post> postResponse = PostRequestSender.getPost(baseUrl, secondStepId);
		Post receivedPost = postResponse.getBody();
		assertEquals(HttpStatus.OK, postResponse.getStatus(), "Actual status doesn't match the expected status");
		assertThat(receivedPost,
				allOf(hasProperty("title", not(emptyOrNullString())), hasProperty("body", not(emptyOrNullString())),
						hasProperty("id", equalTo(secondStepId)), hasProperty("userId", equalTo(secondStepUserId))));

		LOGGER.info("Try to send a GET request to get an empty post");
		HttpResponse<JsonNode> emptyResponse = PostRequestSender.getPostAsJSON(baseUrl, thirdStepPost);
		assertEquals(HttpStatus.NOT_FOUND, emptyResponse.getStatus(),
				"Actual status doesn't match the expected status");
		assertTrue(emptyResponse.getBody().getObject().isEmpty(), "Actual response body isn't empty");

		LOGGER.info("Try to send a POST request to create a post");
		Post sendingPost = new Post(1, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
		HttpResponse<Post> postResponce = PostRequestSender.createPost(baseUrl, sendingPost);
		assertEquals(HttpStatus.CREATED, postResponce.getStatus(), "Actual status doesn't match the expected status");
		assertThat(postResponce.getBody(), sameBeanAs(sendingPost).ignoring("id"));

		LOGGER.info("Try to send a GET request to get all users");
		HttpResponse<List<User>> usersResponse = UserRequestSender.getUsers(baseUrl);
		assertEquals(HttpStatus.OK, usersResponse.getStatus(), "Actual status doesn't match the expected status");
		assertTrue(usersResponse.getHeaders().get("content-type").get(0).contains("application/json"),
				"Actual content type doesn't match the expected content type");
		assertEquals(usersResponse.getBody().get(4), EXPECTED_USER, "Actual user doesn't match the expected user");

		LOGGER.info("Try to send a GET request to get a specific user");
		HttpResponse<User> userResponse = UserRequestSender.getUser(baseUrl, sixthStepUser);
		assertEquals(HttpStatus.OK, userResponse.getStatus(), "Actual status doesn't match the expected status");
		assertEquals(usersResponse.getBody().get(4), userResponse.getBody(),
				"Actual user doesn't match the expected user");
	}

}
