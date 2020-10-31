package utils;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class PostArrayUtils {

	private static final Logger LOGGER = LogManager.getLogger(PostArrayUtils.class);

	public static boolean isSortedAscendingOrder(JSONArray jsonArray) {
		LOGGER.debug("Check that this post array is sorted in ascending order using the basic predicate");
		return isSortedAscendingOrder(jsonArray, (e, t) -> e.getInt("userId") < t.getInt("userId"));
	}

	public static boolean isSortedAscendingOrder(JSONArray jsonArray,
			BiPredicate<JSONObject, JSONObject> negativePredicate) {
		LOGGER.debug("Check that this post array is sorted in ascending order using the received predicate");
		return JSONArrayUtils.isSortedAscendingOrder(jsonArray, negativePredicate);
	}

	public static boolean isPostArray(JSONArray jsonArray) {
		LOGGER.debug("Check that this array is a post array using the basic predicate");
		return isPostArray(jsonArray, (e) -> Objects.isNull(e.opt("userId")) || Objects.isNull(e.opt("id"))
				|| Objects.isNull(e.opt("title")) || Objects.isNull(e.opt("body")));
	}

	public static boolean isPostArray(JSONArray jsonArray, Predicate<JSONObject> negativePredicate) {
		LOGGER.debug("Check that this array is a post array using the received predicate");
		return JSONArrayUtils.isExpectedArray(jsonArray, negativePredicate);
	}

}
