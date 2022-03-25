package com.BelUsa.utility;

import java.time.Duration;
import java.util.function.Function;

import com.BelUsa.managers.FileReaderManager;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**********************************************************************************************
 * 
 * Class is used to provide the Wait of automation steps.
 * 02.Method will hold the test execution till BellUSA Sites are Loading on Web.
 *  
 ***********************************************************************************************/

public class WaitUtil {

	//**********************************************************************************************
	//Method Name:untilPageLoadComplete
	//Description: Method is used to call untilPageLoadComplete(overloaded) with implicit wait
	//Input Arguments:1.WebDriver->driver instance of browser
	//Return: NA
	//**********************************************************************************************  
    public static void untilPageLoadComplete(WebDriver driver) {
    	//call method with implicit wait
    	untilPageLoadComplete(driver, FileReaderManager.getInstance().getConfigReader().getPageLoadTimeOut());
    }
    
	//**********************************************************************************************
	//Method Name:untilPageLoadComplete
	//Description: Method is used to hold script execution until all page load completed
	//Input Arguments:1.WebDriver->driver instance of browser
    //                2.timeoutInSeconds->wait time in seconds
	//Return: NA
	//**********************************************************************************************  
    public static void untilPageLoadComplete(WebDriver driver, Long timeoutInSeconds) {
    	//create explicit wait instance with default pooling time
    	WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
    	webDriverWait.ignoring(NoSuchElementException.class);

    	//create explicit function to check running page loading on application
        Function<WebDriver,Boolean> waitCondition = new Function<WebDriver,Boolean>(){
             public Boolean apply(WebDriver driver) {
                try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                 }

				 boolean isPageLoaded;

				//if it is in the accounts page, use the ng-progress bar
				 if (driver.getCurrentUrl().contains("discountmugs.com"))
					 isPageLoaded = driver.findElement(By.xpath("//div[contains(@class,'ng-progress-bar')]")).getAttribute("active").equals("false");
				 //else any other flow in the path
				 else
					 isPageLoaded = driver.findElement(By.xpath("//div[@class='overlay']")).getAttribute("style").contains("none");


                 //check the boolean value for jQuery active count is zero
                 if (isPageLoaded) {
                     //return from function JQuery call are completed
                     System.out.println("Document Loaded");
                     return true;
                 }else {
                     System.out.println("Document is loading.....");
                     return false;
                 }

             }
        };

