package utils.appium.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import utils.ConfigReader;
import utils.appium.TestConfig;
import utils.appium.enums.Platform;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AppFactory {

    private static AppiumDriver driver;
    private static volatile String suiteBuildName = null;

    private static Map<String, Object> getBrowserstackOptions() {
        Map<String, Object> options = new HashMap<>();
        options.put("userName", ConfigReader.getProperty("browserstack.username"));
        options.put("accessKey", ConfigReader.getProperty("browserstack.accessKey"));
        options.put("appiumVersion", ConfigReader.getProperty("browserstack.appiumVersion"));
        options.put("buildName", getBuildName());
        options.put("projectName", getProjectName());
        return options;
    }

    private static String getBuildName() {
        synchronized (AppFactory.class) {
            if (suiteBuildName == null) {
                String platform = TestConfig.platform.toString();
                String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                suiteBuildName = platform + " - " + date;
            }
            return suiteBuildName;
        }
    }

    private static String getProjectName() {
        return Objects.requireNonNullElse(
                ConfigReader.getProperty("browserstack.projectName"),
                System.getProperty("browserstack.projectName", "Mobile Automation")
        );
    }

    public static void resetBuildName() {
        suiteBuildName = null;
    }

    private static URI getServerUrl() {
        String url = TestConfig.environment.isCloud()
                ? ConfigReader.getProperty("browserstack.hubUrl")
                : ConfigReader.getProperty("appium.localUrl");
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
                        .setCapability("bstack:options", getBrowserstackOptions());
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
                    .setCapability("bstack:options", getBrowserstackOptions());
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
