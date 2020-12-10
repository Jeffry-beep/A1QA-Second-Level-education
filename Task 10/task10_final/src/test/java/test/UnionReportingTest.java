package test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static matcher.IsSortedDescendingByDate.sortedDescendingByDate;

import java.util.List;
import java.util.Objects;

import org.openqa.selenium.Cookie;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import aquality.selenium.browser.AlertActions;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.BrowserName;
import aquality.selenium.core.logging.Logger;
import form.ProjectPage;
import form.ProjectsPage;
import form.TestPage;
import framework.db.DBRequestSender;
import framework.unionreporting.client.UnionReportingAPIClient;
import framework.utils.AlertUtils;
import framework.utils.CookieUtils;
import framework.utils.FileUtils;
import framework.utils.ScreenshotUtils;
import step.CookieStep;
import step.DBStep;
import step.SaveProjectStep;

public class UnionReportingTest extends BaseTest {

	private String savingProjectName;

	@Parameters({ "variant_number", "project_name", "saving_project", "adding_test", "adding_test_method",
			"adding_test_env", "adding_test_log" })
	@Test
	public void test(int variantNumber, String projectName, String savingProject, String addingTest,
			String addingTestMethod, String addingTestEnv, String addingTestLog) {
		savingProjectName = savingProject;
		String apiUrl = SUITE_CONFIGURATION.getString("app_api_url");
		UnionReportingAPIClient unionApiClient = new UnionReportingAPIClient(apiUrl);
		Logger.getInstance().info("Getting a token");
		String token = unionApiClient.getToken(variantNumber);
		assertTrue(Objects.nonNull(token) && !token.isBlank(), "Failed to get a token. The token is null or empty");

		CookieStep.goTo(SUITE_CONFIGURATION.getString("app_url"), SUITE_CONFIGURATION.getString("app_username"),
				SUITE_CONFIGURATION.getString("app_password"));
		ProjectsPage projectsPage = new ProjectsPage();
		assertTrue(projectsPage.state().waitForDisplayed(), "Failed to go the projects page");
		CookieUtils.addCookie(new Cookie("token", token));
		AqualityServices.getBrowser().refresh();
		Logger.getInstance().info("Getting the version");
		int version = Integer.valueOf(projectsPage.getVersion());
		assertEquals(version, variantNumber, "The version isn't equal to the variant number");

		projectsPage.clickProjectButton(projectName);
		ProjectPage projectPage = new ProjectPage();
		assertTrue(projectPage.state().waitForDisplayed(), "Failed to go the project page");
		assertEquals(projectPage.getProjectName(), projectName,
				"The actual project page (name project) is not equal to the expected page");
		List<entity.Test> dbTests = DBStep.getLastStartedProjectTests(projectName, 20);
		List<entity.Test> pageTests = projectPage.getTests();
		assertThat(pageTests, is(sortedDescendingByDate()));
		assertEquals(dbTests, pageTests, "Tests received from the page isn't equal to tests received from a database");

		BROWSER.goBack();
		assertTrue(projectsPage.state().waitForDisplayed(), "Failed to return the projects page");
		SaveProjectStep.saveProject(savingProject);
		assertTrue(AlertUtils.isAlertDisplayed(), "Alert isn't displayed");
		assertEquals(AlertUtils.getAlertText(), String.format("Project %s saved", savingProject));
		BROWSER.handleAlert(AlertActions.ACCEPT);
		BROWSER.executeScript("closePopUp()");
		assertTrue(projectsPage.waitAddProjectWindowForNotDisplayed(), "Add project window is displayed");
		if (BROWSER.getBrowserName().equals(BrowserName.FIREFOX)) {
			BROWSER.refreshPageWithAlert(AlertActions.ACCEPT);
			projectsPage.waitAddProjectWindowForDisplayed();
			BROWSER.executeScript("closePopUp()");
		} else {
			BROWSER.refresh();
		}
		assertTrue(projectsPage.isProjectDisplayed(savingProject), "Saved project isn't diplsayed in projects list");

		projectsPage.clickProjectButton(savingProject);
		String testId = unionApiClient.addTest(apiUrl, savingProject, addingTest, addingTestMethod, addingTestEnv);
		unionApiClient.addTestLog(apiUrl, testId,
				FileUtils.getContentAsList(SUITE_CONFIGURATION.getString("adding_logs_path"), addingTestLog));
		String screenshotPath = ScreenshotUtils.makeScreenshot(SUITE_CONFIGURATION.getString("screenshots_path"));
		unionApiClient.addTestImgAttachment(apiUrl, testId, screenshotPath);
		projectPage.waitTestForDisplayed(addingTest);
		assertThat(projectPage.getTest(addingTest),
				allOf(hasProperty("name", equalTo(addingTest)), hasProperty("methodName", equalTo(addingTestMethod))));

		projectPage.clickTestNameButton(addingTest);
		TestPage testPage = new TestPage();
		assertTrue(testPage.state().waitForDisplayed(), "Test page isn't displayed");
		assertThat(testPage.getTest(), allOf(hasProperty("name", equalTo(addingTest)),
				hasProperty("methodName", equalTo(addingTestMethod)), hasProperty("env", equalTo(addingTestEnv))));
		assertEquals(testPage.getDecodedImageContent(), FileUtils.getContentAsArray(screenshotPath),
				"Actual image isn't equal to expected (uploaded) image");
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		DBRequestSender dbSender = new DBRequestSender();
		dbSender.delete(String.format("DELETE FROM project WHERE (name = '%s')", savingProjectName));
	}
}
