package form;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class WelcomePage extends Form {

	private static final Logger LOGGER = LogManager.getLogger(WelcomePage.class);

	private final ITextBox emailField = super.getElementFactory().getTextBox(By.id("index_email"), "Email field");
	private final ITextBox passwordField = super.getElementFactory().getTextBox(By.id("index_pass"), "Password field");
	private final IButton loginButton = super.getElementFactory().getButton(By.id("index_login_button"),
			"Login button");

	public WelcomePage() {
		super(By.id("index_login"), "VK welcome page");
	}

	public void fillEmail(String email) {
		LOGGER.debug("Clearing and typing email field");
		emailField.clearAndType(email);
	}

	public void fillPassword(String password) {
		LOGGER.debug("Clearing and typing password field");
		passwordField.clearAndType(password);
	}

	public void clickLoginButtonAndWait() {
		LOGGER.debug("Clicking login button and waiting");
		loginButton.clickAndWait();
	}

}
