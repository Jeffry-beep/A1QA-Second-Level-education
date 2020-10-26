package form;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class JavascriptAlertPage extends Form {

	private IButton jsAlertButton = super.getElementFactory().getButton(By.xpath("//button[@onclick='jsAlert()']"),
			"JS Alert button");
	private IButton jsConfirmButton = super.getElementFactory().getButton(By.xpath("//button[@onclick='jsConfirm()']"),
			"JS Confirm button");
	private IButton jsPromptButton = super.getElementFactory().getButton(By.xpath("//button[@onclick='jsPrompt()']"),
			"JS Prompt Button");
	private ITextBox resultBox = super.getElementFactory().getTextBox(By.id("result"), "Result");

	public JavascriptAlertPage() {
		super(By.id("content"), "Javascript alert page");
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

	public String getResult() {
		return resultBox.getText();
	}

}
