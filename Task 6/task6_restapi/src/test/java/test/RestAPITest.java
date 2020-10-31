package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Objects;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import sender.PostRequestSender;
import sender.UserRequestSender;
import utils.PostArrayUtils;
import utils.PostUtils;
import utils.UserUtils;

public class RestAPITest {

	private static final Logger LOGGER = LogManager.getLogger(RestAPITest.class);
	private static final BiPredicate<JSONObject, JSONObject> SEDING_POST_PREDICATE = (e,
			i) -> e.getString("title").equals(i.getString("title")) && e.getString("body").equals(i.getString("body"))
					&& e.getInt("userId") == (i.getInt("userId")) && Objects.nonNull(i.opt("id"));
	private static final Predicate<JSONObject> FIFTH_USER_PREDICATE = (e) -> e.getInt("id") == 5
			&& e.getString("name").equals("Chelsey Dietrich") && e.getString("username").equals("Kamren")
			&& e.getString("email").equals("Lucio_Hettinger@annie.ca")
			&& e.getJSONObject("address").getString("street").equals("Skiles Walks")
			&& e.getJSONObject("address").getString("suite").equals("Suite 351")
			&& e.getJSONObject("address").getString("city").equals("Roscoeview")
			&& e.getJSONObject("address").getString("zipcode").equals("33263")
			&& e.getJSONObject("address").getJSONObject("geo").getString("lat").equals("-31.8129")
			&& e.getJSONObject("address").getJSONObject("geo").getString("lng").equals("62.5342")
			&& e.getString("phone").equals("(254)954-1289") && e.getString("website").equals("demarco.info")
			&& e.getJSONObject("company").getString("name").equals("Keebler LLC")
			&& e.getJSONObject("company").getString("catchPhrase").equals("User-centric fault-tolerant solution")
			&& e.getJSONObject("company").getString("bs").equals("revolutionize end-to-end systems");

	@Parameters({ "second_step_post", "third_step_post", "sixth_step_user" })
	@Test
	public void test(int secondStepPost, int thirdStepPost, int sixthStepUser) {
		LOGGER.info("Try to send a GET request to get all posts");
		HttpResponse<JsonNode> postsResponse = PostRequestSender.getPosts();
		JSONArray jsonArray = postsResponse.getBody().getArray();
		assertEquals(200, postsResponse.getStatus(), "Actual status doesn't match the expected status");
		assertTrue(PostArrayUtils.isPostArray(jsonArray), "Actual array doesn't match the expected array type");
		assertTrue(PostArrayUtils.isSortedAscendingOrder(jsonArray), "Array is not sorted in ascending order");

		LOGGER.info("Try to send a GET request to get a specific post");
		HttpResponse<JsonNode> postResponse = PostRequestSender.getPost(secondStepPost);
		JSONObject jsonObject = postResponse.getBody().getObject();
		assertEquals(200, postResponse.getStatus(), "Actual status doesn't match the expected status");
		assertTrue(PostUtils.isExpectedPost(jsonObject), "Actual post doesn't match the expected post");

		LOGGER.info("Try to send a GET request to get an empty post");
		HttpResponse<JsonNode> emptyResponse = PostRequestSender.getPost(thirdStepPost);
		assertEquals(404, emptyResponse.getStatus(), "Actual status doesn't match the expected status");
		assertTrue(emptyResponse.getBody().getObject().isEmpty(), "Actual response body isn't empty");

		LOGGER.info("Try to send a POST request to create a post");
		JSONObject sendingPost = new JSONObject().put("title", RandomStringUtils.randomAlphabetic(10))
				.put("body", RandomStringUtils.randomAlphabetic(10)).put("userId", 1);
		HttpResponse<JsonNode> postResponce = PostRequestSender.postPost(sendingPost);
		assertEquals(201, postResponce.getStatus(), "Actual status doesn't match the expected status");
		assertTrue(PostUtils.isExpectedPost(sendingPost, postResponce.getBody().getObject(), SEDING_POST_PREDICATE),
				"Received post doesn't match sended post");

		LOGGER.info("Try to send a GET request to get all users");
		HttpResponse<JsonNode> usersResponse = UserRequestSender.getUsers();
		assertEquals(200, usersResponse.getStatus(), "Actual status doesn't match the expected status");
		assertTrue(usersResponse.getHeaders().get("content-type").get(0).contains("application/json"),
				"Actual content type doesn't match the expected content type");
		assertTrue(UserUtils.isExpectedUser(usersResponse.getBody().getArray().getJSONObject(4), FIFTH_USER_PREDICATE),
				"Actual user doesn't match the expected user");

		LOGGER.info("Try to send a GET request to get a specific user");
		HttpResponse<JsonNode> userResponse = UserRequestSender.getUser(sixthStepUser);
		assertEquals(200, userResponse.getStatus(), "Actual status doesn't match the expected status");
		assertEquals(usersResponse.getBody().getArray().getJSONObject(4), userResponse.getBody().getObject(),
				"Actual user doesn't match the expected user");
	}

}
