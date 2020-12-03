package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import framework.loader.PropertiesConfigurationLoader;
import framework.testrail.client.APIClient;
import framework.testrail.client.settings.APIClientSettings;
import framework.testrail.entities.Result;
import framework.testrail.entities.ResultStatus;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public abstract class BaseTest {

	private static final Configuration TEST_CONFIGURATION = PropertiesConfigurationLoader
			.getConfiguration("src/test/resources/config.properties");
	private static final String URL_KEY = "url";

	protected Browser browser;
	protected APIClient apiClient;

	@BeforeClass
	public void beforeClass() {
		setupBrowser();
		setupClient();
	}

	private void setupBrowser() {
		browser = AqualityServices.getBrowser();
		browser.maximize();
		Logger.getInstance().info("Try to open the paige");
		browser.goTo(TEST_CONFIGURATION.getString(URL_KEY));
	}

	private void setupClient() {
		apiClient = new APIClient(new APIClientSettings(TEST_CONFIGURATION.getString("testrail_url"),
				TEST_CONFIGURATION.getString("testrail_username"), TEST_CONFIGURATION.getString("testrail_password")));
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		String screenshotPath = makeScreenshot();
		apiClient.logIn();
		HttpResponse<JsonNode> response = apiClient
				.addResult(getResult(getTestResultStatus(result), getComment(result)));
		apiClient.addAttachmentToResult(response.getBody().getObject().getInt("id"), screenshotPath);
	}

	private String makeScreenshot() {
		String screenshotPath = null;
		try {
			screenshotPath = String.format("%s%s%s", TEST_CONFIGURATION.getString("attachment_path"),
					RandomStringUtils.randomAlphanumeric(10), ".jpg");
			Files.write(Paths.get(screenshotPath), AqualityServices.getBrowser().getScreenshot());
		} catch (IOException e) {
			Logger.getInstance().warn("Can't create file");
		}
		return screenshotPath;
	}

	private Result getResult(ResultStatus resultStatus, String comment) {
		return new Result(resultStatus, TEST_CONFIGURATION.getInt("testrail_user_id"),
				TEST_CONFIGURATION.getInt("testrail_test_id"), System.currentTimeMillis(),
				TEST_CONFIGURATION.getInt("testrail_user_id"), comment);
	}

	private ResultStatus getTestResultStatus(ITestResult result) {
		return result.getStatus() == ITestResult.SUCCESS ? ResultStatus.PASSED
				: result.getStatus() == ITestResult.FAILURE ? ResultStatus.FAILED : ResultStatus.BLOCKED;
	}

	private String getComment(ITestResult result) {
		return result.getStatus() == ITestResult.SUCCESS ? "Passed successfully"
				: result.getStatus() == ITestResult.FAILURE ? "Test failed" : "Test was blocked";
	}

	@AfterClass
	public void afterClass() {
		browser.quit();
	}

}
