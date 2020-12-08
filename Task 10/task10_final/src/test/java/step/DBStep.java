package step;

import java.util.List;

import aquality.selenium.core.logging.Logger;
import entity.Test;
import form.ProjectsPage;
import framework.db.DBRequestSender;

public class DBStep {

	private static final ProjectsPage PROJECTS_PAGE = new ProjectsPage();
	private static final DBRequestSender DB_SENDER = new DBRequestSender();
	private static final String GET_TESTS_QUERY = "SELECT * FROM test";

	public static void goToProject(String projectName) {
		Logger.getInstance().info("Clicking the project button");
		PROJECTS_PAGE.clickProjectButton(projectName);
	}

	public static List<Test> getTests() {
		Logger.getInstance().info("Getting tests");
		return DB_SENDER.select(GET_TESTS_QUERY, Test.class);
	}

}
