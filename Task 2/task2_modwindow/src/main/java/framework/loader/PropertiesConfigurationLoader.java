package framework.loader;

import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import aquality.selenium.core.logging.Logger;

public class PropertiesConfigurationLoader {

	private static final FileBasedConfigurationBuilder<FileBasedConfiguration> BUILDER = new FileBasedConfigurationBuilder<>(
			PropertiesConfiguration.class);

	private PropertiesConfigurationLoader() {
		throw new IllegalStateException("Utility class");
	}

	public static Configuration getConfiguration(String path) {
		Parameters parameters = new Parameters();
		BUILDER.configure(parameters.properties().setFile(new File(path)));
		Configuration config = null;
		try {
			config = BUILDER.getConfiguration();
		} catch (ConfigurationException e) {
			Logger.getInstance().warn("Failed initialize a Configuration object.");
		}
		return config;
	}

}
