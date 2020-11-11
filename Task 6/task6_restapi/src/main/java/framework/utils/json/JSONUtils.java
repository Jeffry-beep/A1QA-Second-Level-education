package framework.utils.json;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class JSONUtils {

	private static final Logger LOGGER = LogManager.getLogger(JSONUtils.class);
	private static final Gson GSON = new Gson();

	private JSONUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean isJSON(String message) {
		boolean isJson;
		try {
			GSON.fromJson(message, Object.class);
			isJson = true;
		} catch (JsonSyntaxException ex) {
			isJson = false;
		}
		return isJson;
	}

	public static boolean isExpectedObject(JSONObject jsonObject, Predicate<JSONObject> predicate) {
		LOGGER.debug("Check that this JSONObject is the expected JSONObject using the received predicate");
		return predicate.test(jsonObject);
	}

	public static boolean isExpectedObject(JSONObject expectedObject, JSONObject actualObject,
			BiPredicate<JSONObject, JSONObject> predicate) {
		LOGGER.debug("Check that the expected JSONObject is the actual JSONObject using the received predicate");
		return predicate.test(expectedObject, actualObject);
	}

	public static boolean isSortedAscendingOrder(JSONArray jsonArray, BiPredicate<JSONObject, JSONObject> predicate) {
		LOGGER.debug("Check that this JSONArray is sorted in ascending order using the received predicate");
		boolean isSorted = true;
		for (int i = 0; i < jsonArray.length() - 1; i++) {
			if (!predicate.test(jsonArray.getJSONObject(i), jsonArray.getJSONObject(i + 1))) {
				isSorted = false;
				break;
			}
		}
		return isSorted;
	}

	public static boolean isExpectedArray(JSONArray jsonArray, Predicate<JSONObject> predicate) {
		LOGGER.debug("Check that this JSONArray is the expected JSONArray using the received predicate");
		boolean result = true;
		for (int i = 0; i < jsonArray.length() - 1; i++) {
			if (!predicate.test(jsonArray.getJSONObject(i))) {
				result = false;
				break;
			}
		}
		return result;
	}
}
