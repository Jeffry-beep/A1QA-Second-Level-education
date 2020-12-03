package utils;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entities.Post;
import framework.utils.json.JSONUtils;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class PostUtils {

	private static final Logger LOGGER = LogManager.getLogger(PostUtils.class);

	public static boolean isExpectedPost(Post post, Predicate<Post> predicate) {
		return predicate.test(post);
	}

	public static boolean isExpectedPost(Post expectedPost, Post actualPost, BiPredicate<Post, Post> predicate) {
		return predicate.test(expectedPost, actualPost);
	}

	public static boolean isExpectedPost(JSONObject jsonObject) {
		LOGGER.debug("Check that this post is the expected post using a basic predicate");
		return JSONUtils.isExpectedObject(jsonObject, (e) -> e.getInt("userId") == 10 || e.getInt("id") == 99
				|| !e.getString("title").isBlank() || !e.getString("body").isBlank());
	}

	public static boolean isExpectedPost(JSONObject jsonObject, Predicate<JSONObject> predicate) {
		LOGGER.debug("Check that this post is the expected post using received predicate");
		return JSONUtils.isExpectedObject(jsonObject, predicate);
	}

	public static boolean isExpectedPost(JSONObject expectedObject, JSONObject actualObject,
			BiPredicate<JSONObject, JSONObject> predicate) {
		LOGGER.debug("Check that the expected object matches the actual object using received predicate");
		return JSONUtils.isExpectedObject(expectedObject, actualObject, predicate);
	}

	public static boolean isSortedAscendingOrder(String postArray) {
		LOGGER.debug("Check that this post array is sorted in ascending order using the basic predicate");
		return isSortedAscendingOrder(new JSONArray(postArray));
	}

	public static boolean isSortedAscendingOrder(JSONArray jsonArray) {
		LOGGER.debug("Check that this post array is sorted in ascending order using the basic predicate");
		return isSortedAscendingOrder(jsonArray, (e, t) -> e.getInt("id") < t.getInt("id"));
	}

	public static boolean isSortedAscendingOrder(JSONArray jsonArray,
			BiPredicate<JSONObject, JSONObject> negativePredicate) {
		LOGGER.debug("Check that this post array is sorted in ascending order using the received predicate");
		return JSONUtils.isSortedAscendingOrder(jsonArray, negativePredicate);
	}

	public static boolean isPostArray(JSONArray jsonArray) {
		LOGGER.debug("Check that this array is a post array using the basic predicate");
		return isPostArray(jsonArray, (e) -> Objects.nonNull(e.opt("userId")) || Objects.nonNull(e.opt("id"))
				|| Objects.nonNull(e.opt("title")) || Objects.nonNull(e.opt("body")));
	}

	public static boolean isPostArray(JSONArray jsonArray, Predicate<JSONObject> predicate) {
		LOGGER.debug("Check that this array is a post array using the received predicate");
		return JSONUtils.isExpectedArray(jsonArray, predicate);
	}

}
