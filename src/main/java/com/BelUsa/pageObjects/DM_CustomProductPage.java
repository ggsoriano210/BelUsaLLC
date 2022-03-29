package com.BelUsa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DM_CustomProductPage {

	public DM_CustomProductPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//********************** Products List ****************************
	public final String xpath_ViewProductButton = "//div[@class='prod-box ']//a[1]//img";
	@FindBy(xpath= xpath_ViewProductButton)
	private List<WebElement> btn_ViewProduct;

	public final String xpath_ProductNameText = "//div[@class='prod-box ']//div[@class='descripton-prod']";
	@FindBy(xpath= xpath_ProductNameText)
	private List<WebElement> txt_ProductName;

	//*****************************************************************************************************************
	//*************************************Start of Methods of Home Page***********************************************
	//*****************************************************************************************************************

	//********************** Products List ****************************
	public List<WebElement> getViewDetailsButton() {return btn_ViewProduct;}

	public List<WebElement> getProductNameText() {return txt_ProductName;}

}