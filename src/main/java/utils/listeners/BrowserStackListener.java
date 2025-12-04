package utils.listeners;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.appium.TestConfig;
import utils.appium.driver.AppDriver;

public class BrowserStackListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        if (!shouldProcess()) {
            return;
        }
        String testName = getTestName(result);
        executeScript("setSessionName", "name", testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        setSessionStatus(true, null);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String reason = extractFailureReason(result);
        setSessionStatus(false, reason);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        setSessionStatus(false, "Test skipped");
    }

    private void setSessionStatus(boolean passed, String reason) {
        if (!shouldProcess()) {
            return;
        }
        String status = passed ? "passed" : "failed";
        executeScript("setSessionStatus", "status", status, "reason", reason != null ? reason : "");
    }

    private void executeScript(String action, String... params) {
        AppiumDriver driver = getDriver();
        if (driver == null) {
            return;
        }
        try {
            String script = buildScript(action, params);
            ((RemoteWebDriver) driver).executeScript(script);
        } catch (Exception e) {
            System.err.println("Failed to execute BrowserStack script: " + e.getMessage());
        }
    }

    private String buildScript(String action, String... params) {
        StringBuilder arguments = new StringBuilder();
        for (int i = 0; i < params.length; i += 2) {
            if (i > 0) {
                arguments.append(", ");
            }
            String key = params[i];
            String value = escapeJson(params[i + 1]);
            arguments.append("\"").append(key).append("\": \"").append(value).append("\"");
        }
        return String.format("browserstack_executor: {\"action\": \"%s\", \"arguments\": {%s}}", action, arguments);
    }

    private boolean shouldProcess() {
        return TestConfig.environment.isCloud();
    }

    private AppiumDriver getDriver() {
        return (AppiumDriver) AppDriver.getCurrentDriver();
    }

    private String getTestName(ITestResult result) {
        String description = result.getMethod().getDescription();
        return (description != null && !description.isEmpty()) ? description : result.getMethod().getMethodName();
    }

    private String extractFailureReason(ITestResult result) {
        Throwable throwable = result.getThrowable();
        if (throwable == null) {
            return "";
        }
        String message = throwable.getMessage();
        return (message != null && !message.isEmpty()) ? message : throwable.getClass().getSimpleName();
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

