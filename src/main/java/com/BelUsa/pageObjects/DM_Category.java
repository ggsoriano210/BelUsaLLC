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

public class DM_Category {

	public DM_Category(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//********************** Shop All Categories section ****************************
	public final String xpath_SelectCategoryButton = "//div[contains(@class,'shop_category_blk position-relative')]//a[contains(@class,'text-primary')]";
	@FindBy(xpath= xpath_SelectCategoryButton)
	private List<WebElement> btn_SelectCategory;

	//********************** Products List ****************************
	public final String xpath_SeeAllButton = "//div[contains(@class,'custom-magnets') or contains(@class,'apparel-landing')]//li//a[contains(text(),'See All')]";
	@FindBy(xpath= xpath_SeeAllButton)
	private List<WebElement> btn_SeeAll;

	public final String xpath_TitleText = "//div[contains(@class,'custom-magnets') or contains(@class,'top-product-single-pic-holder') or contains(@class,'single')][1]//span[1]";
	@FindBy(xpath= xpath_TitleText)
	private List<WebElement> txt_Title;

	//*****************************************************************************************************************
	//*************************************Start of Methods of Home Page***********************************************
	//*****************************************************************************************************************

	//********************** Shop All Categories section ****************************
	public List<WebElement> getSelectCategoryButton() {return btn_SelectCategory;}

	//********************** Products List ****************************
	public List<WebElement> getSeeAllButton() {return btn_SeeAll;}

	public List<WebElement> getTitleText() {return txt_Title;}

}