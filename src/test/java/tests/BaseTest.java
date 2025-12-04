package tests;

import io.qameta.allure.testng.AllureTestNg;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.RetryAnalyzer;
import utils.appium.TestConfig;
import utils.appium.driver.AppFactory;
import utils.appium.driver.AppiumServerManager;
import utils.listeners.AllureListener;
import utils.listeners.BrowserStackListener;

import java.net.MalformedURLException;
import java.util.Arrays;

@Listeners({AllureTestNg.class, AllureListener.class, BrowserStackListener.class})
public class BaseTest {

    @BeforeTest
    public void setUpRetry(ITestContext context) {
        Arrays.stream(context.getAllTestMethods())
                .forEach(method -> method.setRetryAnalyzerClass(RetryAnalyzer.class));
    }

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
    public void tearDown(ITestResult result) {
        AppFactory.quitApp();
    }

    @AfterSuite
    public static void serverStop() {
        if (!TestConfig.environment.isCloud()) {
            AppiumServerManager.stop();
        }
        AppFactory.resetBuildName();
    }
}
