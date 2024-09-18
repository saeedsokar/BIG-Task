package org.qa.pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;

public class HomePage extends AbstractPage {

    private final By shopBarButton = By.xpath("//a[.='Shop Now']");
    private final By bestSellerItem = By.xpath("//a[.='Best SellerQuick View']");

    public boolean clickOnShopBarButton() {
        try {
            clickOnButton(shopBarButton);
            return true;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to open shop page due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean selectItemToCart() {
        try {
            scrollToFindElement(bestSellerItem);
            clickOnButton(bestSellerItem);
            return true;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to add to basket due to: " + ex.getMessage());
            return false;
        }
    }
}
