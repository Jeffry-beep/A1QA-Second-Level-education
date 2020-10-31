package utils;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kong.unirest.json.JSONObject;

public class PostUtils {

	private static final Logger LOGGER = LogManager.getLogger(PostUtils.class);

	public static boolean isExpectedPost(JSONObject jsonObject) {
		LOGGER.debug("Check that this post is the expected post using a basic predicate");
		return JSONObjectUtils.isExpectedObject(jsonObject, (e) -> e.getInt("userId") == 10 || e.getInt("id") == 99
				|| !e.getString("title").isBlank() || !e.getString("body").isBlank());
	}

	public static boolean isExpectedPost(JSONObject jsonObject, Predicate<JSONObject> predicate) {
		LOGGER.debug("Check that this post is the expected post using received predicate");
		return JSONObjectUtils.isExpectedObject(jsonObject, predicate);
	}

	public static boolean isExpectedPost(JSONObject expectedObject, JSONObject actualObject,
			BiPredicate<JSONObject, JSONObject> predicate) {
		LOGGER.debug("Check that the expected object matches the actual object using received predicate");
		return JSONObjectUtils.isExpectedObject(expectedObject, actualObject, predicate);
	}

}
