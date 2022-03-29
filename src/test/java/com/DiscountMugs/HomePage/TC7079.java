package com.DiscountMugs.HomePage;

import com.BelUsa.baseClass.BaseClass;
import com.BelUsa.enums.Context;
import com.BelUsa.utility.JSExecuteUtil;
import com.BelUsa.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC7079 extends BaseClass {
    @Parameters({"platform"})
    @Test
    public void ValidateFlyoutCartIsDisplayedShowingItemsAddedToCartWhenTheUserClicksOnTheCartIconWithItems(@Optional("NA") String platform) {

        //open browser
        openBrowser(platform);

        //Step 1: Browse through the DiscountMugs Website https://www.discountmugs.com/
        pageMethodManager.getHomePageMethods().launchDiscountMugApp();

        //Step 2: Browse through some items in the Website and Add it to Cart
        pageMethodManager.getCommonPageMethods().DM_AddProductsToTheCart();

        ValidationUtil.validateTestStep("Flyout cart is displayed showing all items previously added to cart",
                scenarioContext.getContext(Context.PRODUCT_NAME).toString(),pageObjectManager.getDiscountMugYourCart().getProductNameText().get(0).getText());

        //Step 3: Click on the DiscountMugs Logo
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getDiscountMugHomePage().getDiscountMugLogoLink());

        ValidationUtil.validateTestStep("User is redirected back to the Homepage",
                getDriver().getCurrentUrl().contains(scenarioContext.getContext(Context.HOMEPAGE_URL).toString()));

        //Step 4: Click on the Cart Icon on Top of the Page
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getDiscountMugHomePage().getCartLink());

        ValidationUtil.validateTestStep("Flyout cart is displayed showing all items previously added to cart",
                scenarioContext.getContext(Context.PRODUCT_NAME).toString(),pageObjectManager.getDiscountMugYourCart().getProductNameText().get(0).getText());
    }
}
