package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import aquality.selenium.browser.AlertActions;
import aquality.selenium.core.logging.Logger;
import form.JavascriptAlertPage;
import framework.utils.BrowserUtils;

public class ModalWindowTest extends BaseTest {

	private final static String EXPECTED_ALERT_TEXT = "I am a JS Alert";
	private final static String EXPECTED_ALERT_RESULT = "You successfuly clicked an alert";

	private final static String EXPECTED_CONFIRM_TEXT = "I am a JS Confirm";
	private final static String EXPECTED_CONFIRM_RESULT = "You clicked: Ok";

	private final static String EXPECTED_PROMPT_TEXT = "I am a JS prompt";
	private static final String PROMPT_INPUT_TEXT = "Old friends";
	private static final String EXPECTED_PROMPT_RESULT = "You entered: Old friends";

	@Test
	public void test() {
		JavascriptAlertPage javascriptAlertPage = new JavascriptAlertPage();
		assertTrue(javascriptAlertPage.state().waitForDisplayed(), "Failed open the paige");

		Logger.getInstance().info("Try to click on the JS Alert button");
		JavascriptAlertPage alertPage = javascriptAlertPage;
		alertPage.clickJSAlertButton();
		assertEquals(BrowserUtils.switchToAlert().getText(), EXPECTED_ALERT_TEXT,
				"Failed click on the JS Alert button");

		Logger.getInstance().info("Try to click on OK button in the alert");
		browser.handleAlert(AlertActions.ACCEPT);
		assertEquals(alertPage.getResult(), EXPECTED_ALERT_RESULT, "Failed click on OK button");

		Logger.getInstance().info("Try to click on JS Confirm button");
		alertPage.clickJSConfirmButton();
		assertEquals(BrowserUtils.switchToAlert().getText(), EXPECTED_CONFIRM_TEXT,
				"Failed click on the JS Confirm button");

		Logger.getInstance().info("Try to click on OK button in the alert");
		browser.handleAlert(AlertActions.ACCEPT);
		assertEquals(alertPage.getResult(), EXPECTED_CONFIRM_RESULT, "Failed click on OK button");

		Logger.getInstance().info("Try to click on JS PRompt button");
		alertPage.clickJSPromptButton();
		assertEquals(BrowserUtils.switchToAlert().getText(), EXPECTED_PROMPT_TEXT,
				"Failed click on the JS Prompt button");

		Logger.getInstance().info("Try to enter text and then click on OK button");
		browser.handlePromptAlert(AlertActions.ACCEPT, PROMPT_INPUT_TEXT);
		assertEquals(alertPage.getResult(), EXPECTED_PROMPT_RESULT, "Failed enter text and then click on OK button.");
	}

}
