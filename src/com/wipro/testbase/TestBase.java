package com.wipro.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;

import com.wipro.utilities.ConfigFileReader;
import com.wipro.utilities.ExcelReader;

public class TestBase {

	public static WebDriver driver;
	public static Properties config;
	public static Properties OR;
	public static FileInputStream fis;
	 
	
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\runner\\testdata\\Registration.xlsx");
	
	public static ExcelReader excel2 = new ExcelReader(
			System.getProperty("user.dir") + "\\runner\\testdata\\login.xlsx");
	
	public static ExcelReader excel3 = new ExcelReader(
			System.getProperty("user.dir") + "\\runner\\testdata\\AddProduct.xlsx");

	String userdir = System.getProperty("user.dir");

	ConfigFileReader conf = new ConfigFileReader();

	@BeforeSuite
	public void setUp() throws FileNotFoundException {

		if (driver == null) {
			config = new Properties();
			fis = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config\\config.properties");
			try {
				config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			OR = new Properties();
			fis = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config\\OR.properties");
			try {
				OR.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", userdir + "\\resources\\driverfiles\\geckodriver.exe");
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", userdir + "\\resources\\driverfiles\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (config.getProperty("browser").equals("IE")) {
				System.setProperty("WebdDriver.ie.driver", userdir + "\\resources\\driverfiles\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();

			} else {
				System.out.println("nO bROWSER");
			}
			driver.get(config.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("Page_Load_TimeOut")),
					TimeUnit.SECONDS);
		}

	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}
	}

}
