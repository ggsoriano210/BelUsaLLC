package com.DiscountMugs.HomePage;

import com.BelUsa.baseClass.BaseClass;
import com.BelUsa.utility.JSExecuteUtil;
import com.BelUsa.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC7084 extends BaseClass {
    @Parameters({"platform"})
    @Test
    public void ValidateUserAbleToTrackOrderPlacedViaTrackOrderPage(@Optional("NA") String platform) {

        //open browser
        openBrowser(platform);

        //Step 1: Browse through the DiscountMugs Website https://www.discountmugs.com/
        pageMethodManager.getHomePageMethods().launchDiscountMugApp();

        //Step 2: Hover on the Your Account Link
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getDiscountMugHomePage().getAccountLink());

        //Step 3: Click on the Track an Order Link
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getDiscountMugHomePage().getTrackOrderLink());

        //Step 4: Validate user redirected to the Track Order Page after clicking ‘Track Order’
        ValidationUtil.validateTestStep("Validate user redirected to the Track Order Page after clicking \"Track Order\"",
                getDriver().getCurrentUrl().contains("track_order_info.php"));

        //Step 5 : Track valid order
        //Unable to track a valid order for now, until we get one already created for verification

        //Step 6: Validate order status displayed on track order page
    }
}
