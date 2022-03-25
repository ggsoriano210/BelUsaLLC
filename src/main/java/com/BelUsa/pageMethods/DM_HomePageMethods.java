package com.BelUsa.pageMethods;

import com.BelUsa.baseClass.ScenarioContext;
import com.BelUsa.managers.FileReaderManager;
import com.BelUsa.managers.PageObjectManager;
import com.BelUsa.utility.ValidationUtil;
import com.BelUsa.utility.WaitUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.*;


public class DM_HomePageMethods {

	private WebDriver driver;
	private ScenarioContext scenarioContext;

	public DM_HomePageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
		this.driver=driver;
		this.scenarioContext = scenarioContext;
	}

	@Step("Start Launching of DiscountMug Application")
	public synchronized void launchDiscountMugApp() {

		//This will catch the exception when browser does not load DiscountMug application
			ValidationUtil.validateTestStep("Start Launching of DiscountMug Application", true);

			//launch application under test
			driver.get(FileReaderManager.getInstance().getConfigReader().getDiscountMugApplicationURL());

			//get current url from application
			String actualURL = driver.getCurrentUrl();
			//get url from config file
			String expectedURL = FileReaderManager.getInstance().getConfigReader().getDiscountMugApplicationURL();

			//disable Print popup
			((JavascriptExecutor)driver).executeScript("window.print=function(){};");

				//validate correct url is open
				ValidationUtil.validateTestStep("Validate Discount Mug application open Successfully",actualURL, expectedURL);

		WaitUtil.untilTimeCompleted(5000);
		if(FileReaderManager.getInstance().getConfigReader().getDiscountMugApplicationURL().equals("https://www.discountmugs.com/")){
			WaitUtil.untilPageLoadComplete(driver);
			if(driver.findElements(By.xpath("//div[contains(@class, 'pencil-banner-container')]")).size() >= 1 ) {
				JavascriptExecutor js = null;
				if (driver instanceof JavascriptExecutor) {
					js = (JavascriptExecutor) driver;
				}
				js.executeScript("return document.getElementsByClassName('pencil-banner-container')[0].remove();");
			}
		}
	}
}