package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Below was added to load the config file
 *
 */
public class ConfigReader {

	private Properties prop;

	public Properties initproperties() {
		prop = new Properties();
		// FileInputStream to load the ConfigFile
		try {
			FileInputStream ip = new FileInputStream("src//test//resources//Config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;

	}

}
