package com.DiscountMugs.HomePage;

import com.BelUsa.baseClass.BaseClass;
import com.BelUsa.utility.JSExecuteUtil;
import com.BelUsa.utility.TestUtil;
import com.BelUsa.utility.ValidationUtil;
import com.BelUsa.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC7086 extends BaseClass {
    @Parameters({"platform"})
    @Test
    public void ValidateThatTypingProductCodeInTheSearchPaneWillDisplayMultipleItemsRelatedToTheProductCode(@Optional("NA") String platform) {

        //open browser
        openBrowser(platform);

        //Step 1: Browse through the DiscountMugs Website https://www.discountmugs.com/
        pageMethodManager.getHomePageMethods().launchDiscountMugApp();

        //Step 2: In the Search Pane, type in a product code like: "tot11"
        String productCode = "tot11";

        JSExecuteUtil.sendKeys(getDriver(), pageObjectManager.getDiscountMugHomePage().getSearchTextBox(),productCode);
        pageObjectManager.getDiscountMugHomePage().getSearchTextBox().click();
        WaitUtil.untilTimeCompleted(1200);

        //Step 3: Validate that typing a product code in the Search Pane will display multiple items related to the product code
        ValidationUtil.validateTestStep("Validate that typing a product code \""+productCode+"\" in the Search Panel will display multiple items related to the product code",
                pageObjectManager.getDiscountMugHomePage().getSearchItemsText().size()>0);

        //Step 4: Select an Item from the options displayed from the Product Code in the Search Pane
        int index = TestUtil.getRandomRangeIntegerNumber(0,pageObjectManager.getDiscountMugHomePage().getSearchItemsText().size());
        String matchesName = pageObjectManager.getDiscountMugHomePage().getSearchItemsText().get(index).getText();

        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getDiscountMugHomePage().getSearchItemsText().get(index));

        ValidationUtil.validateTestStep("User is redirected to PDP for the selected product",
                pageObjectManager.getDiscountMugProductPage().getProductNameText().getText().toLowerCase().contains(matchesName.toLowerCase()));
    }
}
