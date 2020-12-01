package form;

import org.openqa.selenium.By;

import aquality.selenium.core.logging.Logger;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class WelcomePage extends Form {

	private final ITextBox emailField = super.getElementFactory().getTextBox(By.id("index_email"), "Email field");
	private final ITextBox passwordField = super.getElementFactory().getTextBox(By.id("index_pass"), "Password field");
	private final IButton loginButton = super.getElementFactory().getButton(By.id("index_login_button"),
			"Login button");

	public WelcomePage() {
		super(By.id("index_login"), "VK welcome page");
	}

	public void fillEmail(String email) {
		Logger.getInstance().debug("Clearing and typing email field");
		emailField.clearAndType(email);
	}

	public void fillPassword(String password) {
		Logger.getInstance().debug("Clearing and typing password field");
		passwordField.clearAndType(password);
	}

	public void clickLoginButton() {
		Logger.getInstance().debug("Clicking login button and waiting");
		loginButton.click();
	}

}
