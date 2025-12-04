package utils.appium.driver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import java.io.File;
import utils.ConfigReader;

public class AppiumServerManager {
    private static AppiumDriverLocalService server;

    private static void initialize() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withAppiumJS(new File(ConfigReader.getProperty("appium.jsPath")))
                .usingDriverExecutable(new File(ConfigReader.getProperty("appium.nodePath")))
                .usingPort(Integer.parseInt(ConfigReader.getProperty("appium.port")));
        server = AppiumDriverLocalService.buildService(builder);
    }

    private static AppiumDriverLocalService getInstance() {
        if (server == null) {
            initialize();
        }
        return server;
    }

    public static void start() {
        getInstance().start();
    }

    public static void stop() {
        if (server != null) {
            try {
                server.stop();
            } catch (Exception e) {
                System.err.println("Error stopping Appium server: " + e.getMessage());
            }
        }
    }
}
