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

public class DM_HomePage {
    public DM_HomePage(WebDriver driver) {
        //this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //*******************************************************************
    //********************** LogIn Section ****************************
    //*******************************************************************
    public final String xpath_DMHeaderText = "";
    @FindBy(xpath= xpath_DMHeaderText)
    private WebElement txt_DMHeader;

    //*****************************************************************************************************************
    //*************************************Start of Methods of Home Page***********************************************
    //*****************************************************************************************************************
    public WebElement getDMHeaderText() {return txt_DMHeader;}

}
