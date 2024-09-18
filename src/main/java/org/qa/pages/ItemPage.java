package org.qa.pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ItemPage extends AbstractPage {

    //    private final By itemColor= By.cssSelector("[aria-label='Black']");
    private final By itemColor = By.xpath("//div[@class='sDPgJyH sCCL_KX szKIKT_']");
    private final By addToCart = By.cssSelector(".sLvk1oG");
    private final By inputNumber = By.cssSelector("[type='number']");
    private final By itemCountUp = By.xpath("//span[@class='bzZYud']");
    private final By viewCartButton = By.xpath("//a[@id='widget-view-cart-button']");
    private final By viewCartIframe= By.cssSelector(".U73P_q");


    public boolean selectColor() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            WebElement color = waitForVisibilityOf(itemColor);
            color.click();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(LogStatus.ERROR, "failed to select color due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean clickOnAddToCart() {
        try {
            WebElement button = waitForVisibilityOf(addToCart);
            button.click();
            return true;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to add to cart button due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean redirectToCheckoutPage(){
        try{
            WebElement iframe = waitForVisibilityOf(viewCartIframe);
            driver.switchTo().frame(iframe);
            waitForElementToBeClickableOf(viewCartButton).click();
            driver.switchTo().defaultContent();
            return true;
        }catch(Exception ex){
            logger.log(LogStatus.ERROR, "failed to redirect to checkout page due to: " + ex.getMessage());
            return false;
        }
    }


    public boolean addItemsToCart(int count) {
        try {
            waitForVisibilityOf(inputNumber).click();
            WebElement countUp = waitForVisibilityOf(itemCountUp);
            for (int i = 0; i < count; i++) {
                countUp.click();
            }
            return true;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get add items to cart due to: " + ex.getMessage());
            return false;
        }
    }
}
