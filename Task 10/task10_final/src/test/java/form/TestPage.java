package form;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import entity.Test;

public class TestPage extends Form {

	private static final String COMMON_INFO_LOC = "div[class*='col-md-4']";
	private static final String IMAGE_CONTENT_SEPARATOR = "data:image/png;base64,";

	private final ITextBox commonInfoBox = super.getElementFactory().getTextBox(By.cssSelector(COMMON_INFO_LOC),
			"Common info textbox");
	private final ITextBox projectNameBox = commonInfoBox.findChildElement(
			By.cssSelector(".list-group-item:nth-child(1) > p[class*='list-group-item-text']"), ElementType.TEXTBOX);
	private final ITextBox testNameBox = commonInfoBox.findChildElement(
			By.cssSelector(".list-group-item:nth-child(2) > p[class*='list-group-item-text']"), ElementType.TEXTBOX);
	private final ITextBox methodNameBox = commonInfoBox.findChildElement(
			By.cssSelector(".list-group-item:nth-child(3) > p[class*='list-group-item-text']"), ElementType.TEXTBOX);
	private final ITextBox environmentBox = commonInfoBox.findChildElement(
			By.cssSelector(".list-group-item:nth-child(6) > p[class*='list-group-item-text']"), ElementType.TEXTBOX);
	private final ITextBox logsAttachmentsBox = super.getElementFactory()
			.getTextBox(By.cssSelector("div[class*='col-md-8']"), "Logs and attachments box");
	private final ITextBox imageBox = logsAttachmentsBox.findChildElement(By.cssSelector("img[class*='thumbnail']"),
			ElementType.TEXTBOX);

	public TestPage() {
		super(By.cssSelector(COMMON_INFO_LOC), "Test page");
	}

	public Test getTest() {
		Test test = new Test();
		test.setName(getTestName());
		test.setMethodName(getMethodName());
		test.setEnv(getEnvironment());
		return test;
	}

	public String getProjectName() {
		return projectNameBox.getText();
	}

	public String getTestName() {
		return testNameBox.getText();
	}

	public String getMethodName() {
		return methodNameBox.getText();
	}

	public String getEnvironment() {
		return environmentBox.getText();
	}

	public String getImageContent() {
		return StringUtils.substringAfter(imageBox.getAttribute("src"), IMAGE_CONTENT_SEPARATOR);
	}

	public byte[] getDecodedImageContent() {
		return Base64.decodeBase64(getImageContent());
	}

}
