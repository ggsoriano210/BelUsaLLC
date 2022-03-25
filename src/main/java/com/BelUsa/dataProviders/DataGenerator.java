package com.BelUsa.dataProviders;

import com.BelUsa.utility.ExcelUtil;
import org.testng.*;
import org.testng.annotations.DataProvider;

import java.util.*;

public class DataGenerator {

        String FileName;
        String sheetName;
        ExcelUtil testData;

    @DataProvider(name="testdataprodivder", parallel=true)
    public Object[][] ProdExection(ITestNGMethod testContext) {

            if(testContext.getMethodName().equalsIgnoreCase("ProductionCompleteBooking")){
            FileName = "PRODUCTION.xlsx";
            sheetName = "ProductionCompleteBooking";
            testData = new ExcelUtil(FileName);
        }

        else{
            System.out.println("No Sheet found");
        }

        int rows = testData.getRowCount(sheetName);
        int cols = testData.getColumnCount(sheetName);

        Object[][] data = new Object[rows - 1][1];

        Hashtable<String,String> table;

        for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

            table = new Hashtable<>();

            for (int colNum = 0; colNum < cols; colNum++) {

                // data[0][0]
                table.put(testData.getCellData(sheetName, colNum, 1), testData.getCellData(sheetName, colNum, rowNum));
                data[rowNum - 2][0] = table;
            }

        }

        return data;
    }
}
