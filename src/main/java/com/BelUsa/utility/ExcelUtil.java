package com.BelUsa.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import com.BelUsa.managers.FileReaderManager;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**********************************************************************************************
 * 
 * Class is used to perform methos to interact with the Microsoft excel sheet. 
 * Currently this class is not used into framework as we are planning to 
 * make platform independent framework.
 * 
 ***********************************************************************************************/

public class ExcelUtil {
	
	public  String excelFileName;
	public  String path;
	public  FileInputStream fis = null;
	public  File file = null;
	public  FileOutputStream fileOut =null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row   =null;
	private XSSFCell cell = null;

	/**
	 * @param excelFileName
	 */
public ExcelUtil(String excelFileName) {
		
		this.excelFileName=excelFileName;
		
		try {
			
			path = System.getProperty("user.dir") + "/src/main/resources/excelSpreadSheets/" + excelFileName;
			
			file = new File(path);
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public boolean addColumnBaseLine(String sheetName, String colName) {
		try {
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);
		int index = workbook.getSheetIndex(sheetName);
			
		if(index==-1) {
			return false;
		}else {
			//create cell style 	
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			
			for(int i=1;i<row.getLastCellNum()+3;i++){
				//System.out.println(row.getCell(i).getStringCellValue().trim());
				if(row.getCell(i).getStringCellValue().trim().equals(colName)) {
					break;
				}else if(row.getCell(i).getStringCellValue().trim().isEmpty()) {
					row.getCell(i).setCellValue(colName);
					row.getCell(i).setCellStyle(style);
					break;
				}	
			}
	        fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		    fileOut.close();
		    return true;
		}	  
		
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public boolean addRowBaseLine(String sheetName, String rowName) {
		
		try {
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);
		int index = workbook.getSheetIndex(sheetName);
			
		if(index==-1) {
			return false;
		}else {
			//create cell style 	
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			workbook.setActiveSheet(index);
			sheet = workbook.getSheetAt(index);
			
			row = sheet.getRow(0);
			
			/*for(int i=0;i<row.getLastCellNum();i++){
				System.out.println(row.getCell(i).getStringCellValue().trim());
			}*/
			
			int startCounter = sheet.getFirstRowNum()+1;
			int endCounter = sheet.getLastRowNum()+2;
			
			for(int i=startCounter;i<=endCounter;i++) {
				//System.out.println(sheet.getRow(i).getCell(0));
				
				//System.out.println(sheet.getRow(i).getCell(0).getStringCellValue().trim());
				if(sheet.getRow(i).getCell(0).getStringCellValue().trim().equals(rowName)) {
					break;
				}else if(sheet.getRow(i).getCell(0).getStringCellValue().trim().isEmpty()) {
					sheet.getRow(i).getCell(0).setCellValue(rowName);
					sheet.getRow(i).getCell(0).setCellStyle(style);
					break;
				}
			}
	        fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		    fileOut.close();
		    return true;
		}	  
		
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param sheetName
	 * @return
	 */
	public int getRowCount(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
		sheet = workbook.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
		}
	}

	/**
	 * @param sheetName
	 * @param colName
	 * @param rowNum
	 * @return
	 */
	// returns the data from a cell
	public String getCellData(String sheetName,String colName,int rowNum){
		try{
			if(rowNum <=0)
				return "";
		
		int index = workbook.getSheetIndex(sheetName);
		int col_Num=-1;
		if(index==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
//			System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				col_Num=i;
		}
		if(col_Num==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(col_Num);
		
		if(cell==null)
			return "";
		//System.out.println(cell.getCellType());
		if(cell.getCellType()==Cell.CELL_TYPE_STRING)
			  return cell.getStringCellValue();
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
			  
			  String cellText  = String.valueOf(cell.getNumericCellValue());
			  if (HSSFDateUtil.isCellDateFormatted(cell)) {
		           // format in form of M/D/YY
				  double d = cell.getNumericCellValue();

				  Calendar cal =Calendar.getInstance();
				  cal.setTime(HSSFDateUtil.getJavaDate(d));
		            cellText =
		             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
		           cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
		                      cal.get(Calendar.MONTH)+1 + "/" + 
		                      cellText;
		           
		           //System.out.println(cellText);

			  }
			  return cellText;
		  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		      return ""; 
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
		
		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
	}


	/**
	 * @param sheetName
	 * @param colNum
	 * @param rowNum
	 * @return
	 */
	// returns the data from a cell
	public String getCellData(String sheetName,int colNum,int rowNum){
		try{
			if(rowNum <=0)
				return "";
		
		int index = workbook.getSheetIndex(sheetName);

		if(index==-1)
			return "";
		
	
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(colNum);
		if(cell==null)
			return "";
		
	  if(cell.getCellType()==Cell.CELL_TYPE_STRING)
		  return cell.getStringCellValue();
	  else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
		  
		  String cellText  = String.valueOf(cell.getNumericCellValue());
		  if (HSSFDateUtil.isCellDateFormatted(cell)) {
	           // format in form of M/D/YY
			  double d = cell.getNumericCellValue();

			  Calendar cal =Calendar.getInstance();
			  cal.setTime(HSSFDateUtil.getJavaDate(d));
	            cellText =
	             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
	           cellText = cal.get(Calendar.MONTH)+1 + "/" +
	                      cal.get(Calendar.DAY_OF_MONTH) + "/" +
	                      cellText;
	           
	          // System.out.println(cellText);

	         }
		  return cellText;
	  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
	      return "";
	  else 
		  return String.valueOf(cell.getBooleanCellValue());
		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
		}
	}


	/**
	 * @param sheetName
	 * @param colName
	 * @param rowName
	 * @param data
	 * @return
	 */
	public boolean setCellData(String sheetName,String colName,String rowName, String data) {
		
		int rowNum = 0;
		int colNum = 0;
		
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			
			int index = workbook.getSheetIndex(sheetName);
			
			if(index == -1) {
				return false;
			}
			
			sheet = workbook.getSheetAt(index);
			
			int endCounter = sheet.getLastRowNum();
			
			for(int i=1;i<sheet.getLastRowNum();i++){
				if(sheet.getRow(i).getCell(0).getStringCellValue().trim().equals(rowName)) {
					rowNum = i;
					break;
				}
			}
			
			row = sheet.getRow(0);

			endCounter = row.getLastCellNum();
			
			for(int i=1;i<endCounter;i++) {
				if(row.getCell(i).getStringCellValue().trim().equals(colName)) {
					colNum = i;
					break;
				}
			}
			
			sheet.getRow(rowNum).getCell(colNum).setCellValue(data);
			
	        fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		    fileOut.close();
		    return true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName,String colName,int rowNum, String data){
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		sheet = workbook.getSheetAt(index);
		
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
//			System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName))
				colNum=i;
		}
		if(colNum==-1)
			return false;

		sheet.autoSizeColumn(colNum); 
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);

	    // cell style
	    //CellStyle cs = workbook.createCellStyle();
	    //cs.setWrapText(true);
	    //cell.setCellStyle(cs);
	    cell.setCellValue(data);

	    fileOut = new FileOutputStream(path);

		workbook.write(fileOut);

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @param sheetName
	 * @param colName
	 * @param rowNum
	 * @param data
	 * @param url
	 * @return
	 */
	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName,String colName,int rowNum, String data,String url){
		//System.out.println("setCellData setCellData******************");
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		//System.out.println("A");
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
				colNum=i;
		}
		
		if(colNum==-1)
			return false;
		sheet.autoSizeColumn(colNum); //ashish
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);
			
	    cell.setCellValue(data);
	    XSSFCreationHelper createHelper = workbook.getCreationHelper();

	    //cell style for hyperlinks
	    //by default hypelrinks are blue and underlined
	    CellStyle hlink_style = workbook.createCellStyle();
	    XSSFFont hlink_font = workbook.createFont();
	    hlink_font.setUnderline(XSSFFont.U_SINGLE);
	    hlink_font.setColor(IndexedColors.BLUE.getIndex());
	    hlink_style.setFont(hlink_font);
	    //hlink_style.setWrapText(true);

	    XSSFHyperlink link = createHelper.createHyperlink(XSSFHyperlink.LINK_FILE);
	    link.setAddress(url);
	    cell.setHyperlink(link);
	    cell.setCellStyle(hlink_style);
	      
	    fileOut = new FileOutputStream(path);
		workbook.write(fileOut);

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * @param sheetname
	 * @return
	 */
	// returns true if sheet is created successfully else false
	public boolean addSheet(String  sheetname){		
		
		FileOutputStream fileOut;
		try {
			workbook.createSheet(sheetname);	
			 fileOut = new FileOutputStream(path);
			 workbook.write(fileOut);
		     fileOut.close();		    
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// returns true if sheet is removed successfully else false if sheet does not exist
	public boolean removeSheet(String sheetName){		
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return false;
		
		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		    fileOut.close();		    
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	// returns true if column is created successfully
	public boolean addColumn(String sheetName,String colName){
		//System.out.println("**************addColumn*********************");
		
		try{				
			fis = new FileInputStream(path); 
			workbook = new XSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
				return false;
			
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		sheet=workbook.getSheetAt(index);
		
		row = sheet.getRow(0);
		if (row == null)
			row = sheet.createRow(0);
		
		//cell = row.getCell();	
		//if (cell == null)
		//System.out.println(row.getLastCellNum());
		if(row.getLastCellNum() == -1)
			cell = row.createCell(0);
		else
			cell = row.createCell(row.getLastCellNum());
	        
	        cell.setCellValue(colName);
	        cell.setCellStyle(style);
	        
	        fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		    fileOut.close();		    

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	// removes a column and all the contents
	public boolean removeColumn(String sheetName, int colNum) {
		try{
		if(!isSheetExist(sheetName))
			return false;
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		//XSSFCreationHelper createHelper = workbook.getCreationHelper();
		style.setFillPattern(HSSFCellStyle.NO_FILL);
		
		for(int i =0;i<getRowCount(sheetName);i++){
			row=sheet.getRow(i);	
			if(row!=null){
				cell=row.getCell(colNum);
				if(cell!=null){
					cell.setCellStyle(style);
					row.removeCell(cell);
				}
			}
		}
		fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
	    fileOut.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
  // find whether sheets exists	
	public boolean isSheetExist(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1){
			index=workbook.getSheetIndex(sheetName.toUpperCase());
				if(index==-1)
					return false;
				else
					return true;
		}
		else
			return true;
	}
	
	// returns number of columns in a sheet	
	public int getColumnCount(String sheetName){
		// check if sheet exists
		if(!isSheetExist(sheetName))
		 return -1;
		
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		
		if(row==null)
			return -1;
		
		return row.getLastCellNum();
		
		
		
	}
	//String sheetName, String testCaseName,String keyword ,String URL,String message
	public boolean addHyperLink(String sheetName,String screenShotColName,String testCaseName,int index,String url,String message){
		//System.out.println("ADDING addHyperLink******************");
		
		url=url.replace('\\', '/');
		if(!isSheetExist(sheetName))
			 return false;
		
	    sheet = workbook.getSheet(sheetName);
	    
	    for(int i=2;i<=getRowCount(sheetName);i++){
	    	if(getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)){
	    		//System.out.println("**caught "+(i+index));
	    		setCellData(sheetName, screenShotColName, i+index, message,url);
	    		break;
	    	}
	    }


		return true; 
	}
	
	public int getCellRowNum(String sheetName,String colName,String cellValue){
		
		for(int i=2;i<=getRowCount(sheetName);i++){
	    	if(getCellData(sheetName,colName , i).equalsIgnoreCase(cellValue)){
	    		return i;
	    	}
	    }
		return -1;
	}
	
	public static void copyWorkbook() {
		//input source excel file which contains sheets to be copied
		
		try {
			String inputFile = System.getProperty("user.dir") + "\\src\\main\\resources\\excelSpreadSheets\\" + FileReaderManager.getInstance().getConfigReader().getExcelDataPath();
			FileInputStream file = new FileInputStream(inputFile);
			XSSFWorkbook workbookinput = new XSSFWorkbook(file);

			//output new excel file to which we need to copy the above sheets
			//this would copy entire workbook from source
			XSSFWorkbook workbookoutput=workbookinput;

			String dateString = TestUtil.currentDateTimeString();

			String outputFile = System.getProperty("user.dir") + "\\src\\main\\resources\\excelSpreadSheets\\" + dateString + FileReaderManager.getInstance().getConfigReader().getExcelDataPath();
			//To write your changes to new workbook
			FileOutputStream out = new FileOutputStream(outputFile);
			workbookoutput.write(out);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}

