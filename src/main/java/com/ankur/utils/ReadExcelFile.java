package com.ankur.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	String email, password;
	ArrayList<Object[]> userData = new ArrayList<>();
	

	public ArrayList<Object[]> getLoginDataFromFile() throws IOException {
		String excelFilePath = "\\src\\test\\resources\\user_credentials.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(Initialize.cwd+excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);

		int rowCount = firstSheet.getLastRowNum();

		for (int counter = 1; counter < rowCount; ++counter) {
			email = firstSheet.getRow(counter).getCell(0).getStringCellValue();
			password = firstSheet.getRow(counter).getCell(1).getStringCellValue();
			
			Object obj[] = { email, password };
			userData.add(obj);

		}

		workbook.close();
		inputStream.close();
		
		return userData;
	}
}
