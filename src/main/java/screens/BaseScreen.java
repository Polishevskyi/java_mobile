package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Constants;
import utils.appium.TestConfig;
import utils.appium.driver.AppDriver;
import utils.appium.enums.Platform;

import java.time.Duration;

public class BaseScreen {

    private static final int DEFAULT_TIMEOUT_SECONDS = 30;

    protected final AppiumDriver driver;
    protected final Platform platform;
    protected final WebDriverWait wait;

    public BaseScreen() {
        this.driver = (AppiumDriver) AppDriver.getCurrentDriver();
        this.platform = TestConfig.platform;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
    }

    public String getText(By byLocator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        if (platform == Platform.ANDROID) {
            return element.getText();
        } else {
            return element.getAttribute("value");
        }
    }

    public String getAttribute(By byLocator, String attr) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(byLocator)).getAttribute(attr);
    }

    public void assertElementIsVisible(By byLocator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
        Assert.assertTrue(element.isDisplayed(), Constants.ASSERT_ELEMENT_VISIBLE + byLocator);
    }

    public void assertElementIsNotVisible(By byLocator) {
        boolean isInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
        Assert.assertTrue(isInvisible, Constants.ASSERT_ELEMENT_NOT_VISIBLE + byLocator);
    }

    public void assertElementExists(By byLocator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        Assert.assertNotNull(element, Constants.ASSERT_ELEMENT_EXISTS + byLocator);
    }

    public void assertElementLabelContains(By byLocator, String partialValue) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
        String label = element.getAttribute("label");
        if (label == null || label.isEmpty()) {
            label = element.getAttribute("content-desc");
        }
        Assert.assertNotNull(label, Constants.ASSERT_LABEL_NULL);
        Assert.assertTrue(label.contains(partialValue), 
                String.format(Constants.ASSERT_LABEL_CONTAINS, partialValue, label));
    }

    public void assertElementValueContains(By byLocator, String partialValue) {
        String value = getText(byLocator);
        Assert.assertNotNull(value, Constants.ASSERT_VALUE_NULL);
        Assert.assertTrue(value.contains(partialValue), 
                String.format(Constants.ASSERT_VALUE_CONTAINS, partialValue, value));
    }

    public void tapWhenVisible(By byLocator) {
        wait.until(ExpectedConditions.elementToBeClickable(byLocator)).click();
    }

    public void setValue(By byLocator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
        element.sendKeys(value);
    }

    public void replaceValue(By byLocator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
        element.clear();
        element.sendKeys(value);
    }

    protected void swipe(Point start, Point end, Duration duration) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(input, 0);
        sequence.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        sequence.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        sequence.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(java.util.List.of(sequence));
    }
}
