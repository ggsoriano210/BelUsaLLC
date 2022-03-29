package com.BelUsa.pageObjects;

/*
ifr      -  iframe
btn      -  Button
chkbx    -  CheckBox
chklst   -  CheckBoxList
drpdwn   -  DropDownList
grdvew   -  GridView
hyrlnk   -  Hyperlink
img      -  Image
imgbtn   -  ImageButton
lbl      -  Label
lnkbtn   -  LinkButton
lnk      -  Link
lstbx    -  ListBox
lit      -  Literal
pnl      -  Panel
ph       -  PlaceHolder
rdbtn    -  RadioButton
rdbtnlst -  RadioButtonList
txtbx       Textbox
txt      -  Text
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DM_YourCart {

	public DM_YourCart(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//********************** Cart Header ****************************
	public final String xpath_YourCartTitleText = "//div[@id='cartFlyOut']//div[@class='cartFlyOutHeader']//span[contains(@class,'Title')]";
	@FindBy(xpath= xpath_YourCartTitleText)
	private WebElement txt_YourCartTitle;

	public final String xpath_YourCartCloseButton = "//div[@id='cartFlyOut']//div[@class='cartFlyOutHeader']//span[contains(@class,'close')]";
	@FindBy(xpath= xpath_YourCartCloseButton)
	private WebElement btn_YourCartClose;

	//********************** Products List ****************************
	public final String xpath_ProductNameText = "//div[@id='cartFlyOut']//li//div[contains(@class,'ItemName')]";
	@FindBy(xpath= xpath_ProductNameText)
	private List<WebElement> txt_ProductName;

	public final String xpath_ProductNumberText = "//div[@id='cartFlyOut']//li//div[contains(@class,'ItemNumber')]";
	@FindBy(xpath= xpath_ProductNumberText)
	private List<WebElement> txt_ProductNumber;

	public final String xpath_ProductQtyText = "//div[@id='cartFlyOut']//li//section[@class='priceSection']//input[contains(@class,'ItemQty')]";
	@FindBy(xpath= xpath_ProductQtyText)
	private List<WebElement> txt_ProductQty;

	public final String xpath_ProductPriceText = "//div[@id='cartFlyOut']//li//section[@class='priceSection']//span[contains(@class,'ItemRegularPrice')]";
	@FindBy(xpath= xpath_ProductPriceText)
	private List<WebElement> txt_ProductPrice;

	public final String xpath_DeleteProductButton = "//div[@id='cartFlyOut']//li//a[contains(@class,'DeleteItem')]";
	@FindBy(xpath= xpath_DeleteProductButton)
	private List<WebElement> btn_DeleteProduct;

	//********************** Footer Section ****************************
	public final String xpath_CheckoutButton = "//div[@id='cartFlyOut']//div[contains(@class,'Footer')]//button[contains(@class,'Checkout')]";
	@FindBy(xpath= xpath_CheckoutButton)
	private WebElement btn_Checkout;

	public final String xpath_ContinueShoppingButton = "//div[@id='cartFlyOut']//div[contains(@class,'Footer')]//button[contains(@class,'Continue')]";
	@FindBy(xpath= xpath_ContinueShoppingButton)
	private WebElement btn_ContinueShopping;


	//*****************************************************************************************************************
	//*************************************Start of Methods of Home Page***********************************************
	//*****************************************************************************************************************

	//********************** Cart Header ****************************
	public WebElement getYourCartTitleText() {return txt_YourCartTitle;}

	public WebElement getYourCartCloseButton() {return btn_YourCartClose;}

	//********************** Products List ****************************
	public List<WebElement> getProductNameText() {return txt_ProductName;}

	public List<WebElement> getProductNumberText() {return txt_ProductNumber;}

	public List<WebElement> getProductQtyText() {return txt_ProductQty;}

	public List<WebElement> getProductPriceText() {return txt_ProductPrice;}

	public List<WebElement> getDeleteProductButton() {return btn_DeleteProduct;}

	//********************** Footer Section ****************************
	public WebElement getCheckoutButton() {return btn_Checkout;}

	public WebElement getContinueShoppingButton() {return btn_ContinueShopping;}

}