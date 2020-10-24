package form;

import org.apache.commons.configuration2.Configuration;
import org.openqa.selenium.By;

import aquality.selenium.browser.Browser;

import aquality.selenium.forms.Form;

public class BasicAuthorizationPage extends Form {

	private static final String URL_SPLITERATOR = "//";
	private static final String LOGIN_URL_FORMAT = "%s//%s:%s@%s";
	private static final String USERNAME_KEY = "username";
	private static final String PASSWORD_KEY = "password";

	public BasicAuthorizationPage(By locator, String name) {
		super(locator, name);
	}

	public static void logIn(String url, Browser browser, Configuration testConfiguration) {
		String[] urlElements = url.split(URL_SPLITERATOR);
		browser.goTo(String.format(LOGIN_URL_FORMAT, urlElements[0], testConfiguration.getString(USERNAME_KEY),
				testConfiguration.getString(PASSWORD_KEY), urlElements[1]));
	}

}
