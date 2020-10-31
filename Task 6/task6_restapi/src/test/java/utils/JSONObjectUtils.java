package utils;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kong.unirest.json.JSONObject;

public class JSONObjectUtils {

	private static final Logger LOGGER = LogManager.getLogger(JSONObjectUtils.class);

	public static boolean isExpectedObject(JSONObject jsonObject, Predicate<JSONObject> predicate) {
		LOGGER.debug("Check that this JSONObject is the expected JSONObject using the received predicate");
		return predicate.test(jsonObject);
	}

	public static boolean isExpectedObject(JSONObject expectedObject, JSONObject actualObject,
			BiPredicate<JSONObject, JSONObject> predicate) {
		LOGGER.debug("Check that the expected JSONObject is the actual JSONObject using the received predicate");
		return predicate.test(expectedObject, actualObject);
	}

}
