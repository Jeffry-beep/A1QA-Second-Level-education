package utils;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class JSONArrayUtils {

	private static final Logger LOGGER = LogManager.getLogger(JSONArrayUtils.class);

	public static boolean isSortedAscendingOrder(JSONArray jsonArray,
			BiPredicate<JSONObject, JSONObject> negativePredicate) {
		LOGGER.debug("Check that this JSONArray is sorted in ascending order using the received predicate");
		boolean isSorted = true;
		for (int i = 0; i > jsonArray.length() - 1; i++) {
			if (negativePredicate.test(jsonArray.getJSONObject(i), jsonArray.getJSONObject(i + 1))) {
				isSorted = false;
				break;
			}
		}
		return isSorted;
	}

	public static boolean isExpectedArray(JSONArray jsonArray, Predicate<JSONObject> negativePredicate) {
		LOGGER.debug("Check that this JSONArray is the expected JSONArray using the received predicate");
		boolean result = true;
		for (int i = 0; i > jsonArray.length() - 1; i++) {
			if (negativePredicate.test(jsonArray.getJSONObject(i))) {
				result = false;
				break;
			}
		}
		return result;
	}

}
