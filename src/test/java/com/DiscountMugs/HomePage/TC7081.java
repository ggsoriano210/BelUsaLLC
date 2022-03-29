package com.DiscountMugs.HomePage;

import com.BelUsa.baseClass.BaseClass;
import com.BelUsa.managers.FileReaderManager;
import com.BelUsa.utility.JSExecuteUtil;
import com.BelUsa.utility.TestUtil;
import com.BelUsa.utility.ValidationUtil;
import com.BelUsa.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC7081 extends BaseClass {
    @Parameters({"platform"})
    @Test
    public void ValidateCHATBOXExpandsAtTheLowerRightSideOfThePageAfterClickingChatNowFromHeader(@Optional("NA") String platform) {

        //open browser
        openBrowser(platform);

        //Step 1: Browse through the DiscountMugs Website https://www.discountmugs.com/
        pageMethodManager.getHomePageMethods().launchDiscountMugApp();

        //Step 2: Click on the "Click to Chat Now Link" on the top part of the Page
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getDiscountMugHomePage().getChatNowLink());

       //Validate Chat Module opens
        ValidationUtil.validateTestStep("The chat module opens",
                getDriver().findElements(By.xpath("//div[contains(@class,'fc-widget')]")).get(0).getClass().getName().contains("open"));

        getDriver().switchTo().frame(1);
        if (TestUtil.verifyElementDisplayed(pageObjectManager.getDiscountMugHomePage().getChatBackButton())){
            JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getDiscountMugHomePage().getChatBackButton());
        }

        //Able to Chat with Sales Representatives
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getDiscountMugHomePage().getChatSalesButton());

        if(FileReaderManager.getInstance().getConfigReader().getDiscountMugApplicationURL().equals(FileReaderManager.getInstance().getConfigReader().getDiscountMugApplicationURL())){
            WaitUtil.untilTimeCompleted(5000);

            //closing promo container
            getDriver().switchTo().activeElement().sendKeys(Keys.chord(Keys.ESCAPE));
            WaitUtil.untilTimeCompleted(1200);

            ValidationUtil.validateTestStep("Promo Container closed",true);

        }
        ValidationUtil.validateTestStep("Customer is able to chat with Sales associates",
                getDriver().findElements(By.xpath("//h1[contains(@class,'channel-title')]")).get(0).getText(),"Sales");

        //move back to the main chat options
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getDiscountMugHomePage().getChatBackButton());

       //Able to Chat with Customer Service Representatives
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getDiscountMugHomePage().getChatCustomerServiceButton());

        ValidationUtil.validateTestStep("Customer is able to chat with Customer Service associates",
                getDriver().findElements(By.xpath("//h1[contains(@class,'channel-title')]")).get(0).getText(),"Customer Service");
    }

}
