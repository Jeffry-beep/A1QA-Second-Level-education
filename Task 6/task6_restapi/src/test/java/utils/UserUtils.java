package utils;

import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import framework.utils.json.JSONUtils;
import kong.unirest.json.JSONObject;

public class UserUtils {

	private static final Logger LOGGER = LogManager.getLogger(UserUtils.class);

	public static boolean isExpectedUser(JSONObject user, Predicate<JSONObject> predicate) {
		LOGGER.debug("Check that this user is the expected user using received predicate");
		return JSONUtils.isExpectedObject(user, predicate);
	}

}
