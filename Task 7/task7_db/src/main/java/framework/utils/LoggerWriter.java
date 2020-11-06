package framework.utils;

import java.util.List;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerWriter {

	private static final Logger LOGGER = LogManager.getLogger(LoggerWriter.class);

	private LoggerWriter() {
		throw new IllegalStateException("Utility class");
	}

	public static void write(List<Object[]> objectsList, Function<Object[], String> formatFunction) {
		for (Object[] array : objectsList) {
			LOGGER.info(formatFunction.apply(array));
		}
	}

}
