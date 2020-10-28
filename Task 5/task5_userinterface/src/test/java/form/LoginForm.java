package form;

import org.openqa.selenium.By;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class LoginForm extends Form {

	private static final By LOGIN_FORM_LOCATOR = By.cssSelector("div[class='login-form']");
	private static final By TLDS_LIST_LOCATOR = By.cssSelector("div[class='dropdown__list']");
	private static final String TLD_LOCATOR = "//div[@class='dropdown__list-item'][text()[contains(.,'%s')]]";

	private final ITextBox passwordField = super.getElementFactory().getTextBox(
			By.xpath("//input[@class='input input--blue input--gray'] [@placeholder='Choose Password']"),
			"Password field");
	private final ITextBox emailField = super.getElementFactory().getTextBox(
			By.xpath("//input[@class='input input--blue input--gray'] [@placeholder='Your email']"), "Email field");
	private final ITextBox domainField = super.getElementFactory().getTextBox(
			By.xpath("//input[@class='input input--blue input--gray'] [@placeholder='Domain']"), "Domain field");
	private final ITextBox genericTLDsField = super.getElementFactory()
			.getTextBox(By.xpath("//div[@class='dropdown dropdown--gray']"), "Generic TLDs filed");
	private final ICheckBox acceptTermsConditionsCheckBox = super.getElementFactory()
			.getCheckBox(By.cssSelector("span[class='icon icon-check checkbox__check']"), "Accept terms conditions");
	private final IButton nextButton = super.getElementFactory()
			.getButton(By.xpath("//a[@class='button--secondary'][text()[contains(.,'Next')]]"), "Next button");

	public LoginForm() {
		super(LOGIN_FORM_LOCATOR, "Login form");
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
		AqualityServices.getBrowser().getDriver().findElement(TLDS_LIST_LOCATOR)
				.findElement(By.xpath(String.format(TLD_LOCATOR, tlds))).click();
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
