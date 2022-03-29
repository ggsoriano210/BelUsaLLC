package com.BelUsa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

public class DM_Product {

	public DM_Product(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//********************** Product Detail ****************************
	public final String xpath_ProductNameText = "//section[@class='detail-product-code']//h1";
	@FindBy(xpath= xpath_ProductNameText)
	private WebElement txt_ProductName;

	public final String xpath_StartDesigningNowButton = "//a[contains(text(),'Start Designing Now')]";
	@FindBy(xpath= xpath_StartDesigningNowButton)
	private WebElement btn_StartDesigningNow;

	public final String xpath_SeePricingDetailsButton = "//section[contains(@class,'btn-proceed')]//a[@id='proceed_to_imprint']";
	@FindBy(xpath= xpath_SeePricingDetailsButton)
	private List<WebElement> btn_SeePricingDetails;

	public final String xpath_ImprintColorDropDown = "//select[@id='Imprint_Colors1']";
	@FindBy(xpath= xpath_ImprintColorDropDown)
	private WebElement drpdwn_ImprintColor;

	public final String xpath_AddToCartButton = "//section[contains(@class,'proceed-to-checkout')]//a";
	@FindBy(xpath= xpath_AddToCartButton)
	private WebElement btn_AddToCart;

	//*****************************************************************************************************************
	//*************************************Start of Methods of Home Page***********************************************
	//*****************************************************************************************************************

	//********************** Product Detail ****************************
	public WebElement getProductNameText() {return txt_ProductName;}

	public WebElement getStartDesigningNowButton() {return btn_StartDesigningNow;}

	public List<WebElement> getSeePricingDetailsButton() {return btn_SeePricingDetails;}

	public WebElement getImprintColorDropDown() {return drpdwn_ImprintColor;}

	public WebElement getAddToCartButton() {return btn_AddToCart;}

}