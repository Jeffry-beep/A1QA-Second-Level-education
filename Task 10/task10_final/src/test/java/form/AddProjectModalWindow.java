package form;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class AddProjectModalWindow extends Form {

	private final ITextBox PROJECT_NAME_FIELD = super.getElementFactory().getTextBox(By.id("projectName"),
			"Enter project name field");
	private final IButton SAVE_PROJECT_BUTTON = super.getElementFactory()
			.getButton(By.cssSelector("button[class*='btn-primary'][type='submit']"), "Save project button");
	private final IButton CANCEL_SAVE_PROJECT_BUTTON = super.getElementFactory()
			.getButton(By.cssSelector("button[class=*'btn-default']"), "Cancel save project button");

	public AddProjectModalWindow() {
		super(By.cssSelector("div[class*='modal-content']"), "Add project modal window");
	}

	public void clickSaveProjectButton() {
		SAVE_PROJECT_BUTTON.click();
	}

	public void clickCancelSaveProjectButton() {
		CANCEL_SAVE_PROJECT_BUTTON.click();
	}

	public void enterProjectName(String projectName) {
		PROJECT_NAME_FIELD.clearAndType(projectName);
	}

}
