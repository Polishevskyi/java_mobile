package utils.listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.appium.driver.AppDriver;

import java.io.ByteArrayInputStream;
import java.util.function.Supplier;

public class AllureListener implements ITestListener {

    public static <T> T logWaitForElement(By locator, Supplier<T> action) {
        return logStep("Waiting for element: " + formatLocator(locator), action);
    }

    public static void logEnterText(By locator, String text, Runnable action) {
        String textInfo = (text == null || text.isEmpty()) ? "[empty]" : text;
        logStep("Entering text '" + textInfo + "' into element: " + formatLocator(locator), action);
    }

    public static void logTap(By locator, Runnable action) {
        logStep("Tapping on element: " + formatLocator(locator), action);
    }

    public static <T> T logGetText(By locator, Supplier<T> action) {
        return logStep("Getting text from element: " + formatLocator(locator), action);
    }

    private static <T> T logStep(String stepName, Supplier<T> action) {
        return Allure.step(stepName, () -> action.get());
    }

    private static void logStep(String stepName, Runnable action) {
        Allure.step(stepName, () -> action.run());
    }

    private static String formatLocator(By locator) {
        String locatorStr = locator.toString();
        if (!locatorStr.startsWith("By.")) {
            return locatorStr;
        }

        int colonIndex = locatorStr.indexOf(':');
        if (colonIndex <= 0) {
            return locatorStr;
        }

        String strategy = locatorStr.substring(3, colonIndex);
        String value = locatorStr.substring(colonIndex + 2).trim();
        return strategy + " = \"" + value + "\"";
    }

    @Override
    public void onTestStart(ITestResult result) {
        if (!isTestMethod(result)) {
            return;
        }
        String testName = getTestName(result);
        Allure.getLifecycle().updateTestCase(testCase -> testCase.setName(testName));
        Allure.step("Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (!isTestMethod(result)) {
            return;
        }
        Allure.step("Test completed successfully: " + getTestName(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (!isTestMethod(result)) {
            return;
        }
        String errorMessage = buildErrorMessage(result);
        Allure.step(errorMessage);
        attachScreenshot("Screenshot on failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (!isTestMethod(result)) {
            return;
        }
        Allure.step("Test skipped: " + getTestName(result));
    }

    private boolean isTestMethod(ITestResult result) {
        return result.getMethod().isTest();
    }

    private String getTestName(ITestResult result) {
        String description = result.getMethod().getDescription();
        return (description != null && !description.isEmpty()) ? description : result.getMethod().getMethodName();
    }

    private String buildErrorMessage(ITestResult result) {
        String testName = getTestName(result);
        Throwable throwable = result.getThrowable();
        if (throwable != null && throwable.getMessage() != null) {
            return "Test failed: " + testName + " - " + throwable.getMessage();
        }
        return "Test failed: " + testName;
    }

    private void attachScreenshot(String name) {
        WebDriver driver = AppDriver.getCurrentDriver();
        if (!(driver instanceof TakesScreenshot)) {
            return;
        }
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.getLifecycle().addAttachment(name, "image/png", "png", new ByteArrayInputStream(screenshot));
        } catch (Exception ignored) {
        }
    }
}

