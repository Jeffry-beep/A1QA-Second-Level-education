package form;

import org.openqa.selenium.By;

import aquality.selenium.forms.Form;

public class SuccessfulBasicAuthorizationPage extends Form {

	private static final By AUTHORIZATION_RESULT_LOCATOR = By
			.cssSelector("pre[style='word-wrap: break-word; white-space: pre-wrap;']");
	private static final String AUTHORIZATION_RESULT_KEY = "Authorization result";

	public SuccessfulBasicAuthorizationPage() {
		super(AUTHORIZATION_RESULT_LOCATOR, AUTHORIZATION_RESULT_KEY);
	}

	public String getAuthorizationResult() {
		return super.getElementFactory().getTextBox(AUTHORIZATION_RESULT_LOCATOR, AUTHORIZATION_RESULT_KEY).getText();
	}

}
