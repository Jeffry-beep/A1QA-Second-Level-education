package framework.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import aquality.selenium.core.logging.Logger;

public class FileUtils {

	private static final String FILE_PATH_FORMAT = "%s/%s";

	private FileUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static List<String> getContentAsList(String folderPath, String filename) {
		List<String> content = null;
		try {
			content = Files.readAllLines(Paths.get(String.format(FILE_PATH_FORMAT, folderPath, filename)));
		} catch (IOException e) {
			Logger.getInstance().debug("Failed to read all lines from the file", e);
		}
		return content;
	}

	public static byte[] getContentAsArray(String filePath) {
		byte[] content = null;
		try {
			content = Files.readAllBytes(Paths.get(filePath));
		} catch (IOException e) {
			Logger.getInstance().debug("Failed to read all lines from the file", e);
		}
		return content;
	}

}