    	//call function to check page loading status
		try{
			webDriverWait.until(waitCondition);
		}catch (Exception e){}
    }

	//**********************************************************************************************
	//Method Name:untilPageURLVisible
	//Description: Method is used to hold script execution until URL appear on application
	//Input Arguments:1.WebDriver->driver instance of browser
    //                2.expectedURL->url to be appear in application
	//Return: NA
	//********************************************************************************************** 
    public static void untilPageURLVisible(WebDriver driver, String expectedURL) {
    	//call method with implicit wait
    	untilPageURLVisible(driver, expectedURL, FileReaderManager.getInstance().getConfigReader().getImplicitWait());
    }
    
	//**********************************************************************************************
	//Method Name:untilPageURLVisible
	//Description: Method is used to hold script execution until URL appear on application
	//Input Arguments:1.WebDriver->driver instance of browser
    //                2.expectedURL->url to be appear in application
    //				  3.timeoutInSeconds->wait time in seconds
	//Return: NA
	//********************************************************************************************** 
    public static void untilPageURLVisible(WebDriver driver, String expectedURL, Long timeoutInSeconds) {
    	//create explicit wait instance
    	WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
    	webDriverWait.ignoring(NoSuchElementException.class);
    	
    	//create explicit function to check running page loading on application
    	Function<WebDriver,Boolean> waitCondition = new Function<WebDriver,Boolean>(){
			@Override
			public Boolean apply(WebDriver driver) {
				//System.out.println(driver.getCurrentUrl());
				//check page loading is complete
				boolean isURLLoadDone = driver.getCurrentUrl().contains(expectedURL);
				//check the boolean value for jQuery active count is zero
				if (!isURLLoadDone) {
					//return from function JQuery call are completed
//					System.out.println("ANGULAR is NOT Ready!");
				}
//				System.out.println("ANGULAR is Ready!");
				//return false 
				return isURLLoadDone;
			}
    	};
    	
    	//call function to check page loading status
		try{
			webDriverWait.until(waitCondition);
		}catch (Exception e){
			System.out.println(e.getMessage());
		} 
    }
    
    
	//**********************************************************************************************
	//Method Name:untilTimeCompleted
	//Description: Method is used to hold script execution until URL appear on application
	//Input Arguments:1.timeMilliSecond->driver instance of browser
	//Return: NA
	//********************************************************************************************** 
    public static void untilTimeCompleted(int timeMilliSecond) {
    	try {
			Thread.sleep(timeMilliSecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	//**********************************************************************************************
	//Method Name:untilElementIsClickable
	//Description: Method is used to hold script execution till element to be clickable
	//Input Arguments:1.driver->driver instance of browser
	//				  2.element->clickable element
	//Return: NA
	//**********************************************************************************************
    public static void untilElementIsClickable(WebDriver driver, WebElement element){
    	//call explicit wait method to wait for element to be clickable
		untilElementIsClickable( driver, element, FileReaderManager.getInstance().getConfigReader().getExplicitWait());
	}

	//**********************************************************************************************
	//Method Name:untilElementIsClickable
	//Description: Method is used to hold script execution till element to be clickable
	//Input Arguments:1.driver->driver instance of browser
	//				  2.element->clickable element
	//				  3.timeOut->timeout to wait for element
	//Return: NA
	//**********************************************************************************************
	public static void untilElementIsClickable(WebDriver driver, WebElement element, long timeOut){
    	//create explicit wait object
		WebDriverWait explictWait = new WebDriverWait(driver,timeOut);

		//wait for element to be clickable
		explictWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	//**********************************************************************************************
	//Method Name:untilElementIsClickable
	//Description: Method is used to hold script execution till element to be clickable
	//Input Arguments:1.driver->driver instance of browser
	//				  2.By->clickable element
	//Return: NA
	//**********************************************************************************************
	public static void untilElementIsClickable(WebDriver driver, By elementIdentifier){
		//call explicit wait method to wait for element to be clickable
		untilElementIsClickable( driver, driver.findElement(elementIdentifier), FileReaderManager.getInstance().getConfigReader().getExplicitWait());
	}

	//**********************************************************************************************
	//Method Name:untilElementIsClickable
	//Description: Method is used to hold script execution till element to be clickable
	//Input Arguments:1.driver->driver instance of browser
	//				  2.By->clickable element
	//				  3.timeOut->timeout to wait for element
	//Return: NA
	//**********************************************************************************************
	public static void untilElementIsClickable(WebDriver driver, By elementIdentifier, long timeOut){
		//create explicit wait object
		WebDriverWait explicitWait = new WebDriverWait(driver,timeOut);

		//wait for element to be clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(elementIdentifier)));

		//create explicit wait instance with default pooling time
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
		webDriverWait.withTimeout(Duration.ofSeconds(timeOut));
		webDriverWait.ignoring(NoSuchElementException.class);

		//create explicit function to check running jQuery on application
		Function<WebDriver,Boolean> waitCondition = new Function<WebDriver,Boolean>(){
			@Override
			public Boolean apply(WebDriver driver) {
				//check jQuery active count is zero
				boolean isJqueryCallDone = driver.findElements(elementIdentifier).size()>0;
				//check the boolean value for jQuery active count is zero
				if (!isJqueryCallDone) {
					//return from function JQuery call are completed
					System.out.println("JQuery call is in Progress");
				}
				System.out.println("JQuery call is Called");
				//return false
				return isJqueryCallDone;
			}
		};

		//call function to check jQuery active count on application
		try{
			webDriverWait.until(waitCondition);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void waitforWindowsElement(WindowsDriver driver, String IDType, String elementLocator, int timeOut) {
		boolean iterate = true;
		WindowsElement windowElement = null;


		for(int counterValue=0;counterValue<=timeOut;counterValue++) {
			try {
				switch (IDType) {
					case "id":
						windowElement = (WindowsElement) driver.findElementsByAccessibilityId(elementLocator);
						break;
					case "xpath":
						windowElement = (WindowsElement) driver.findElementsByXPath(elementLocator);
						break;
					case "name":
						windowElement = (WindowsElement) driver.findElementsByName(elementLocator);
				}
			} catch (Exception ex) {
				//Do Nothing
			}
			System.out.println(counterValue);

			if(windowElement!=null){
				System.out.println(counterValue);
				return;
			}
		}
	}
}
