package test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import aquality.selenium.core.logging.Logger;
import form.GamePage;
import step.ChooseRandomInterestsStep;
import step.FillLoginFormStep;

public class FillCardsTest extends BaseTest {

	@Parameters({ "password", "email", "domain", "tlds", "interests_number", "upload_file_path" })
	@Test
	public void test(String password, String email, String domain, String tlds, int interestsNumber,
			String uploadFilePath) {
		GamePage gamePage = new GamePage();
		assertTrue(gamePage.state().waitForDisplayed(), "Failed click on the game start link");
		assertTrue(gamePage.isLoginFormDisplayed(), "Failed click on the game start link");

		Logger.getInstance().info("Try to fill login form and click next button");
		FillLoginFormStep loginFormStep = new FillLoginFormStep(gamePage);
		loginFormStep.execute(password, email, domain, tlds);
		assertTrue(gamePage.isAvatarAndInterestsFormDisplayed(), "Failed fill login form and click next button");

		Logger.getInstance()
				.info(String.format("Try to choose %d random interests and load random image", interestsNumber));
		ChooseRandomInterestsStep randomInterestsStep = new ChooseRandomInterestsStep(gamePage);
		randomInterestsStep.execute(interestsNumber, uploadFilePath);
		assertTrue(gamePage.isPersonalDetailsFormDisplayed(), "Failed choose 3 random interests and load random image");
	}

}
