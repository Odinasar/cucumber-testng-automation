package com.app.utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	private String filePath = ConfigurationReader.getProperty("xpath_location");
	private FileInputStream inStream;
	private Workbook workbook;
	private Sheet worksheet;

	public List<Map<String, String>> downloadData(int rowForHeader) {
		List<Map<String, String>> data = new ArrayList<>();
		Map<String, String> row = new HashMap();
		List<String> keys = new ArrayList<>();
		try {
			inStream = new FileInputStream(filePath);
			workbook = WorkbookFactory.create(inStream);
			worksheet = workbook.getSheetAt(0);
			
			for (int i = 0; i < worksheet.getRow(rowForHeader).getPhysicalNumberOfCells(); i++) {
				keys.add(worksheet.getRow(rowForHeader).getCell(i) + "");
			}
			System.out.println("Keys: " + keys);
			for (int i = rowForHeader + 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
				for (int j = 0; j < keys.size(); j++) {
					row.put(keys.get(j), worksheet.getRow(i).getCell(j) + "");
				}
				data.add(row);
				row = new HashMap<>();
			}
			workbook.close();
			inStream.close();

		} catch (Exception e) {
           throw new RuntimeException();
		}
		return data;
	}

	public List<String> getRow(int whichRow) {
		List<String> row = new ArrayList<>();
		
		try {

			inStream = new FileInputStream(filePath);
			workbook = WorkbookFactory.create(inStream);
			worksheet = workbook.getSheetAt(0);
			
			for (int i = 0; i < worksheet.getRow(whichRow-1).getPhysicalNumberOfCells(); i++) {
				row.add(worksheet.getRow(whichRow-1).getCell(i) + "");
			}
			
			workbook.close();
			inStream.close();

		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		return row;
	}

	public String getCell(int whichRow, int whichCell) {
		return null;
	}

	public int getNumberOfRows() {
		int numberOfrow = 0;
		try {

			inStream = new FileInputStream(filePath);
			workbook = WorkbookFactory.create(inStream);
			worksheet = workbook.getSheetAt(0);
			numberOfrow = worksheet.getPhysicalNumberOfRows();
			workbook.close();
			inStream.close();

		} catch (Exception e) {
			throw new RuntimeException();
		}

		return numberOfrow - 1;

	}

	public int getNumberOfCell(int whichRow) {
		int numberOfCell = 0;
		try {

			inStream = new FileInputStream(filePath);
			workbook = WorkbookFactory.create(inStream);
			worksheet = workbook.getSheetAt(0);
			numberOfCell = worksheet.getRow(whichRow).getPhysicalNumberOfCells();

			workbook.close();
			inStream.close();

		} catch (Exception e) {
			throw new RuntimeException();
		}
		return numberOfCell;
	}

	public static void main(String[] args) {
		ExcelReader ob = new ExcelReader();
		List<Map<String, String>> contacts = ob.downloadData(2);
		for(Map<String,String> row : contacts) {
			 System.out.println("name: "+row.get("Name"));
			 System.out.println("account: "+row.get("Account"));
			 System.out.println("email: "+row.get("Email"));
			 System.out.println("phone: "+row.get("Phone"));
			 System.out.println("__________________");
		}
	
	}

}
