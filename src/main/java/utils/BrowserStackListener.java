package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.appium.TestConfig;
import utils.appium.driver.AppDriver;

public class BrowserStackListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        if (!TestConfig.environment.isCloud()) {
            return;
        }

        AppiumDriver driver = (AppiumDriver) AppDriver.getCurrentDriver();
        if (driver != null) {
            try {
                String testName = result.getMethod().getMethodName();
                if (result.getMethod().getDescription() != null && !result.getMethod().getDescription().isEmpty()) {
                    testName = result.getMethod().getDescription();
                }
                setSessionName(driver, testName);
            } catch (Exception e) {
                System.err.println("Failed to set BrowserStack session name: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        setSessionStatus(result, true, null);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String reason = "";
        if (result.getThrowable() != null) {
            reason = result.getThrowable().getMessage();
            if (reason == null || reason.isEmpty()) {
                reason = result.getThrowable().getClass().getSimpleName();
            }
        }
        setSessionStatus(result, false, reason);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        setSessionStatus(result, false, "Test skipped");
    }

    private void setSessionName(AppiumDriver driver, String testName) {
        try {
            String escapedName = escapeJson(testName != null ? testName : "");
            String script = String.format("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\": \"%s\"}}",
                    escapedName);
            ((RemoteWebDriver) driver).executeScript(script);
        } catch (Exception e) {
            System.err.println("Failed to set BrowserStack session name: " + e.getMessage());
        }
    }

    private void setSessionStatus(ITestResult result, boolean passed, String reason) {
        if (!TestConfig.environment.isCloud()) {
            return;
        }

        AppiumDriver driver = (AppiumDriver) AppDriver.getCurrentDriver();
        if (driver != null) {
            try {
                String status = passed ? "passed" : "failed";
                String escapedReason = escapeJson(reason != null ? reason : "");
                String script = String.format("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"%s\", \"reason\": \"%s\"}}",
                        status, escapedReason);
                ((RemoteWebDriver) driver).executeScript(script);
            } catch (Exception e) {
                System.err.println("Failed to set BrowserStack session status: " + e.getMessage());
            }
        }
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", " ")
                .replace("\r", " ")
                .replace("\t", " ");
    }
}

