package com.BelUsa.managers;

import com.BelUsa.pageObjects.*;
import org.openqa.selenium.WebDriver;


public class PageObjectManager {
	
	//declare class variable used for PageObjectManager class
	private WebDriver driver;
	private DM_HomePage dmHomePage;
	private DM_Category dmCategory;
	private DM_CustomProductPage dmCustomProductPage;
	private DM_Product dmProduct;
	private DM_YourCart dmYourCart;

	//declare constructor to initialize selenium driver
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public DM_HomePage getDiscountMugHomePage() {
		if(dmHomePage == null) {
			return dmHomePage = new DM_HomePage(driver);
		}else {
			return dmHomePage;
		}
	}

	public DM_Category getDiscountMugCategoryPage() {
		if(dmCategory == null) {
			return dmCategory = new DM_Category(driver);
		}else {
			return dmCategory;
		}
	}

	public DM_CustomProductPage getDiscountMugCustomProductsPage() {
		if(dmCustomProductPage == null) {
			return dmCustomProductPage = new DM_CustomProductPage(driver);
		}else {
			return dmCustomProductPage;
		}
	}

	public DM_Product getDiscountMugProductPage() {
		if(dmProduct == null) {
			return dmProduct = new DM_Product(driver);
		}else {
			return dmProduct;
		}
	}

	public DM_YourCart getDiscountMugYourCart() {
		if(dmYourCart == null) {
			return dmYourCart = new DM_YourCart(driver);
		}else {
			return dmYourCart;
		}
	}
}
