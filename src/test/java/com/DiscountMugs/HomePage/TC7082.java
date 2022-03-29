package com.DiscountMugs.HomePage;

import com.BelUsa.baseClass.BaseClass;
import com.BelUsa.utility.JSExecuteUtil;
import com.BelUsa.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC7082 extends BaseClass {
    @Parameters({"platform"})
    @Test
    public void ValidateUserRedirectedToPromotionalPageWhenHEROBannerIsClicked(@Optional("NA") String platform) {

        //open browser
        openBrowser(platform);

        //Step 1: Browse through the DiscountMugs Website https://www.discountmugs.com/
        pageMethodManager.getHomePageMethods().launchDiscountMugApp();

        //Step 2: Click on the Hero Banner in the Homepage
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getDiscountMugHomePage().getHeroBannerLink());
        ValidationUtil.validateTestStep("Customer clicks on the Hero Banner",true);

        //Step 3:Validate User is redirected to promotional product page when hero banner is clicked
        ValidationUtil.validateTestStep("User is redirected to promotional product page when hero banner is clicked",
                getDriver().getCurrentUrl().contains("promotional-products"));
    }
}
