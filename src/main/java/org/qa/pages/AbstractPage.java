package org.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.utilities.WebBaseTest;

import java.time.Duration;
import java.util.List;

public class AbstractPage extends WebBaseTest {

    WebDriverWait wait;
    private final int duration = 30;

    protected void scrollToFindElement(By by) {
        new Actions(driver)
                .scrollToElement(driver.findElement(by))
                .perform();
    }

    protected WebElement waitForVisibilityOf(By by) throws Exception {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected WebElement waitForElementToBeClickableOf(By by) throws Exception {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected WebElement findElementByLocatorWithVisibility(By locator) throws Exception {
        WebElement element = null;
        try {
            element = waitForVisibilityOf(locator);
            return element;
        } catch (Exception e) {
            throw new Exception("NoSuchElement : can't locate the element with specified locators" + e.getMessage());
        }
    }

    protected List<WebElement> findElementList(By by) throws Exception {
        return driver.findElements(by);
    }

    protected List<WebElement> findElementList(WebElement element, By by) throws Exception {
        return element.findElements(by);
    }

    public void clickOnButton(By by) throws Exception {
        waitForVisibilityOf(by).click();
    }

    public void clickOnButton(WebElement element, By by) throws Exception {
        element.findElement(by).click();
    }

    public void skipAllAds() {
        ((JavascriptExecutor) driver).executeScript("document.querySelectorAll('ad-container').forEach(function(el) {el.style.display = 'none'});");

    }
}
