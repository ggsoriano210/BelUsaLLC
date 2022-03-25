package com.BelUsa.dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.BelUsa.enums.DriverType;
import com.BelUsa.enums.EnvironmentType;

public class ConfigFileReader {

	private Properties properties;
	private final String ConfigFilePath = "/src/main/resources/configs/Configuration.properties";

	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + ConfigFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + ConfigFilePath);
		}
	}

	//Get current Browser from configuration property file
	public String getBrowserType() {
		String applicationURL = properties.getProperty("browser");
		if(applicationURL != null) {
			return applicationURL;
		}else {
			throw new RuntimeException("browser type is not set in the configuration file.");
		}
	}

	//Get Discount Mug Application URL from configuration property file
	public String getDiscountMugApplicationURL() {
		String applicationURL = properties.getProperty("urlDM");
		if(applicationURL != null) {
			return applicationURL;
		}else {
			throw new RuntimeException("url not specified in the Configuration.properties file");
		}
	}

	//Get BelPromo Application URL from configuration property file
	public String getBelPromoApplicationURL() {
		String applicationURL = properties.getProperty("urlBP");
		if(applicationURL != null) {
			return applicationURL;
		}else {
			throw new RuntimeException("url not specified in the Configuration.properties file");
		}
	}

	public DriverType getBrowserUnderTest(String browserParallel) {
		String browserUnderTest;
		if(browserParallel.equalsIgnoreCase("NA")) {
			browserUnderTest = properties.getProperty("browser");
		}else {
			browserUnderTest = browserParallel;
		}

		if(browserUnderTest == null || browserUnderTest.equalsIgnoreCase("InternetExplorer")) {
			return DriverType.INTERNETEXPLORER;
		}else if(browserUnderTest.equalsIgnoreCase("Chrome")) {
			return DriverType.CHROME;
		}else if(browserUnderTest.equalsIgnoreCase("Firefox")) {
			return DriverType.FIREFOX;
		}else if(browserUnderTest.equalsIgnoreCase("Safari")) {
			return DriverType.SAFARI;
		}else if (browserUnderTest.equalsIgnoreCase("Edge")){
			return DriverType.EDGE;
		}else if(browserUnderTest.equalsIgnoreCase("Android")){
			return DriverType.ANDROID;
		}else if(browserUnderTest.equalsIgnoreCase("Ios")){
			return DriverType.IOS;
		}else {
			throw new RuntimeException("Browser under test is not specified in the Configuration.properties file");
		}
	}

	public String getUserName() {
		String UserName = properties.getProperty("username");
		if(UserName != null) {
			return UserName;
		}else {
			throw new RuntimeException("Application UserName is not specified in the Configuration.properties file");
		}
	}

	public String getPassword() {
		String Password = properties.getProperty("password");
		if(Password != null) {
			return Password;
		}else {
			throw new RuntimeException("Application Password is not specified in the Configuration.properties file");
		}
	}

	public Long getImplicitWait() {
		String implicitWait = properties.getProperty("implicitWait");
		if(implicitWait != null) {
			try {
				return Long.parseLong(implicitWait);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitWait + " in to Long");
			}
		}else {
			throw new RuntimeException("Implicit Wait is not specified in the Configuration.properties file");
		}
	}

	public Long getExplicitWait() {
		String explicitWait = properties.getProperty("explicitWait");
		if(explicitWait != null) {
			try {
				return Long.parseLong(explicitWait);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + explicitWait + " in to Long");
			}
		}else {
			throw new RuntimeException("Explicit Wait is not specified in the Configuration.properties file");
		}
	}

	public Long getPageLoadTimeOut() {
		String pageLoadTimeOut = properties.getProperty("pageLoadTimeOut");
		if(pageLoadTimeOut != null) {
			try {
				return Long.parseLong(pageLoadTimeOut);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + pageLoadTimeOut + " in to Long");
			}
		}else {
			throw new RuntimeException("Page Load TimeOut is not specified in the Configuration.properties file");
		}
	}

	public EnvironmentType getEnvironment(String browserParallel) {
		String environmentType = properties.getProperty("environment");
		if(environmentType.equalsIgnoreCase("local")) {
			return EnvironmentType.LOCAL;
		}else if(environmentType.equalsIgnoreCase("remote")) {
			return EnvironmentType.REMOTE;
		}else {
			throw new RuntimeException("Environment Type is not specified in the Configuration.properties file");
		}
	}
	public Boolean getWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if(windowSize != null) {
			return Boolean.valueOf(windowSize);
		}else {
			return true;
		}
	}

	public String getReportExtentPath() {
		String reportExtentPath = properties.getProperty("reportExtentPath");
		if(reportExtentPath != null) {
			return reportExtentPath;
		}else {
			throw new RuntimeException("Extent Report xml file path is not specified in the Configuration.properties file");
		}
	}

	public String getBrowserDriverPath() {
		String browserDriverPath = properties.getProperty("browserDriverPath");
		if(browserDriverPath != null) {
			return browserDriverPath;
		}else {
			throw new RuntimeException("Browser Drivers Path is not specified in the Configuration.properties file");
		}
	}

	public String getExcelDataPath() {
		String reportExcelDataPath = properties.getProperty("excelDataPath");
		if(reportExcelDataPath != null) {
			return reportExcelDataPath;
		}else {
			throw new RuntimeException("Base Line Report xls file path is not specified in the Configuration.properties file");
		}
	}

	public String getTestDataResourcePath(){
		String testDataResourcePath = properties.getProperty("testDataResourcePath");
		if(testDataResourcePath!= null) {
			return testDataResourcePath;
		}else {
			throw new RuntimeException("Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");
		}
	}
}
