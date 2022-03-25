package com.BelUsa.ReportGenerator;

import com.BelUsa.baseClass.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GenerateReport extends BaseClass {
    /******************************************************************************
     ***********************Set-Up / Configurations********************************
     ******************************************************************************/

    // 1) input the Name og Html file from jenkins; example: "Nav01_16_07_2019_04_58_42.html"
//    String htmlReportFileName = "Nav01_28_09_2019_10_41_38.html";
    // 2) Make sure that a blank .xlsx file named "failure_report" is in the /src/main/resources/excelSpreadSheets/ directory
//    String FileName = "failure_report.xlsx";
    // 3) Make sure that the first sheet on the failure_report spreadsheet is called "Sheet1"

    /******************************************************************************
     *************************Set-Up / Configurations*******************************
     ******************************************************************************/
    @Parameters({"platform"})
    @Test
    public String Testname() {
        return getDriver().findElement(By.xpath("(//div[@class='subview-right left'])[1]//h5")).getText().trim();
    }
    public String testCase() {
        String FirstStep = getDriver().findElement(By.xpath("((//div[@class='subview-right left'])[1]//div[@class='test-steps']//td[@class='step-details'])[1]")).getText().trim();
        return FirstStep.substring(FirstStep.indexOf("ID"), FirstStep.indexOf("ID") + 10);
    }
    public String browserType() {
        String FirstStep = getDriver().findElement(By.xpath("((//div[@class='subview-right left'])[1]//div[@class='test-steps']//td[@class='step-details'])[1]")).getText().trim();
        return FirstStep.substring(FirstStep.indexOf("on ") + 3, FirstStep.indexOf("Browser") - 1);
    }
    public String firstFailStep() {
        return getDriver().findElement(By.xpath("((//div[@class='subview-right left'])[1]//div[@class='test-steps']//tr[@status='fail'])[1]//td[@class='step-details']")).getText().trim();
    }
    public String codeError() {
        String value = null;
        try
        {
            value = getDriver().findElement(By.xpath("((//div[@class='subview-right left'])[1]//div[@class='test-steps']//tr[@status='fail'])//td//textarea")).getText().trim();
        }
        catch (Exception e)
        {
            value = getDriver().findElement(By.xpath("((//div[@class='subview-right left'])[1]//div[@class='test-steps']//tr[@status='fail'])[1]//td[@class='step-details']")).getText().trim();
        }
        return value;
    }
}