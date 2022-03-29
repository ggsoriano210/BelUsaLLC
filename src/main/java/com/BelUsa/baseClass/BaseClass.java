package com.BelUsa.baseClass;


import com.aventstack.extentreports.*;
import com.BelUsa.enums.*;
import com.BelUsa.managers.*;
import com.BelUsa.utility.*;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.*;

import java.util.*;


public class BaseClass {
	//declare class used in BaseClass
	public DeskTopWebDriverManager deskTopWebDriverManager;
	public PageObjectManager pageObjectManager;
	public PageMethodManager pageMethodManager;
	public ScenarioContext scenarioContext;
	public ExcelSpreadSheetManager excelSpreadSheetManager;

	private ExtentReports extent;
	private ActiveNodeDeterminer activeNodeDeterminer;

	private static final Logger logger = LogManager.getLogger(ITestListener.class);

	//declare ThreadLocal to maintain multiple threads
	public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
	public static ThreadLocal<ExtentTest> extReport  = new ThreadLocal<ExtentTest>();
	/*
	 * Declare getter and setter for web driver
	 */
	public WebDriver getDriver() {
		return threadDriver.get();
	}

	public void setDriver(WebDriver driver) {
		threadDriver.set(driver);
	}

	/*
	 * Declare getter and setter for Extent Report
	 */
	public synchronized ExtentTest getExtText() {
		return extReport.get();
	}

	public synchronized void setExtTest(ExtentTest et) {
		extReport.set(et);
	}

