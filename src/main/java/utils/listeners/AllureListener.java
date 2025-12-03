package utils.listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.appium.driver.AppDriver;

import java.io.ByteArrayInputStream;

public class AllureListener implements ITestListener {

    private final BrowserStackListener browserStackListener;

    public AllureListener() {
        this.browserStackListener = new BrowserStackListener();
    }

    @Override
    public void onTestStart(ITestResult result) {
        if (!isTestMethod(result)) {
            return;
        }
        browserStackListener.onTestStart(result);
        String testName = getTestName(result);
        Allure.getLifecycle().updateTestCase(testCase -> testCase.setName(testName));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (!isTestMethod(result)) {
            return;
        }
        browserStackListener.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (!isTestMethod(result)) {
            return;
        }
        browserStackListener.onTestFailure(result);
        attachScreenshot("Screenshot on failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (!isTestMethod(result)) {
            return;
        }
        browserStackListener.onTestSkipped(result);
    }

    private boolean isTestMethod(ITestResult result) {
        return result.getMethod().isTest();
    }

    private String getTestName(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        if (result.getMethod().getDescription() != null && !result.getMethod().getDescription().isEmpty()) {
            testName = result.getMethod().getDescription();
        }
        return testName;
    }

    private void attachScreenshot(String name) {
        WebDriver driver = AppDriver.getCurrentDriver();
        if (driver instanceof TakesScreenshot) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.getLifecycle().addAttachment(name, "image/png", "png", new ByteArrayInputStream(screenshot));
            } catch (Exception ignored) {
            }
        }
    }
}

