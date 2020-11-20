package form;

import org.openqa.selenium.By;

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
		emailField.clearAndType(email);
	}

	public void fillPassword(String password) {
		passwordField.clearAndType(password);
	}

	public void clickLoginButtonAndWait() {
		loginButton.clickAndWait();
	}

}
