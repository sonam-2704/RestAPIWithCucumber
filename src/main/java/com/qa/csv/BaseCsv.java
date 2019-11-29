package com.qa.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BaseCsv {
	
	
	public static String csvFile = System.getProperty("user.dir")+"\\src\\main\\resources\\input\\data.csv";
	
	public static HashMap<String, String> readCsvToMap(){
		
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
		HashMap<String,String> csvMap = new HashMap<String,String>();
		
		   try {

	            br = new BufferedReader(new FileReader(csvFile));
	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] data = line.split(cvsSplitBy);
	                csvMap.put(data[0].replace("\"", ""),data[1].replace("\"", ""));

	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		   
		   return csvMap;
	}
	
	/*public static void main(String[] args) {
		
		
		HashMap map = BaseCsv.readCsvToMap();
		
		System.out.println(map);
    }
*/
	
}
