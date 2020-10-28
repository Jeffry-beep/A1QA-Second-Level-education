package test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import aquality.selenium.core.logging.Logger;
import form.GamePage;

public class FillCardsTest extends BaseTest {

	private static final String PASSWORD_KEY = "password";
	private static final String EMAIL_KEY = "email";
	private static final String DOMAIN_KEY = "domain";
	private static final String TLDS_KEY = "tlds";
	private static final String UPLOAD_FILE_PATH_KEY = "upload_file_path";

	@Parameters({ "interests_number" })
	@Test
	public void test(int interestsNumber) {
		GamePage gamePage = new GamePage();
		assertTrue(gamePage.state().waitForDisplayed(), "Failed click on the game start link");
		assertTrue(gamePage.isLoginFormDisplayed(), "Failed click on the game start link");

		Logger.getInstance().info("Try to fill login form and click next button");
		gamePage.setPassword(super.suiteConfiguration.getString(PASSWORD_KEY));
		gamePage.setEmail(super.suiteConfiguration.getString(EMAIL_KEY));
		gamePage.setDomain(super.suiteConfiguration.getString(DOMAIN_KEY));
		gamePage.setGenericTLDs(super.suiteConfiguration.getString(TLDS_KEY));
		gamePage.acceptTermsConditions();
		gamePage.clickNextButton();
		assertTrue(gamePage.isAvatarAndInterestsFormDisplayed(), "Failed fill login form and click next button");

		Logger.getInstance()
				.info(String.format("Try to choose %d random interests and load random image", interestsNumber));
		gamePage.initializeAvatarAndInterestsFormElements();
		gamePage.chooseRandomInterests(interestsNumber);
		gamePage.clickUploadImageLink();
		gamePage.uploadImage(super.suiteConfiguration.getString(UPLOAD_FILE_PATH_KEY));
		gamePage.clickAvatarNextButton();
		assertTrue(gamePage.isPersonalDetailsFormDisplayed(), "Failed choose 3 random interests and load random image");
	}

}
