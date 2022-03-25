package com.BelUsa.managers;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.BelUsa.baseClass.BaseClass;
import com.BelUsa.utility.ValidationUtil;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.BelUsa.enums.DriverType;
import com.BelUsa.enums.EnvironmentType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;


public  class DeskTopWebDriverManager extends BaseClass {

	private WebDriver driver;
	private DriverType driverType;
	private EnvironmentType environmentType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private static final String INTERNETEXPLORER_DRIVER_PROPERTY = "webdriver.ie.driver";
	private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";
	private static final String EDGE_DRIVER_PROPERTY = "webdriver.edge.driver";


	public DeskTopWebDriverManager(String driverReq) {
		// Get Environment Type(e.g. Local/Remote)
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment(driverReq);

		//taking driver type from config file
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowserUnderTest(driverReq);
	}

	// **********************************************************************************************
	// Method : getDriver
	// Description: Method is used to get Driver
	// Input Arguments: NA
	// Return: WebDriver
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public synchronized WebDriver getDriver() {
		if (driver == null) {
			return createNewDriver();
		} else {
			return driver;
		}
	}

	// **********************************************************************************************
	// Method : createNewDriver
	// Description: Method is used to create new Driver
	// Input Arguments: NA
	// Return: WebDriver
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	private synchronized WebDriver createNewDriver() {
		switch (environmentType) {
			case LOCAL:
				driver = createLocalDriver();
				break;
			case REMOTE:
				driver = createRemoteDriver();
				break;
		}
		return driver;
	}

	//***********************************************************************************************
	// Method : createRemoteDriver
	// Description: Method is used to create new Remote Driver
	// Input Arguments: NA
	// Return: WebDriver
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	private synchronized WebDriver createRemoteDriver()  {

		//taking reference
		InetAddress localhost = null;
		MutableCapabilities capabilities = null;
		String ipAddress = null;
		String Node = null;

		//getting information about local host
		try {
			localhost = InetAddress.getLocalHost();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//getting IP address of the machine
		ipAddress = (localhost.getHostAddress()).trim();

		//creating node
		Node = "http://" + ipAddress + ":4455/wd/hub";


		switch (driverType) {
			case FIREFOX:
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				capabilities = new DesiredCapabilities(firefoxOptions);
				firefoxOptions.merge(capabilities);
				break;
			case CHROME:
				ChromeOptions chromeOptions = new ChromeOptions();
				capabilities = new DesiredCapabilities(chromeOptions);
				chromeOptions.merge(capabilities);
				break;

			case INTERNETEXPLORER:
				InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
				internetExplorerOptions.destructivelyEnsureCleanSession();
				internetExplorerOptions.introduceFlakinessByIgnoringSecurityDomains();
				capabilities = new DesiredCapabilities(internetExplorerOptions);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				internetExplorerOptions.merge(capabilities);
				break;
			case SAFARI:
				SafariOptions safariOptions = new SafariOptions();
				// safariOptions.setUseTechnologyPreview(true);
				safariOptions.getAutomaticProfiling();
				capabilities = new DesiredCapabilities(safariOptions);
				safariOptions.merge(capabilities);

				break;
			case EDGE:
				EdgeOptions edgeOptions = new EdgeOptions();
				capabilities = new DesiredCapabilities(edgeOptions);
				capabilities.setCapability("resolution", "1920x1080");
				edgeOptions.merge(capabilities);
				break;
		}

		try {
			driver = new RemoteWebDriver(new URL(Node), capabilities);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			String ip = e.getMessage();
			getExtText().info(ip.substring(ip.indexOf("host"), ip.indexOf("ip") + 41));
			ValidationUtil.validateTestStep("Unable to create Remote driver: " + ip.substring(ip.indexOf("host"),ip.indexOf("ip") + 41), false);

		}

		//Maximize window
		if(FileReaderManager.getInstance().getConfigReader().getWindowSize()) {
			driver.manage().window().maximize();
		}

		//implicit wait
		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWait(), TimeUnit.SECONDS);

		//page load time
		driver.manage().timeouts().pageLoadTimeout(FileReaderManager.getInstance().getConfigReader().getPageLoadTimeOut(), TimeUnit.SECONDS);

		// return created driver
		return driver;
	}

	// **********************************************************************************************
	// Method : createLocalDriver
	// Description: Method is used to create new Local Driver
	// Input Arguments: NA
	// Return: WebDriver
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	private synchronized WebDriver createLocalDriver()  {

		//switch according to driver type
		switch (driverType) {
			case FIREFOX:
				System.setProperty(FIREFOX_DRIVER_PROPERTY,
						System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getBrowserDriverPath() + "geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			case CHROME:
				System.setProperty(CHROME_DRIVER_PROPERTY,
						System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getBrowserDriverPath() + "chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case INTERNETEXPLORER:

				DesiredCapabilities capabilities = null;

				capabilities.setBrowserName(BrowserType.IE);
				capabilities.setPlatform(Platform.WINDOWS);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);

				System.setProperty(INTERNETEXPLORER_DRIVER_PROPERTY,
						System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getBrowserDriverPath() + "IEDriverServer.exe");
				driver = new InternetExplorerDriver(capabilities);
				break;
			case SAFARI:
				driver = new SafariDriver();
				break;
			case EDGE:
				System.setProperty(EDGE_DRIVER_PROPERTY,
						System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getBrowserDriverPath() + "MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
				break;

		}

		// Maximize window
		if (FileReaderManager.getInstance().getConfigReader().getWindowSize()) {
			driver.manage().window().maximize();
		}

		// implicit wait
		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWait(), TimeUnit.SECONDS);

		// page load time
		driver.manage().timeouts().pageLoadTimeout(FileReaderManager.getInstance().getConfigReader().getPageLoadTimeOut(), TimeUnit.SECONDS);

		// return created driver
		return driver;
	}


	public synchronized WebDriver createMobileDriver(){

		//taking reference
		InetAddress localhost = null;
		DesiredCapabilities capabilities = null;
		String ipAddress = null;
		String URL_STRING = null;

		//getting information about local host
		try {
			localhost = InetAddress.getLocalHost();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//getting IP address of the machine
		ipAddress = (localhost.getHostAddress()).trim();

		//creating node
		URL_STRING = "http://" + ipAddress + ":4455/wd/hub";

		URL url;
		try{
			url = new URL(URL_STRING);
		}catch (MalformedURLException e){
			e.printStackTrace();
			throw new RuntimeException("");
		}

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2_XL_API_28");
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		caps.setCapability("noReset", false);
		driver = new RemoteWebDriver(url, caps);

		// implicit wait
		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWait(), TimeUnit.SECONDS);

		// page load time
		driver.manage().timeouts().pageLoadTimeout(FileReaderManager.getInstance().getConfigReader().getPageLoadTimeOut(), TimeUnit.SECONDS);

		// return created driver
		return driver;

	}

	// **********************************************************************************************
	// Method : closeDriver
	// Description: Method is used to close window and quit Driver
	// Input Arguments: NA
	// Return: NA
	// Created By :
	// Created On :
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public void closeDriver() {
		// close current window
		driver.close();
//		// Close all window
		driver.quit();
	}
}