package com.BelUsa.managers;

import com.BelUsa.baseClass.ScenarioContext;
import com.BelUsa.pageMethods.*;
import org.openqa.selenium.WebDriver;

public class PageMethodManager {
	
	private WebDriver driver;
	private PageObjectManager pageObjectManager;
	private PageMethodManager pageMethodManager;
	private ScenarioContext scenarioContext;
	private DM_HomePageMethods homePageMethods;
	private CommonPageMethods commonPageMethods;
	
	public PageMethodManager(WebDriver driver,PageObjectManager pageObjectManager,ScenarioContext scenarioContext) {
		this.driver=driver;
		this.pageObjectManager = pageObjectManager;
		this.scenarioContext = scenarioContext;
	}

	public DM_HomePageMethods getHomePageMethods() {
		if(homePageMethods == null) {
			return homePageMethods = new DM_HomePageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return homePageMethods;
		}
	}

	public CommonPageMethods getCommonPageMethods() {
		if(commonPageMethods == null) {
			return commonPageMethods = new CommonPageMethods(driver, pageObjectManager, pageMethodManager, scenarioContext);
		}else {
			return commonPageMethods;
		}
	}

}
