package test.constants;

public class Queries {

	public final static String SELECT_MIN_WORKING_TESTS_TIME = "SELECT project.name AS PROJECT, test.name AS TEST, MIN(test.end_time - test.start_time) AS MIN_WORKING_TIME FROM test JOIN project ON test.project_id = project.id WHERE test.end_time and test.start_time IS NOT NULL GROUP BY TEST ORDER BY PROJECT, TEST;";
	public final static String SELECT_ALL_PROJECT_WITH_UNIQUE_TEST_COUNT = "SELECT project.name AS PROJECT, COUNT(DISTINCT test.name) AS TEST_COUNT FROM project JOIN test ON test.project_id = project.id GROUP BY PROJECT;";
	public final static String SELECT_TESTS_BEFORE_DATE = "SELECT project.name AS PROJECT, test.name AS TEST, test.start_time AS DATE FROM test JOIN project ON project.id = test.project_id WHERE test.start_time >= ? ORDER BY PROJECT, TEST;";
	public final static String SELECT_TEST_COUNT_EXECUTED_ON_BROWSERS = "SELECT test.browser AS BROWSER, COUNT(*) AS TEST_COUNT FROM test WHERE test.browser = ? UNION SELECT test.browser AS BROWSER, COUNT(*) AS TEST_COUNT FROM test WHERE test.browser = ?;";
}
