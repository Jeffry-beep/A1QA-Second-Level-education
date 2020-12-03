package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.db.DBRequestSender;
import framework.utils.LoggerWriter;
import test.constants.Queries;

public class DBTest {

	private static final Logger LOGGER = LogManager.getLogger(DBTest.class);

	@Parameters({ "date", "first_browser", "second_browser" })
	@Test
	public void test(String date, String firstBrowser, String secondBrowser) {
		DBRequestSender requestSender = new DBRequestSender();

		LOGGER.info("Get tests with a min working time");
		LoggerWriter.write(requestSender.select(Queries.SELECT_MIN_WORKING_TESTS_TIME),
				(array) -> String.format("Project: %s. Test: %s. Min working time: %d", array[0], array[1], array[2]));

		LOGGER.info("Get projects with a unique test count");
		LoggerWriter.write(requestSender.select(Queries.SELECT_ALL_PROJECT_WITH_UNIQUE_TEST_COUNT),
				(array) -> String.format("Project: %s. Test count: %d.", array[0], array[1]));

		LOGGER.info(String.format("Get tests before date %s", date));
		LoggerWriter.write(requestSender.select(Queries.SELECT_TESTS_BEFORE_DATE, date),
				(array) -> String.format("Project: %s. Test: %s. Date: %tF.", array[0], array[1], array[2]));

		LOGGER.info("Get tests executed in browsers");
		LoggerWriter.write(
				requestSender.select(Queries.SELECT_TEST_COUNT_EXECUTED_ON_BROWSERS, firstBrowser, secondBrowser),
				(array) -> String.format("Browser: %s. Test count: %d", array[0], array[1]));
	}
}
