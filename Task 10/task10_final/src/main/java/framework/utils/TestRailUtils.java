package framework.utils;

import org.testng.ITestResult;

import framework.testrail.entities.Result;
import framework.testrail.entities.ResultStatus;

public class TestRailUtils {

	private TestRailUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static Result getResult(ResultStatus resultStatus, String comment, int userId, int testId) {
		return new Result(resultStatus, userId, testId, System.currentTimeMillis(), userId, comment);
	}

	public static ResultStatus getTestResultStatus(ITestResult result) {
		return result.getStatus() == ITestResult.SUCCESS ? ResultStatus.PASSED
				: result.getStatus() == ITestResult.FAILURE ? ResultStatus.FAILED : ResultStatus.BLOCKED;
	}

	public static String getComment(ITestResult result) {
		return result.getStatus() == ITestResult.SUCCESS ? "Passed successfully"
				: result.getStatus() == ITestResult.FAILURE ? "Test failed" : "Test was blocked";
	}

}
