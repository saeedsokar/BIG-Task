package org.qa.pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends AbstractPage {
    private final By totalText = By.xpath("//dt[.='Total']");
    private final By totalPrice = By.xpath("//dd[@id='total-sum']");
    private final By color = By.cssSelector("[data-wix-line-item-id='1'] .sbTnf91");
    private final By paymentButton = By.cssSelector(".sJldBxD");


    public boolean isTotalTextVisible() {
        try {
            WebElement total = waitForVisibilityOf(totalText);
            logger.log(LogStatus.INFO, "Total Text appears with title: " + total.getText());
            return total.isDisplayed();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get checkout page displayed due to: " + ex.getMessage());
            return false;
        }
    }


    public String isItemPriceVisible() {
        try {
            WebElement bookPrice = waitForVisibilityOf(totalPrice);
            logger.log(LogStatus.INFO, "Items price with value: " + bookPrice.getText());
            return bookPrice.getText();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get Items price displayed due to: " + ex.getMessage());
            return "";
        }
    }

    public String isColorVisible() {
        try {
            WebElement colorSpan = waitForVisibilityOf(color);
            logger.log(LogStatus.INFO, "color with value: " + colorSpan.getText());
            return colorSpan.getText();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get color displayed due to: " + ex.getMessage());
            return "";
        }
    }

    public boolean clickOnProceedToPayment() {
        try {
            clickOnButton(paymentButton);
            logger.log(LogStatus.INFO, "proceed to checkout");
            return true;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to click on payment button due to: " + ex.getMessage());
            return false;
        }
    }
}
