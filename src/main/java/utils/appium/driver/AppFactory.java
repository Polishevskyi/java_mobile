package utils.appium.driver;

import utils.ConfigReader;
import utils.appium.TestConfig;
import utils.appium.enums.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class AppFactory {

    private static AppiumDriver driver;

    private static Map<String, Object> getBrowserstackOptions() {
        Map<String, Object> options = new HashMap<>();
        options.put("userName", ConfigReader.getProperty("browserstackUserName"));
        options.put("accessKey", ConfigReader.getProperty("browserstackAccessKey"));
        options.put("appiumVersion", ConfigReader.getProperty("browserstackAppiumVersion"));
        return options;
    }

    private static URI getServerUrl() {
        String url = TestConfig.environment.isCloud()
                ? ConfigReader.getProperty("browserstackHubUrl")
                : ConfigReader.getProperty("appiumLocalUrl");
        return URI.create(url);
    }

    private static UiAutomator2Options createAndroidOptions() {
        UiAutomator2Options options = new UiAutomator2Options();
        String prefix = TestConfig.environment.isCloud() ? "android.cloud." : "android.local.";

        options.setDeviceName(ConfigReader.getProperty(prefix + "deviceName"))
                .setPlatformVersion(ConfigReader.getProperty(prefix + "platformVersion"))
                .setAppPackage(ConfigReader.getProperty(prefix + "appPackage"))
                .setAppActivity(ConfigReader.getProperty(prefix + "appActivity"))
                .setFullReset(false)
                .setNoReset(false)
                .setAutoGrantPermissions(true);

        String appPath = ConfigReader.getProperty(prefix + "app");
        if (appPath != null && !appPath.isEmpty()) {
            if (TestConfig.environment.isCloud()) {
                options.setApp(appPath)
                        .setCapability(ConfigReader.getProperty("browserstackOptionsKey"), getBrowserstackOptions());
            } else {
                File appFile = new File(appPath);
                if (!appFile.isAbsolute()) {
                    appFile = new File(System.getProperty("user.dir"), appPath);
                }
                options.setApp(appFile.getAbsolutePath());
            }
        }

        return options;
    }

    private static XCUITestOptions createIosOptions() {
        XCUITestOptions options = new XCUITestOptions();
        String prefix = TestConfig.environment.isCloud() ? "ios.cloud." : "ios.local.";

        options.setDeviceName(ConfigReader.getProperty(prefix + "deviceName"))
                .setPlatformVersion(ConfigReader.getProperty(prefix + "platformVersion"))
                .setBundleId(ConfigReader.getProperty(prefix + "bundleId"))
                .setFullReset(false)
                .setNoReset(false);

        if (!TestConfig.environment.isCloud()) {
            options.setUdid(ConfigReader.getProperty(prefix + "udid"));
        } else {
            options.setApp(ConfigReader.getProperty(prefix + "app"))
                    .setCapability(ConfigReader.getProperty("browserstackOptionsKey"), getBrowserstackOptions());
        }

        return options;
    }

    public static void launchApp() throws MalformedURLException {
        URI serverUrl = getServerUrl();

        if (TestConfig.platform == Platform.IOS) {
            XCUITestOptions iosOptions = createIosOptions();
            driver = new IOSDriver(serverUrl.toURL(), iosOptions);
        } else {
            UiAutomator2Options androidOptions = createAndroidOptions();
            driver = new AndroidDriver(serverUrl.toURL(), androidOptions);
        }

        AppDriver.setDriver(driver);
    }

    public static void quitApp() {
        AppiumDriver currentDriver = (AppiumDriver) AppDriver.getCurrentDriver();
        if (currentDriver != null) {
            currentDriver.quit();
            AppDriver.setDriver(null);
        }
    }
}
