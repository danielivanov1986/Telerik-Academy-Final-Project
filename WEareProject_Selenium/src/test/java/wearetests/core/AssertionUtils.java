package wearetests.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testframework.Driver;

public class AssertionUtils {

    public static void assertElementVisible(WebDriver driver, String locatorType, By locatorValue) {
        WebElement element = null;

        // Use Driver's findElement method to locate the element
        if (driver instanceof Driver) {
            Driver customDriver = (Driver) driver;

            // Call the overridden findElement method from the Driver class
            element = customDriver.findElement(locatorValue); // Using custom findElement
        } else {
            throw new IllegalArgumentException("Driver must be of type Driver");
        }

        // Check visibility
        if (!element.isDisplayed()) {
            throw new AssertionError("Element is not visible as expected.");
        }
    }

    // Validate if an element is enabled
    public static void assertElementEnabled(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        if (!element.isEnabled()) {
            throw new AssertionError("Expected element to be enabled, but it is disabled.");
        }
    }

    // Validate if an element is disabled
    public static void assertElementDisabled(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        if (element.isEnabled()) {
            throw new AssertionError("Expected element to be disabled, but it is enabled.");
        }
    }

    // Validate if a checkbox or radio button is selected
    public static void assertElementSelected(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        if (!element.isSelected()) {
            throw new AssertionError("Expected element to be selected, but it is not.");
        }
    }

    // Validate if a checkbox or radio button is deselected
    public static void assertElementDeselected(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        if (element.isSelected()) {
            throw new AssertionError("Expected element to be deselected, but it is selected.");
        }
    }

    public static boolean isTextVisible(WebDriver driver, String text) {
        return driver.getPageSource().contains(text);
    }

    public static void assertEquals(String message, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(message + " - Expected: " + expected + ", but got: " + actual);
        }
    }
}

