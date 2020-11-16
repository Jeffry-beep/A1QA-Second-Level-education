package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.configuration2.Configuration;
import org.testng.annotations.AfterClass;
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

	@AfterClass
	public void afterClass() {
		makeScreenshot();
		apiClient.logIn();
		HttpResponse<JsonNode> response = apiClient.addResult(new Result(ResultStatus.PASSED, 2311, 58154422,
				System.currentTimeMillis(), 2311, "Passed successfully"));
		apiClient.addAttachmentToResult(response.getBody().getObject().getInt("id"),
				TEST_CONFIGURATION.getString("attachment_path"));
		browser.quit();
	}

	private void makeScreenshot() {
		try {
			Files.write(Paths.get("B:\\Attachment.jpg"), AqualityServices.getBrowser().getScreenshot());
		} catch (IOException e) {
			Logger.getInstance().warn("Can't create file");
		}
	}

}
