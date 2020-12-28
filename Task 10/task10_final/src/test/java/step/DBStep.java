package step;

import java.util.List;
import java.util.stream.Collectors;

import aquality.selenium.core.logging.Logger;
import entity.Test;
import framework.db.DBRequestSender;

public class DBStep {

	private static final DBRequestSender DB_SENDER = new DBRequestSender();
	private static final String GET_TESTS_QUERY = "SELECT * FROM test WHERE (test.name, test.start_time) IN (SELECT DISTINCT test.name, MAX(test.start_time) FROM test JOIN project ON project.id = test.project_id WHERE project.name='%s' GROUP BY test.name) order by test.start_time desc";

	public static List<Test> getLastStartedProjectTests(String projectName, int number) {
		Logger.getInstance().info("Getting tests");
		List<Test> tests = DB_SENDER.select(String.format(GET_TESTS_QUERY, projectName), Test.class);
		return tests.stream().limit(number).collect(Collectors.toList());
	}

}
