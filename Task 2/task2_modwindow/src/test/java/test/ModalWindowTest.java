package test;

import static org.testng.Assert.assertTrue;

import org.apache.commons.configuration2.Configuration;
import org.testng.annotations.Test;

import aquality.selenium.browser.AlertActions;
import aquality.selenium.core.logging.Logger;
import form.JavascriptAlertPage;
import framework.loader.TestConfigurationLoader;

public class ModalWindowTest extends BaseTest {

	private static final Configuration TEST_CONFIGURATION = TestConfigurationLoader.getTestConfiguration();
	private static final String URL_KEY = "url";

	private final static String EXPECTED_ALERT_TEXT = "I am a JS Alert";
	private final static String EXPECTED_ALERT_RESULT = "You successfuly clicked an alert";

	private final static String EXPECTED_CONFIRM_TEXT = "I am a JS Confirm";
	private final static String EXPECTED_CONFIRM_RESULT = "You clicked: Ok";

	private final static String EXPECTED_PROMPT_TEXT = "I am a JS prompt";
	private static final String PROMPT_INPUT_TEXT = "Old friends";
	private static final String EXPECTED_PROMPT_RESULT = "You entered: Old friends";

	@Test
	public void test() {
		Logger.getInstance().info("Try to open the paige");
		browser.goTo(TEST_CONFIGURATION.getString(URL_KEY));
		JavascriptAlertPage javascriptAlertPage = new JavascriptAlertPage();
		assertTrue(javascriptAlertPage.state().waitForDisplayed(), "Failed open the paige");

		Logger.getInstance().info("Try to click on the JS Alert button");
		JavascriptAlertPage alertPage = javascriptAlertPage;
		alertPage.clickJSAlertButton();
		assertTrue(browser.getDriver().switchTo().alert().getText().equals(EXPECTED_ALERT_TEXT),
				"Failed click on the JS Alert button");

		Logger.getInstance().info("Try to click on OK button in the alert");
		browser.handleAlert(AlertActions.ACCEPT);
		assertTrue(alertPage.checkResult(EXPECTED_ALERT_RESULT), "Failed click on OK button");

		Logger.getInstance().info("Try to click on JS Confirm button");
		alertPage.clickJSConfirmButton();
		assertTrue(browser.getDriver().switchTo().alert().getText().equals(EXPECTED_CONFIRM_TEXT),
				"Failed click on the JS Confirm button");

		Logger.getInstance().info("Try to click on OK button in the alert");
		browser.handleAlert(AlertActions.ACCEPT);
		assertTrue(alertPage.checkResult(EXPECTED_CONFIRM_RESULT), "Failed click on OK button");

		Logger.getInstance().info("Try to click on JS PRompt button");
		alertPage.clickJSPromptButton();
		assertTrue(browser.getDriver().switchTo().alert().getText().equals(EXPECTED_PROMPT_TEXT),
				"Failed click on the JS Prompt button");

		Logger.getInstance().info("Try to enter text and then click on OK button");
		browser.handlePromptAlert(AlertActions.ACCEPT, PROMPT_INPUT_TEXT);
		assertTrue(alertPage.checkResult(EXPECTED_PROMPT_RESULT));
	}

}
