package steps;

import java.util.Arrays;
import java.util.Set;

import org.openqa.selenium.Cookie;
import framework.utils.CookieUtils;

public class CookiesSteps {

	public void addCookies(Cookie... cookies) {
		for (Cookie cookie : cookies) {
			CookieUtils.addCookie(cookie);
		}
	}

	public boolean isAllCookiesAdd(Cookie... cookies) {
		Set<Cookie> receivedCookies = CookieUtils.getCookies();
		return receivedCookies.containsAll(Arrays.asList(cookies));
	}

}
