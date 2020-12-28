package step;

import form.ProjectsPage;

public class SaveProjectStep {

	private static final ProjectsPage PROJECTS_PAGE = new ProjectsPage();

	public static void saveProject(String savingProjectName) {
		PROJECTS_PAGE.clickAddButton();
		PROJECTS_PAGE.waitAddProjectWindowForDisplayed();
		PROJECTS_PAGE.enterProjectName(savingProjectName);
		PROJECTS_PAGE.clickSaveProjectButton();
	}

}
