package form;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class ProjectsPage extends Form {

	private static final String VERSION_SEPARATOR = "Version:";
	private static final String PROJECTS_LIST_LOC = "a[class*='list-group-item']";

	private final AddProjectModalWindow addProjectWindow = new AddProjectModalWindow();
	private final ITextBox footerBox = super.getElementFactory().getTextBox(By.cssSelector("p[class*='footer-text']"),
			"Footer text textbox");
	private final IButton addButton = super.getElementFactory().getButton(By.cssSelector("button[class*='btn']"),
			"Add button");

	public ProjectsPage() {
		super(By.cssSelector("div[class*='panel']"), "Projects page");
	}

	public String getFooterText() {
		return footerBox.getText();
	}

	public String getVersion() {
		Logger.getInstance().debug("Getting the version from the footer text");
		return StringUtils.substringAfter(footerBox.getText(), VERSION_SEPARATOR).trim();
	}

	public void clickProjectButton(String projectName) {
		getProject(projectName).click();
	}

	public boolean isProjectDisplayed(String projectName) {
		return Objects.nonNull(getProject(projectName));
	}

	private ITextBox getProject(String projectName) {
		return super.getElementFactory().findElements(By.cssSelector(PROJECTS_LIST_LOC), ITextBox.class).stream()
				.filter(elem -> elem.getText().equals(projectName)).findAny().orElseGet(null);
	}

	public void clickAddButton() {
		addButton.click();
	}

	public void clickSaveProjectButton() {
		addProjectWindow.clickSaveProjectButton();
	}

	public void clickCancelSaveProjectButton() {
		addProjectWindow.clickCancelSaveProjectButton();
	}

	public void enterProjectName(String projectName) {
		addProjectWindow.enterProjectName(projectName);
	}

	public boolean waitAddProjectWindowForDisplayed() {
		return addProjectWindow.state().waitForDisplayed();
	}

	public boolean waitAddProjectWindowForNotDisplayed() {
		return addProjectWindow.state().waitForNotDisplayed();
	}

}