	// **********************************************************************************************
	// Method : openBrowser
	// Description: Method is used to open Browser on DeskTop Platform
	// Input Arguments: NA
	// Return: WebDriver
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	@Step("Browser was successfully opened")
	public synchronized void openBrowser(String platform) {
		activeNodeDeterminer = new ActiveNodeDeterminer("localhost", 4455);

		//Create Instance of WebDriver Manager
		deskTopWebDriverManager = new DeskTopWebDriverManager(platform);

		//Get Driver instance from WebDriver manager Class
		setDriver(deskTopWebDriverManager.getDriver());

		//initialize page objects
		pageObjectManager = new PageObjectManager(getDriver());

		//initialize page methods
		pageMethodManager = new PageMethodManager(getDriver(),pageObjectManager,scenarioContext);

		excelSpreadSheetManager = new ExcelSpreadSheetManager();

		String classPath = getClass().getName();
		int classIndex = classPath.lastIndexOf(".") + 1;
		String ClassName = classPath.substring(classIndex);
		scenarioContext.setContext(Context.CLASS_NAME,ClassName);

		if(platform.equalsIgnoreCase("NA")){
			//set browser under test
			scenarioContext.setContext(Context.HOMEPAGE_BROWSER,FileReaderManager.getInstance().getConfigReader().getBrowserUnderTest(platform));
			ValidationUtil.logTestInformation("Starting Test Case " + ClassName + " on " + scenarioContext.getContext(Context.HOMEPAGE_BROWSER) + " Browser");
		}else{
			//set browser under test
			scenarioContext.setContext(Context.HOMEPAGE_BROWSER,platform);
			ValidationUtil.logTestInformation("Starting Test Case " + ClassName + " on " + scenarioContext.getContext(Context.HOMEPAGE_BROWSER) + " Browser");

//			getExtText().assignCategory(activeNodeDeterminer.getNodeInfoForSession(((RemoteWebDriver)getDriver()).getSessionId()).getNodeIp());
//			getExtText().info("************** Test Case Running on "+activeNodeDeterminer.getNodeInfoForSession(((RemoteWebDriver)getDriver()).getSessionId()).getNodeIp() + " **************");
//			getExtText().info(platform);
		}
	}

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}


	@BeforeSuite(alwaysRun = true)
	public synchronized void suiteSetup(ITestContext context) {
		String suiteName, appURL = null;
		//get the suite name from xml file
		suiteName = context.getCurrentXmlTest().getSuite().getName();
		System.out.println("This is the start of Suite " + suiteName);
		extent =  ExtentReportManager.getInstance();
	}

	@BeforeMethod(alwaysRun = true)
	public synchronized void setup(ITestResult result, ITestContext context) {
		System.out.println("Start of Before Method");
		//get the suite name from xml file
		String suiteName = context.getCurrentXmlTest().getSuite().getName();

		extent =  ExtentReportManager.getInstance();
		setExtTest(extent.createTest(result.getMethod().getMethodName()));
		getExtText().assignCategory(suiteName);

		//create global variable hashmap
		scenarioContext = new ScenarioContext();
	}

	public String getComputerName()
	{
		Map<String, String> env = System.getenv();
		if (env.containsKey("COMPUTERNAME"))
			return env.get("COMPUTERNAME");
		else if (env.containsKey("HOSTNAME"))
			return env.get("HOSTNAME");
		else
			return "Unknown Computer";
	}

	@AfterMethod(alwaysRun = true)
	public synchronized void tearDown(ITestResult result,ITestContext context) {
		//declare variable used in method
		String suiteName, appURL = null;
		System.out.println("Start of After Method");
		if(getDriver() != null ) {
			getDriver().switchTo().defaultContent();
		}

		if(result.getStatus()==ITestResult.SUCCESS){
			getExtText().pass(getTestMethodName(result).toUpperCase() +  " is Passed");
			System.out.println(result.getTestClass().getName() + " run finished: " + getTestMethodName(result).toUpperCase() +  " Passed");
		}else if(result.getStatus()==ITestResult.FAILURE){
			System.out.println(result.getTestClass().getName() + " run finished: " + getTestMethodName(result).toUpperCase() +  " Failed");

			if(getDriver() != null ) {
				//add failed page on report as a category
				String failedPage = getDriver().getTitle();
				getExtText().assignCategory(failedPage);
				failTest(result);
			}

			if(!(result.getThrowable().getClass() == new AssertionError().getClass()))
			{
				try
				{
					String pngScreenshot = TestUtil.takeScreenshotAtEndOfTest(getDriver());
					getExtText().fail(result.getThrowable() , MediaEntityBuilder.createScreenCaptureFromPath(pngScreenshot).build());
					failTest(result);
				}
				catch (Exception e)
				{
					getExtText().fail(result.getThrowable());
				}
			}
			else {
				try {
					if (getDriver() != null) {
						failTest(result);
						String pngScreenshot = TestUtil.takeScreenshotAtEndOfTest(getDriver());
						getExtText().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(pngScreenshot).build());
					}
				} catch (Exception e) {
					getExtText().fail(result.getThrowable());
				}
			}

		}else if(result.getStatus()==ITestResult.SKIP){
			getExtText().skip(getTestMethodName(result).toUpperCase() +  " is Skipped" + result.getThrowable());
			getExtText().skip(result.getThrowable());
		}


		if(getDriver()!=null){
			//close current window
			getDriver().quit();
		}

		threadDriver.set(null);

	}

	private void failTest(ITestResult iTestResult) {
		logger.error(iTestResult.getTestClass().getName());
		logger.error(iTestResult.getThrowable());

		// Allure ScreenShot and performedActions log
		if (getDriver() != null) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenShotOnFailure();
		}
	}

	@Attachment
	public byte[] saveScreenShotOnFailure() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "{0}", type = "text/plain")
	public static String performedActions(String message) {
		return message;
	}


	@AfterClass(alwaysRun = true)
	public synchronized void cleanupTest(ITestContext context)
	{
		String classPath = getClass().getName();
		int classIndex = classPath.lastIndexOf(".") + 1;
		String ClassName = classPath.substring(classIndex);

		System.out.println("Inside the @AfterClass method");
		if(getDriver()!=null){
			System.out.println("Driver still hasn't torn down");
			System.out.println(ClassName);
			getDriver().quit();
		}
		extent.flush();
	}

	@AfterSuite(alwaysRun = true)
	public synchronized void cleanupSuite(ITestContext context) {
		String suiteName, appURL = null;
		System.out.println("Start of After Suite");
		//get the suite name from xml file
		suiteName = context.getCurrentXmlTest().getSuite().getName();
		System.out.println(suiteName + " Completed");
		extent.flush();
//		EmailUtil.sendEmailWithAttachReport(TestUtil.getLatestReportFile(),suiteName);
	}

}
