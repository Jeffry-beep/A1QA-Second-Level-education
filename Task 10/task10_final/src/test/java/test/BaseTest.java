package test;

import org.apache.commons.configuration2.Configuration;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import framework.loader.PropertiesConfigurationLoader;
import framework.testrail.client.TestRailAPIClient;
import framework.testrail.client.settings.APIClientSettings;
import framework.utils.ScreenshotUtils;
import framework.utils.TestRailUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public abstract class BaseTest {

	public static final Configuration SUITE_CONFIGURATION = PropertiesConfigurationLoader
			.getConfiguration("src/test/resources/config.properties");
	public static final Browser BROWSER = AqualityServices.getBrowser();
	public static final TestRailAPIClient TEST_RAIL_API_CLIENT = new TestRailAPIClient(new APIClientSettings(
			SUITE_CONFIGURATION.getString("testrail_url"), SUITE_CONFIGURATION.getString("testrail_username"),
			SUITE_CONFIGURATION.getString("testrail_password"), SUITE_CONFIGURATION.getInt("testrail_api_version")));

	@BeforeClass
	public void beforeClass() {
		BROWSER.maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) {
		String screenshotPath = ScreenshotUtils.makeScreenshot(SUITE_CONFIGURATION.getString("screenshots_path"));
		TEST_RAIL_API_CLIENT.logIn();
		HttpResponse<JsonNode> response = TEST_RAIL_API_CLIENT.addResult(TestRailUtils.getResult(
				TestRailUtils.getTestResultStatus(result), TestRailUtils.getComment(result),
				SUITE_CONFIGURATION.getInt("testrail_user_id"), SUITE_CONFIGURATION.getInt("testrail_test_id")));
		TEST_RAIL_API_CLIENT.addAttachmentToResult(response.getBody().getObject().getInt("id"), screenshotPath);
	}

	@AfterClass
	public void afterClass() {
		BROWSER.quit();
	}

}
