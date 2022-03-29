package com.DiscountMugs.HomePage;

import com.BelUsa.baseClass.BaseClass;
import com.BelUsa.utility.JSExecuteUtil;
import com.BelUsa.utility.TestUtil;
import com.BelUsa.utility.ValidationUtil;
import com.BelUsa.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TC7085 extends BaseClass {
    @Parameters({"platform"})
    @Test
    public void ValidateThatTypingKeywordInTheSearchPanelWillDisplayMultipleItemsRelatedToTheKeyword(@Optional("NA") String platform) {

        //open browser
        openBrowser(platform);

        //Step 1: Browse through the DiscountMugs Website https://www.discountmugs.com/
        pageMethodManager.getHomePageMethods().launchDiscountMugApp();

        //Step 2: In the Search Pane, type in a keyword like: "can" or "lit"
        List<String> keyword = Arrays.asList("can","lit");
        int count = 0;

        for (int key = 0; key<keyword.size(); key ++){

            JSExecuteUtil.sendKeys(getDriver(), pageObjectManager.getDiscountMugHomePage().getSearchTextBox(),keyword.get(key));
            pageObjectManager.getDiscountMugHomePage().getSearchTextBox().click();
            WaitUtil.untilTimeCompleted(1200);

            boolean flag = true;
            for (int i = 0; i < pageObjectManager.getDiscountMugHomePage().getSearchItemsText().size(); i ++){
                if (!pageObjectManager.getDiscountMugHomePage().getSearchItemsText().get(i).getText().toLowerCase().contains(keyword.get(key).toLowerCase())){
                    flag = false;
                    break;
                }
            }

            //Step 3: Validate that typing a keyword in the Search Pane will display multiple items related to the keyword
            ValidationUtil.validateTestStep("Typing "+keyword.get(key)+" in the Search Panel displays multiple items related to",flag);
            count ++;

            if (keyword.size()>count){
                JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getDiscountMugHomePage().getDiscountMugLogoLink());
            }else {
                //Step 4: Select an Item from the options displayed from the Keyword in the Search Pane
                int index = TestUtil.getRandomRangeIntegerNumber(0,pageObjectManager.getDiscountMugHomePage().getSearchItemsText().size());
                String matchesName = pageObjectManager.getDiscountMugHomePage().getSearchItemsText().get(index).getText();

                JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getDiscountMugHomePage().getSearchItemsText().get(index));

                ValidationUtil.validateTestStep("Users be redirected to the Correct Item Page selected from the Search Pane",
                        pageObjectManager.getDiscountMugProductPage().getProductNameText().getText().toLowerCase().contains(matchesName.toLowerCase()));

            }
        }
    }
}
