package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import aquality.selenium.core.logging.Logger;
import form.GamePage;

public class TimerTest extends BaseTest {

	@Parameters({ "expected_timer_time" })
	@Test
	public void test(String expectedTimerTime) {
		GamePage gamePage = new GamePage();
		assertTrue(gamePage.state().waitForDisplayed(), "Failed click on the game start link");
		assertTrue(gamePage.isLoginFormDisplayed(), "Failed click on the game start link");

		Logger.getInstance().info(String.format("Check that a timer time is %s", expectedTimerTime));
		assertEquals(gamePage.getTimerTime(), expectedTimerTime, "The timer time is incorrect.");
	}

}
