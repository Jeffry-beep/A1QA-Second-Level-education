package form;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class AuthorizationPage extends Form {

	private static final By PAGE_LOCATOR = By.cssSelector("pre");
	private static final String PAGE_NAME = "Authorization page";
	private static final String AUTHORIZATION_RESULT_NAME = "Authorization result";

	private final ITextBox authorizationResult = super.getElementFactory().getTextBox(PAGE_LOCATOR,
			AUTHORIZATION_RESULT_NAME);

	public AuthorizationPage() {
		super(PAGE_LOCATOR, PAGE_NAME);
	}

	public String getAuthorizationResult() {
		return authorizationResult.getText();
	}

}
