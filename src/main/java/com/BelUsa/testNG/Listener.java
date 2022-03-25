package com.BelUsa.testNG;


import java.util.Iterator;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import com.BelUsa.baseClass.BaseClass;

public class Listener extends BaseClass implements ITestListener, ISuiteListener, IInvokedMethodListener{

	private static final Logger logger = LogManager.getLogger(ITestListener.class);

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}


	@Attachment
	public byte[] saveScreenShotOnFailure() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "{0}", type = "text/plain")
	public static String performedActions(String message) {
		return message;
	}


	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		//setExtTest(rep.startTest(result.getName().toUpperCase()));
		//startTest(result.getMethod().getMethodName(),getTestMethodName(result) + " Test Case Started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		/*getExtText().log(LogStatus.PASS, result.getName().toUpperCase() + " PASS");
		rep.endTest(getExtText());
		rep.flush();*/

	}

	@Override
	public void onTestFailure(ITestResult result) {
		failTest(result);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//getExtText().log(LogStatus.SKIP, result.getName().toUpperCase() + "SKIP with exception " + result.getThrowable());
		//rep.endTest(getExtText());
		//rep.flush();
		logger.error(result.getThrowable());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
        Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();
        while (skippedTestCases.hasNext()) {
            ITestResult skippedTestCase = skippedTestCases.next();
            ITestNGMethod method = skippedTestCase.getMethod();
            if (context.getSkippedTests().getResults(method).size() > 0) {
                System.out.println("Removing:" + skippedTestCase.getTestClass().toString());
                skippedTestCases.remove();
            }
        }
		
	}

	private void failTest(ITestResult iTestResult) {
		logger.error(iTestResult.getTestClass().getName());
		logger.error(iTestResult.getThrowable());

		// Allure ScreenShot and performedActions log
		if (getDriver() != null) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenShotOnFailure();
		}
		performedActions(getTestMethodName(iTestResult) + " failed and screenshot taken!");
	}

}
