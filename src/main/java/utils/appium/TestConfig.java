package utils.appium;

import utils.ConfigReader;
import utils.appium.enums.Environment;
import utils.appium.enums.Platform;

public class TestConfig {
    public static final Platform platform = Platform.fromString(ConfigReader.getProperty("platform"));
    public static final Environment environment = Environment.fromString(ConfigReader.getProperty("isCloud"));
}