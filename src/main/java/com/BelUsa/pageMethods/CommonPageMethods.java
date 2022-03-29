package com.BelUsa.pageMethods;

import com.BelUsa.baseClass.ScenarioContext;
import com.BelUsa.enums.Context;
import com.BelUsa.managers.PageMethodManager;
import com.BelUsa.managers.PageObjectManager;
import com.BelUsa.pageObjects.*;
import com.BelUsa.utility.*;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class CommonPageMethods {

    private WebDriver driver;
    private PageObjectManager pageObjectManager;
    private PageMethodManager pageMethodManager;
    private ScenarioContext scenarioContext;
    private DM_HomePage homePage;
    private DM_Category categoryPage;
    private DM_CustomProductPage customProductPage;
    private DM_Product productPage;

    public CommonPageMethods(WebDriver driver, PageObjectManager pageObjectManager, PageMethodManager pageMethodManager, ScenarioContext scenarioContext) {
        this.driver				= driver;
        this.pageObjectManager 	= pageObjectManager;
        this.pageMethodManager  = pageMethodManager;
        this.scenarioContext 	= scenarioContext;
        this.homePage           = pageObjectManager.getDiscountMugHomePage();
        this.categoryPage       = pageObjectManager.getDiscountMugCategoryPage();
        this.customProductPage  = pageObjectManager.getDiscountMugCustomProductsPage();
        this.productPage        = pageObjectManager.getDiscountMugProductPage();
    }

    @Step("Adding random products to the cart")
    public synchronized void DM_AddProductsToTheCart() {

//        //review in all the available categories
//        JSExecuteUtil.clickOnElement(driver,homePage.getViewAllCategoriesButton());

        int link = TestUtil.getRandomRangeIntegerNumber(0,homePage.getPromoProductsLink().size());

        //select product per category
        ValidationUtil.validateTestStep("Customer select "+homePage.getPromoProductsLink().get(link).getText()+" category",true);
        JSExecuteUtil.clickOnElement(driver,homePage.getPromoProductsLink().get(link));

        //open custom page
        if (categoryPage.getSeeAllButton().size()>0){
            int product = TestUtil.getRandomRangeIntegerNumber(0, categoryPage.getSeeAllButton().size());
            ValidationUtil.validateTestStep("Customer is reviewing "+ categoryPage.getTitleText().get(product).getText()+" details",true);
            JSExecuteUtil.clickOnElement(driver, categoryPage.getSeeAllButton().get(product));
        }else {

        }


        //open product page
        int custom = TestUtil.getRandomRangeIntegerNumber(0, customProductPage.getProductNameText().size());
        scenarioContext.setContext(Context.PRODUCT_NAME,customProductPage.getProductNameText().get(custom).getText());

        ValidationUtil.validateTestStep("Customer selects "+ customProductPage.getProductNameText().get(custom).getText(),true);
        JSExecuteUtil.clickOnElement(driver, customProductPage.getViewDetailsButton().get(custom));

        //click on See Pricing Details button
        WaitUtil.untilTimeCompleted(1200);
        JSExecuteUtil.clickOnElement(driver,productPage.getSeePricingDetailsButton().get(0));

        if (TestUtil.verifyElementDisplayed(productPage.getImprintColorDropDown())){
            //select imprint color
            TestUtil.selectDropDownUsingIndex(productPage.getImprintColorDropDown(),1);
        }

        //click on Add To Cart button
        JSExecuteUtil.clickOnElement(driver, productPage.getAddToCartButton());

    }






}
