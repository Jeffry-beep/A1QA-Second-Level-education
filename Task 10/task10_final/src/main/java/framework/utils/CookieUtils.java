package framework.utils;

import java.util.Set;

import org.openqa.selenium.Cookie;

import aquality.selenium.browser.AqualityServices;

public class CookieUtils {

	private CookieUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static void addCookie(Cookie cookie) {
		AqualityServices.getBrowser().getDriver().manage().addCookie(cookie);
	}

	public static Cookie getCookieNamed(String cookieName) {
		return AqualityServices.getBrowser().getDriver().manage().getCookieNamed(cookieName);
	}

	public static Set<Cookie> getCookies() {
		return AqualityServices.getBrowser().getDriver().manage().getCookies();
	}

	public static void deleteCookieNamed(String cookieName) {
		AqualityServices.getBrowser().getDriver().manage().deleteCookieNamed(cookieName);
	}

	public static void deleteAllCookies() {
		AqualityServices.getBrowser().getDriver().manage().deleteAllCookies();
	}
}
