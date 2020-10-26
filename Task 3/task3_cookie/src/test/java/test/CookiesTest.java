package test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Cookie;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import aquality.selenium.core.logging.Logger;
import form.ExamplePage;
import framework.utils.CookieUtils;
import steps.CookiesSteps;

public class CookiesTest extends BaseTest {

	private static final String NEW_COOKIE_VALUE = "example_value_300";

	@Parameters({ "first_cookie_key", "first_cookie_value", "second_cookie_key", "second_cookie_value",
			"third_cookie_key", "third_cookie_value" })
	@Test
	public void test(String firstCookieKey, String firstCookieValue, String secondCookieKey, String secondCookieValue,
			String thirdCookieKey, String thirdCookieValue) {
		ExamplePage examplePage = new ExamplePage();
		assertTrue(examplePage.state().isDisplayed(), "Failed open the paige");

		Logger.getInstance().info("Try to add cookies");
		CookiesSteps cookiesSteps = new CookiesSteps();
		Cookie[] cookies = { new Cookie(firstCookieKey, firstCookieValue),
				new Cookie(secondCookieKey, secondCookieValue), new Cookie(thirdCookieKey, thirdCookieValue) };
		cookiesSteps.addCookies(cookies);
		assertTrue(cookiesSteps.isAllCookiesAdd(), "Failed add cookies");

		Logger.getInstance().info("Try to delete cookie");
		CookieUtils.deleteCookieNamed(firstCookieKey);
		assertTrue(!CookieUtils.getCookies().contains(new Cookie(firstCookieKey, firstCookieValue)),
				"Failed delete cookie");

		Logger.getInstance().info("Try to update cookie");
		CookieUtils.deleteCookieNamed(thirdCookieKey);
		CookieUtils.addCookie(new Cookie(thirdCookieKey, NEW_COOKIE_VALUE));
		assertTrue(CookieUtils.getCookieNamed(thirdCookieKey).getValue().equals(NEW_COOKIE_VALUE),
				"Failed update cookie");

		Logger.getInstance().info("Try to delete all cookies");
		CookieUtils.deleteAllCookies();
		assertTrue(CookieUtils.getCookies().isEmpty(), "Failed delete all cookies");
	}

}
