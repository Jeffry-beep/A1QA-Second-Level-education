package step;

import org.openqa.selenium.Cookie;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import framework.utils.CookieUtils;

public class CookieStep {

	public static void goTo(String baseUrl, String username, String password) {
		Logger.getInstance().info("Going to the page with credentials");
		AqualityServices.getBrowser()
				.goTo(baseUrl.replaceAll("http://", String.format("http://%s:%s@", username, password)));
	}

	public static void addCookieAndRefresh(String token) {
		Logger.getInstance().info("Adding a cookie with the token");
		CookieUtils.addCookie(new Cookie("token", token));
		Logger.getInstance().info("Refreshing of current page");
		AqualityServices.getBrowser().refresh();
	}

}
