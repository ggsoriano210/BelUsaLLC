package com.BelUsa.managers;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;
import com.BelUsa.utility.TestUtil;
import com.aventstack.extentreports.ExtentReports;

public class ExtentReportManager {

	private ExtentHtmlReporter extentHtmlReporter;
	private static ExtentReports extent;


	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	//Create an extent report instance
	public static ExtentReports createInstance() {

		String browser = FileReaderManager.getInstance().getConfigReader().getBrowserType();

		String fileName =  TestUtil.currentDateTimeStringForReport()+ "_"+ browser+ ".html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/reports/"+fileName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().enableTimeline(false);
		htmlReporter.config().setDocumentTitle("QA Automation Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("QA Automation Report");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;
	}

}

