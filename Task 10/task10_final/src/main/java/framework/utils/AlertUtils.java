package framework.utils;

import org.openqa.selenium.support.ui.ExpectedConditions;

import aquality.selenium.browser.AqualityServices;

public class AlertUtils {

	private AlertUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static String getAlertText() {
		return AqualityServices.getBrowser().getDriver().switchTo().alert().getText();
	}

	public static boolean isAlertDisplayed() {
		AqualityServices.getConditionalWait().waitFor(ExpectedConditions.alertIsPresent());
		return true;
	}

}
