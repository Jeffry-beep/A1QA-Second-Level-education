package test;

import static org.testng.Assert.assertTrue;

import org.apache.commons.configuration2.Configuration;
import org.json.JSONObject;
import org.testng.annotations.Test;

import aquality.selenium.core.logging.Logger;
import form.BasicAuthorizationPage;
import form.SuccessfulBasicAuthorizationPage;
import framework.loader.TestConfigurationLoader;

public class BasicAuthorizationTest extends BaseTest {

	private static final Configuration TEST_CONFIGURATION = TestConfigurationLoader.getTestConfiguration();
	private static final String URL_KEY = "url";
	private static final JSONObject EXPECTED_AUTHORIZATION_RESULT = new JSONObject(
			"{\"authenticated\":true,\"user\":\"user\"}");

	@Test
	public void test() {
		Logger.getInstance().info("Try to open the Basic Authorization page and log in");
		BasicAuthorizationPage.logIn(TEST_CONFIGURATION.getString(URL_KEY), browser, TEST_CONFIGURATION);
		SuccessfulBasicAuthorizationPage authorizationPage = new SuccessfulBasicAuthorizationPage();
		assertTrue(authorizationPage.state().waitForDisplayed(), "Successful authorization page didn't open");

		Logger.getInstance().info("Check authorization result");
		JSONObject authorizationResult = new JSONObject(authorizationPage.getAuthorizationResult());
		assertTrue(authorizationResult.toString().equals(EXPECTED_AUTHORIZATION_RESULT.toString()),
				"Unexpected authorization result");
	}

}
