package framework.utils;

import org.openqa.selenium.Alert;

import aquality.selenium.browser.AqualityServices;

public class BrowserUtils {

	private BrowserUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static Alert switchToAlert() {
		return AqualityServices.getBrowser().getDriver().switchTo().alert();
	}

}
