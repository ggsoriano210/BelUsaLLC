package com.BelUsa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Common {

	public Common(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}