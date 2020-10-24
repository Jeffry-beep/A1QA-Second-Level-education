package framework.loader;

import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class TestConfigurationLoader {

	private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";
	private static final FileBasedConfigurationBuilder<FileBasedConfiguration> BUILDER = new FileBasedConfigurationBuilder<FileBasedConfiguration>(
			PropertiesConfiguration.class);

	public static Configuration getTestConfiguration() {
		return getConfiguration(CONFIG_FILE_PATH);
	}

	public static Configuration getTestConfiguration(String filePath) {
		return getConfiguration(filePath);
	}

	private static Configuration getConfiguration(String path) {
		Parameters parameters = new Parameters();
		BUILDER.configure(parameters.properties().setFile(new File(path)));
		Configuration config = null;
		try {
			config = BUILDER.getConfiguration();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return config;
	}

}
