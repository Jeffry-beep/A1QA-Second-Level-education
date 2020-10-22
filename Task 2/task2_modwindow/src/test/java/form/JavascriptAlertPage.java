package form;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;

public class JavascriptAlertPage extends Form {

	private IButton jsAlertButton = super.getElementFactory().getButton(By.xpath("//button[@onclick='jsAlert()']"),
			"JS Alert button");
	private IButton jsConfirmButton = super.getElementFactory().getButton(By.xpath("//button[@onclick='jsConfirm()']"),
			"JS Confirm button");
	private IButton jsPromptButton = super.getElementFactory().getButton(By.xpath("//button[@onclick='jsPrompt()']"),
			"JS Prompt Button");

	public JavascriptAlertPage() {
		this(By.id("content"), "Javascript alert page");
	}

	protected JavascriptAlertPage(By locator, String name) {
		super(locator, name);
	}

	public void clickJSAlertButton() {
		jsAlertButton.click();
	}

	public void clickJSConfirmButton() {
		jsConfirmButton.click();
	}

	public void clickJSPromptButton() {
		jsPromptButton.click();
	}

	public boolean checkResult(String expectedResult) {
		return super.getElementFactory().getTextBox(By.id("result"), "Result").getText().equals(expectedResult);
	}

}
