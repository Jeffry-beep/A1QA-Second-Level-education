package framework.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.RandomStringUtils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import framework.constants.ImageType;

public class ScreenshotUtils {

	private static final String SCREENSHOT_PATH_FORMAT = "%s/%s%s";

	private ScreenshotUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static String makeScreenshot(String saveFolderPath) {
		String screenshotPath = null;
		try {
			screenshotPath = String.format(SCREENSHOT_PATH_FORMAT, saveFolderPath,
					RandomStringUtils.randomAlphanumeric(10), ImageType.PNG.getType());
			Files.write(Paths.get(screenshotPath), AqualityServices.getBrowser().getScreenshot());
		} catch (IOException e) {
			Logger.getInstance().warn("Can't create file");
		}
		return screenshotPath;
	}

}
