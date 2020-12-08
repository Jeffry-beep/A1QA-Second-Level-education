package form;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import entity.Test;
import entity.TestStatus;

public class ProjectPage extends Form {

	private static final String PROJECT_NAME_SEPARATOR = "Home";
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	private static final String TEST_LOC = "tr";
	private static final String TEST_NAME_LOC = "td:nth-child(1)";
	private static final String TEST_NAME_BUTTON_LOC = "a";
	private static final String TEST_METHOD_LOC = "td:nth-child(2)";
	private static final String TEST_RESULT_LOC = "td:nth-child(3)";
	private static final String TEST_START_TIME_LOC = "td:nth-child(4)";
	private static final String TEST_END_TIME_LOC = "td:nth-child(5)";

	private final ITextBox headerBox = super.getElementFactory().getTextBox(By.cssSelector("ol[class*='breadcrumb']"),
			"Header text box");
	private final ITextBox testsBox = super.getElementFactory().getTextBox(By.id("allTests"), "Tests box");

	public ProjectPage() {
		super(By.cssSelector("canvas[class*='flot-base']"), "Project page");
	}

	public String getHeaderText() {
		return headerBox.getText();
	}

	public String getProjectName() {
		return StringUtils.substringAfter(getHeaderText(), PROJECT_NAME_SEPARATOR).trim();
	}

	public List<Test> getTests() {
		return getTestsList(getTestsBoxes());
	}

	public Test getTest(String testName) {
		return getTest(getTestsBoxes(), testName);
	}

	private List<Test> getTestsList(List<ITextBox> testsBoxes) {
		List<Test> tests = new ArrayList<Test>(testsBoxes.size());
		for (ITextBox testBox : testsBoxes) {
			testBox.getJsActions().scrollToTheCenter();
			tests.add(createTest(testBox));
		}
		return tests;
	}

	private Test getTest(List<ITextBox> testsBoxes, String testName) {
		Test test = null;
		for (ITextBox testBox : testsBoxes) {
			testBox.getJsActions().scrollToTheCenter();
			if (getTestName(testBox).equals(testName)) {
				test = createTest(testBox);
			}
		}
		return test;
	}

	private Test createTest(ITextBox testBox) {
		Test test = new Test();
		test.setName(getTestName(testBox));
		test.setMethodName(getTestMethod(testBox));
		test.setStatusId(getTestResult(testBox).orElse(null));
		test.setStartTime(getTestStartTime(testBox));
		test.setEndTime(getTestEndTime(testBox));
		return test;
	}

	private List<ITextBox> getTestsBoxes() {
		List<ITextBox> testBoxes = testsBox.findChildElements(By.cssSelector(TEST_LOC), ElementType.TEXTBOX);
		testBoxes.remove(0);
		testBoxes.removeIf(elem -> Objects.isNull(elem));
		return testBoxes;
	}

	private ITextBox getTestBox(List<ITextBox> testsBoxes, String testName) {
		ITextBox testBox = null;
		for (ITextBox box : testsBoxes) {
			box.getJsActions().scrollToTheCenter();
			if (getTestName(box).equals(testName)) {
				testBox = box;
			}
		}
		return testBox;
	}

	private String getTestName(ITextBox testBox) {
		return testBox.findChildElement(By.cssSelector(TEST_NAME_LOC), ElementType.TEXTBOX).getText();
	}

	private String getTestMethod(ITextBox testBox) {
		return testBox.findChildElement(By.cssSelector(TEST_METHOD_LOC), ElementType.TEXTBOX).getText();
	}

	private Optional<Integer> getTestResult(ITextBox testBox) {
		String testResult = testBox.findChildElement(By.cssSelector(TEST_RESULT_LOC), ElementType.TEXTBOX).getText();
		return testResult.equals(TestStatus.PASSED.getDescription()) ? Optional.of(TestStatus.PASSED.getStatusId())
				: testResult.equals(TestStatus.FAILED.getDescription()) ? Optional.of(TestStatus.FAILED.getStatusId())
						: testResult.equals(TestStatus.SKIPPED.getDescription())
								? Optional.of(TestStatus.SKIPPED.getStatusId())
								: Optional.empty();
	}

	private LocalDateTime getTestStartTime(ITextBox testBox) {
		return getTimeBySelector(testBox, TEST_START_TIME_LOC);
	}

	private LocalDateTime getTestEndTime(ITextBox testBox) {
		return getTimeBySelector(testBox, TEST_END_TIME_LOC);
	}

	private LocalDateTime getTimeBySelector(ITextBox testBox, String locator) {
		LocalDateTime dateTime = null;
		String time = testBox.findChildElement(By.cssSelector(locator), ElementType.TEXTBOX).getText();
		if (Objects.nonNull(time) && !time.isBlank()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
			dateTime = LocalDateTime.parse(time, formatter);
		}
		return dateTime;
	}

	public void clickTestNameButton(String testName) {
		getTestBox(getTestsBoxes(), testName).findChildElement(By.cssSelector(TEST_NAME_LOC), ElementType.TEXTBOX)
				.findChildElement(By.cssSelector(TEST_NAME_BUTTON_LOC), ElementType.BUTTON).click();
	}

	public boolean waitTestForDisplayed(String testName) {
		try {
			AqualityServices.getConditionalWait()
					.waitForTrue(() -> Objects.nonNull(getTestBox(getTestsBoxes(), testName)));
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return getTestBox(getTestsBoxes(), testName).state().waitForDisplayed();
	}

}
