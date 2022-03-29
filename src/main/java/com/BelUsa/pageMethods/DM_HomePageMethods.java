package com.BelUsa.pageMethods;

import com.BelUsa.baseClass.ScenarioContext;
import com.BelUsa.enums.Context;
import com.BelUsa.managers.FileReaderManager;
import com.BelUsa.managers.PageObjectManager;
import com.BelUsa.utility.JSExecuteUtil;
import com.BelUsa.utility.TestUtil;
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
		scenarioContext.setContext(Context.HOMEPAGE_URL,expectedURL);

		//validate correct url is open
		ValidationUtil.validateTestStep("Validate Discount Mug application open Successfully",actualURL, expectedURL);

		if(FileReaderManager.getInstance().getConfigReader().getDiscountMugApplicationURL().equals(FileReaderManager.getInstance().getConfigReader().getDiscountMugApplicationURL())){
			WaitUtil.untilTimeCompleted(5000);

			//closing promo container
			driver.switchTo().activeElement().sendKeys(Keys.chord(Keys.ESCAPE));
			WaitUtil.untilTimeCompleted(5000);

			ValidationUtil.validateTestStep("Promo Container closed",true);

		}
	}
}