package com.BelUsa.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSExecuteUtil {
	private static JavascriptExecutor javaScriptExecute = null;
	
	// **********************************************************************************************
	// Method : clickOnElement
	// Description: Method is used to click on element on the Web page
	// Input Arguments: 1. WebDriver  2. WebElement
	// Return: NA
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void clickOnElement(WebDriver driver,WebElement webElement) {
		javaScriptExecute = (JavascriptExecutor)driver;
		javaScriptExecute.executeScript("arguments[0].click();", webElement);
		WaitUtil.untilTimeCompleted(1500);
	}
	
	// **********************************************************************************************
	// Method : scrollDown
	// Description: Method is used to scroll down in the Web page
	// Input Arguments: 1. WebDriver  2. vertical height
	// Return: NA
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void scrollDown(WebDriver driver,String vertical) {
		javaScriptExecute = (JavascriptExecutor)driver;
		javaScriptExecute.executeScript("window.scrollBy(0,"+vertical+")");
	}

	// **********************************************************************************************
	// Method : scrollDownToElementVisible
	// Description: Method is used to scroll down in the Web page to make element visible
	// Input Arguments: 1. WebDriver  2. vertical height
	// Return: NA
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void scrollDownToElementVisible(WebDriver driver,WebElement element) {
		javaScriptExecute = (JavascriptExecutor)driver;

		javaScriptExecute.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	// **********************************************************************************************
	// Method : generateAlertPopup
	// Description: Method is used to generate Alert pop up with provided message of the Web page
	// Input Arguments: 1. WebDriver  2. Message
	// Return: NA
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void generateAlertPopup(WebDriver driver, String message) {
		javaScriptExecute = (JavascriptExecutor)driver;
		javaScriptExecute.executeScript("alert('"+message+"');");
	}
	
	// **********************************************************************************************
	// Method : refreshBrowser
	// Description: Method is used to refresh the web page
	// Input Arguments: 1. WebDriver
	// Return: NA
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void refreshBrowser(WebDriver driver) {
		javaScriptExecute = (JavascriptExecutor)driver;
		javaScriptExecute.executeScript("history.go(0)");
	}
	
	// **********************************************************************************************
	// Method : getElementInnerText
	// Description: Method is used to get all the inner element of the Web page
	// Input Arguments:1. WebDriver 
	// Return: String -> All Inner element
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getElementInnerText(WebDriver driver) {
		javaScriptExecute = (JavascriptExecutor)driver;
		
		return javaScriptExecute.executeScript("return document.documentElement.innerText;").toString();
	}
	
	// **********************************************************************************************
	// Method : getElementTextValue
	// Description: Method is used to get all the inner element of the Web page
	// Input Arguments:1. WebDriver 
	// Return: String -> All Inner element
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getElementTextValue(WebDriver driver, WebElement webElement) {
		javaScriptExecute = (JavascriptExecutor)driver;
		return javaScriptExecute.executeScript("return arguments[0].value", webElement).toString();
	}
	
	// **********************************************************************************************
	// Method : getWebPageDomain
	// Description: Method is used to get Web page Domain
	// Input Arguments:1. WebDriver 
	// Return: String -> Domain
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getWebPageDomain(WebDriver driver) {
		javaScriptExecute = (JavascriptExecutor)driver;
		return javaScriptExecute.executeScript("return document.domain;").toString();
	}
	
	// **********************************************************************************************
	// Method : getWebPageTitle
	// Description: Method is used to get Web page Title
	// Input Arguments:1. WebDriver 
	// Return: String -> Title
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getWebPageTitle(WebDriver driver) {
		javaScriptExecute = (JavascriptExecutor)driver;
		return javaScriptExecute.executeScript("return document.title;").toString();
	}
	
	// **********************************************************************************************
	// Method : getWebPageURL
	// Description: Method is used to get Web page URL
	// Input Arguments:1. WebDriver 
	// Return: String -> URL
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getWebPageURL(WebDriver driver) {
		javaScriptExecute = (JavascriptExecutor)driver;
		return javaScriptExecute.executeScript("return document.URL;").toString();
	}
	
	// **********************************************************************************************
	// Method : highlightElement
	// Description: Method is used to highlight web element
	// Input Arguments:1. WebDriver  2. WebElement
	// Return: NA
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void highlightElement(WebDriver driver, WebElement webElement) {
		javaScriptExecute = (JavascriptExecutor)driver;
		javaScriptExecute.executeScript("arguments[0].style.border='3px dotted blue'", webElement);
	}
	
	// **********************************************************************************************
	// Method : getBackGroundColor
	// Description: Method is used to get background color of webelement
	// Input Arguments:1. WebDriver  2. WebElement
	// Return: NA
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getElementBackGroundColor(WebDriver driver, WebElement webElement) {
		javaScriptExecute = (JavascriptExecutor)driver;
		return javaScriptExecute.executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('background-color');",webElement).toString();
	}
	
	// **********************************************************************************************
	// Method : sendKeys
	// Description: Method is used to enter the value in the text box
	// Input Arguments:1. WebDriver  2. WebElement 3. text
	// Return: NA
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void sendKeys(WebDriver driver, WebElement webElement,String text) {
		javaScriptExecute = (JavascriptExecutor)driver;
		javaScriptExecute.executeScript("arguments[0].value='"+text+"';", webElement);
	}
	
	// **********************************************************************************************
	// Method : getElementAfterProperty
	// Description: Method is used to get After property of WebElement
	// Input Arguments:1. WebDriver  2. WebElement 3. attributeValue
	// Return: NA
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getElementAfterProperty(WebDriver driver, WebElement webElement,String attributeValue) {
		javaScriptExecute = (JavascriptExecutor)driver;
		return javaScriptExecute.executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('"+attributeValue+"');",webElement).toString();
	}

	public static void openNewTabWithGivenURL(WebDriver driver, String navigateURL){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.open('"+navigateURL+"', '_blank');");
	}
	
	// **********************************************************************************************
	// Method : getElementBeforeProperty
	// Description: Method is used to get Before property of WebElement
	// Input Arguments:1. WebDriver  2. WebElement 3. attributeValue
	// Return: NA
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getElementBeforeProperty(WebDriver driver, WebElement webElement,String attributeValue) {
		javaScriptExecute = (JavascriptExecutor)driver;
		
		//update extent report
		return javaScriptExecute.executeScript("return window.getComputedStyle(arguments[0], ':before').getPropertyValue('"+attributeValue+"');",webElement).toString();
	}

	// **********************************************************************************************
	// Method : jsSelect
	// Description: Method is used to to select visible text from dropdown
	// Input Arguments:1. WebDriver  2. WebElement 3. visibleText
	// Return: NA
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void jsSelect(WebDriver driver, WebElement webElement, String visibleText) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("const textToFind = '" + visibleText + "';" +
				"const dd = arguments[0];" +
				"dd.selectedIndex = [...dd.options].findIndex (option => option.text === textToFind);", webElement);
	}

	// **********************************************************************************************
	// Method : isVisibleInViewport
	// Description: Method is used to verify element is is view
	// Input Arguments:1. WebElement
	// Return: NA
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static Boolean isVisibleInViewport(WebDriver driver,WebElement element) {
		return (Boolean)((JavascriptExecutor)driver).executeScript(
				"var elem = arguments[0],                 " +
						"  box = elem.getBoundingClientRect(),    " +
						"  cx = box.left + box.width / 2,         " +
						"  cy = box.top + box.height / 2,         " +
						"  e = document.elementFromPoint(cx, cy); " +
						"for (; e; e = e.parentElement) {         " +
						"  if (e === elem)                        " +
						"    return true;                         " +
						"}                                        " +
						"return false;                            "
				, element);
	}
}
