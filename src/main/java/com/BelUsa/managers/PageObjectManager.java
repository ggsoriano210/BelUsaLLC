package com.BelUsa.managers;

import com.BelUsa.pageObjects.*;
import org.openqa.selenium.WebDriver;


public class PageObjectManager {
	
	//declare class variable used for PageObjectManager class
	private WebDriver driver;
	private Common common;
	private DM_HomePage dmHomePage;

	//declare constructor to initialize selenium driver
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public Common getCommon() {
		if(common == null) {
			return common = new Common(driver);
		}else {
			return common;
		}
	}

	public DM_HomePage getDiscountMugHomePage() {
		if(dmHomePage == null) {
			return dmHomePage = new DM_HomePage(driver);
		}else {
			return dmHomePage;
		}
	}

}
