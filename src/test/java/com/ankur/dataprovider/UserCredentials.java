package com.ankur.dataprovider;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.ankur.utils.ReadExcelFile;

public class UserCredentials {

	ReadExcelFile readExcelFile = new ReadExcelFile();
	
	@DataProvider
	public Iterator<Object[]> getUserData(){
		try {
			ArrayList<Object[]> userData = readExcelFile.getLoginDataFromFile();	
			return userData.iterator();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		
	}
}
