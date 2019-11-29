package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.qa.csv.BaseCsv;

public class TestBase {

	/*public Properties prop;
	
	public TestBase() {

		try {
			prop = new Properties();

			FileInputStream fis = new FileInputStream(
					new File(System.getProperty("user.dir") + "/src/main/java/com/qa/config/config.properties"));

			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}*/
	
	public static String getCsvData(String key){

		HashMap<String,String> csvMap = BaseCsv.readCsvToMap();
		return csvMap.get(key);
	}
}
