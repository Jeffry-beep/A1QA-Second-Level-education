package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.testng.annotations.Test;

import aquality.selenium.core.logging.Logger;
import form.AuthorizationPage;

public class BasicAuthorizationTest extends BaseTest {

	private static final JSONObject EXPECTED_AUTHORIZATION_RESULT = new JSONObject(
			"{\"authenticated\":true,\"user\":\"user\"}");

	@Test
	public void test() {
		AuthorizationPage authorizationPage = new AuthorizationPage();
		assertTrue(authorizationPage.state().waitForDisplayed(), "Successful authorization page didn't open");

		Logger.getInstance().info("Check authorization result");
		JSONObject authorizationResult = new JSONObject(authorizationPage.getAuthorizationResult());
		assertEquals(authorizationResult.toString(), EXPECTED_AUTHORIZATION_RESULT.toString(),
				"Unexpected authorization result");
	}

}
