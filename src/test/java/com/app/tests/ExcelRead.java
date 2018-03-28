package com.app.tests;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelRead {
    public static void main(String[] args) {
		String filePath = "C:\\Users\\Beknazar\\Desktop\\Employees.xlsx";
	        //	workbook > workSheet > Rows > Cell
		try {
			// Open file and convert to the String of data
			FileInputStream inStream = new FileInputStream(filePath);
			// take the stream of data and use it as WorkBook
			Workbook workbook = WorkbookFactory.create(inStream);
			// Get the first workSheet from the workbook
			Sheet worksheet = workbook.getSheetAt(0);
			// goto first row
			Row row = worksheet.getRow(0);
			Cell cell = row.getCell(0);
			System.out.println(cell.toString());
			
			//Find out how many rows in Excel sheet
			int rowNum = worksheet.getPhysicalNumberOfRows();
			
			System.out.println("Number of rows: "+rowNum);
			
//			for(int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
//				System.out.println(i+" - "+worksheet.getRow(i).getCell(0));
//			}
			
			// Print Job Id of Nancy
			for(int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
				if(worksheet.getRow(i).getCell(0).toString().equals("Nancy")) {
					System.out.println(worksheet.getRow(i).getCell(2));
					break;
				}
			}
			
			workbook.close();
			inStream.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
