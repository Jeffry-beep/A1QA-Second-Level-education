package step;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;

public class CookieStep {

	public static void goTo(String baseUrl, String username, String password) {
		Logger.getInstance().info("Going to the page with credentials");
		AqualityServices.getBrowser()
				.goTo(baseUrl.replaceAll("http://", String.format("http://%s:%s@", username, password)));
	}

}
