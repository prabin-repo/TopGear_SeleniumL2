package com.wipro.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	
	public static FileInputStream fis;
	
	
	public static void main(String[] args) throws FileNotFoundException {
	

		System.out.println(System.getProperty("user.dir"));

		Properties config = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\resources\\config\\config.properties");
		try {
			config.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Properties OR = new Properties();
		fis=new FileInputStream(System.getProperty("user.dir")+"\\resources\\config\\OR.properties");
	try {
		OR.load(fis);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

}

