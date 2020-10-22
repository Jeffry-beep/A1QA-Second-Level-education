package step;

import java.util.Arrays;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import aquality.selenium.browser.Browser;

public class AddCookiesStep {

	private final Cookie cookie1 = new Cookie("example_key_1", "example_value_1");
	private final Cookie cookie2 = new Cookie("example_key_2", "example_value_2");
	private final Cookie cookie3 = new Cookie("example_key_3", "example_value_3");

	public void addCookies(Browser browser) {
		WebDriver webDriver = browser.getDriver();
		webDriver.manage().addCookie(cookie1);
		webDriver.manage().addCookie(cookie2);
		webDriver.manage().addCookie(cookie3);
	}

	public boolean isAllCookiesAdd(Browser browser) {
		WebDriver webDriver = browser.getDriver();
		Set<Cookie> cookies = webDriver.manage().getCookies();
		return cookies.containsAll(Arrays.asList(cookie1, cookie2, cookie3));
	}

}
