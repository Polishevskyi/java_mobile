package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.BrowserStackListener;
import utils.appium.TestConfig;
import utils.appium.driver.AppDriver;
import utils.appium.driver.AppFactory;
import utils.appium.driver.AppiumServerManager;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

@Listeners(BrowserStackListener.class)
public class BaseTest {

    @BeforeSuite
    public static void serverStart() {
        if (!TestConfig.environment.isCloud()) {
            AppiumServerManager.start();
        }
    }

    @BeforeMethod
    public void launchApp() throws MalformedURLException {
        AppFactory.launchApp();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getTestName());
        }
        AppFactory.quitApp();
    }

    @AfterSuite
    public static void serverStop() {
        if (!TestConfig.environment.isCloud()) {
            AppiumServerManager.stop();
        }
        AppFactory.resetBuildName();
    }

    private void takeScreenshot(String testName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) AppDriver.getCurrentDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String filePath = "./screenshot/" + testName + ".jpg";
        FileUtils.copyFile(source, new File(filePath));
    }
}
