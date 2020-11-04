package form;

import org.openqa.selenium.By;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class LoginForm extends Form {

	private static final String LOGIN_FORM_LOCATOR = "div[class='login-form']";
	private static final String TLD_LOCATOR = "//div[@class='dropdown__list-item'][text()[contains(.,'%s')]]";

	private final ITextBox passwordField = super.getElementFactory().getTextBox(By.xpath(
			"//input[@class[contains(., 'input') and contains(., 'input--blue') and contains(., 'input--gray')] and @placeholder='Choose Password']"),
			"Password field");
	private final ITextBox emailField = super.getElementFactory().getTextBox(By.xpath(
			"//input[@class[contains(., 'input') and contains(., 'input--blue') and contains(., 'input--gray')] and @placeholder='Your email']"),
			"Email field");
	private final ITextBox domainField = super.getElementFactory().getTextBox(By.xpath(
			"//input[@class[contains(., 'input') and contains(., 'input--blue') and contains(., 'input--gray')] and @placeholder='Domain']"),
			"Domain field");
	private final ITextBox genericTLDsField = super.getElementFactory().getTextBox(
			By.xpath("//div[@class[contains(., 'dropdown') and contains(., 'dropdown--gray')]]"), "Generic TLDs filed");
	private final ITextBox tldsList = super.getElementFactory()
			.getTextBox(By.cssSelector("div[class='dropdown__list']"), "TLDs list");
	private final ICheckBox acceptTermsConditionsCheckBox = super.getElementFactory()
			.getCheckBox(By.cssSelector("span[class='icon icon-check checkbox__check']"), "Accept terms conditions");
	private final IButton nextButton = super.getElementFactory()
			.getButton(By.xpath("//a[@class='button--secondary' and text()[contains(.,'Next')]]"), "Next button");

	public LoginForm() {
		super(By.cssSelector(LOGIN_FORM_LOCATOR), "Login form");
	}

	public void setPassword(String password) {
		passwordField.clearAndType(password);
	}

	public void setEmail(String email) {
		emailField.clearAndType(email);
	}

	public void setDomain(String domain) {
		domainField.clearAndType(domain);
	}

	public void setGenericTLDs(String tlds) {
		genericTLDsField.click();
		tldsList.findChildElement(By.xpath(String.format(TLD_LOCATOR, tlds)), "TLD box", ElementType.TEXTBOX).click();
	}

	public void acceptTermsConditions() {
		if (!acceptTermsConditionsCheckBox.isChecked()) {
			acceptTermsConditionsCheckBox.check();
		}
	}

	public void clickNextButton() {
		nextButton.click();
	}
}
