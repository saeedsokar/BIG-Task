package org.qa.tests;

import org.qa.pages.CheckoutPage;
import org.qa.pages.HomePage;
import org.qa.pages.ItemPage;
import org.qa.utilities.WebBaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddITemTest extends WebBaseTest {

    final HomePage homePage = new HomePage();
    final ItemPage itemPage = new ItemPage();
    final CheckoutPage checkoutPage = new CheckoutPage();

    SoftAssert softAssert = new SoftAssert();

    @Test(priority = 0)
    public void selectItemFromHome() {
        boolean isShopClicked = homePage.clickOnShopBarButton();
        softAssert.assertEquals(isShopClicked, true);

        boolean isItemAddedToBasket = homePage.selectItemToCart();
        softAssert.assertEquals(isItemAddedToBasket, true);

        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void addItemToBasket() {
        boolean selectColor = itemPage.selectColor();
        softAssert.assertEquals(selectColor,true);

        boolean isItemsAdded = itemPage.addItemsToCart(2);
        softAssert.assertEquals(isItemsAdded, true);

        boolean cartButtonClicked = itemPage.clickOnAddToCart();
        softAssert.assertEquals(cartButtonClicked, true);

        boolean isRedirectedToCheckout = itemPage.redirectToCheckoutPage();
        softAssert.assertEquals(isRedirectedToCheckout, true);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void viewCheckoutPage() {
        String colorAppeared = checkoutPage.isColorVisible();
        softAssert.assertEquals(colorAppeared,"Color: Black");

        boolean isTotalTextAppears = checkoutPage.isTotalTextVisible();
        softAssert.assertEquals(isTotalTextAppears,true);

        String totalValue = checkoutPage.isItemPriceVisible();
        softAssert.assertEquals(totalValue, "C$54.00");
        checkoutPage.clickOnProceedToPayment();
        softAssert.assertAll();

    }
}
