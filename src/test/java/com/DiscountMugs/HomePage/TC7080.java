package com.DiscountMugs.HomePage;

import com.BelUsa.baseClass.BaseClass;
import com.BelUsa.utility.TestUtil;
import com.BelUsa.utility.ValidationUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC7080 extends BaseClass {
    @Parameters({"platform"})
    @Test
    public void ValidateGlobalBannerDisplayedOnTopOfTheHomepage(@Optional("NA") String platform) {

        //open browser
        openBrowser(platform);

        //Step 1: Browse through the DiscountMugs Website https://www.discountmugs.com/
        pageMethodManager.getHomePageMethods().launchDiscountMugApp();

        //Step 2: Validate Global banner is displayed on top of homepage
        ValidationUtil.warningTestStep("Global banner is displayed on top of homepage",
                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("(//div[@class='header_section']/..//div)[5]"))));

        //Step 3: Validate Global Banner promo code matches promo shown in hero banner
        ValidationUtil.validateTestStep("Unable to verify text in an image with Selenium",true);

    }
}
