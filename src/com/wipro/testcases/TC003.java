package com.wipro.testcases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wipro.testbase.TestBase;
import com.wipro.utilities.TestUtils;

public class TC003 extends TestBase {

	@Test(priority = 1, dataProvider = "getData")
	public void login(String Email, String password) {

		driver.findElement(By.xpath(OR.getProperty("linkMyAccount"))).click();
		driver.findElement(By.xpath(OR.getProperty("linkLogin"))).click();
		driver.findElement(By.name(OR.getProperty("txtEmail"))).sendKeys(Email);
		driver.findElement(By.name(OR.getProperty("txtPassword"))).sendKeys(password);
		driver.findElement(By.xpath(OR.getProperty("btnLogin"))).click();
		TestUtils.captureScreenShot(driver, "LoginSuccess");
	}

	@Test(priority = 2)
	public void addProductTest() {

		driver.findElement(By.xpath(OR.getProperty("linkComponent"))).click();
		driver.findElement(By.xpath(OR.getProperty("linkMonitor"))).click();
		TestUtils.captureScreenShot(driver, "Monitors");

		String output = driver.findElement(By.xpath(OR.getProperty("labelAppleCinema"))).getText();
		String output2 = driver.findElement(By.xpath(OR.getProperty("labelPrice"))).getText();

		File DestFile = new File(System.getProperty("user.dir") + "\\textMessages\\Price.txt");
		try {
			FileUtils.writeStringToFile(DestFile, "Monitor name :" + output + "Price is " + output2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(priority = 3, dataProvider = "getProductData")
	public void addProduct(String Text, String TextArea, String Qty, String Colour, String Date, String Time) {

		driver.findElement(By.xpath(OR.getProperty("AddToCart"))).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Checkbox3"))));
		driver.findElement(By.xpath(OR.getProperty("Checkbox3"))).click();
		driver.findElement(By.xpath(OR.getProperty("txt_Text"))).clear();
		driver.findElement(By.xpath(OR.getProperty("txt_Text"))).sendKeys(Text);

		Select select = new Select(driver.findElement(By.name(OR.getProperty("SelectColour"))));
		select.selectByVisibleText(Colour);

		driver.findElement(By.xpath(OR.getProperty("txt_Textarea"))).clear();
		driver.findElement(By.xpath(OR.getProperty("txt_Textarea"))).sendKeys(TextArea);

		driver.findElement(By.name(OR.getProperty("txtDate"))).clear();
		driver.findElement(By.name(OR.getProperty("txtDate"))).sendKeys(Date);
		driver.findElement(By.name(OR.getProperty("txtTime"))).clear();
		driver.findElement(By.name(OR.getProperty("txtTime"))).sendKeys(Time);

		driver.findElement(By.xpath(OR.getProperty("txt_Qty"))).clear();
		driver.findElement(By.xpath(OR.getProperty("txt_Qty"))).sendKeys(Qty);
	}

	@DataProvider
	public static Object[][] getData() {

		String sheetName = "Login";

		int rows = excel2.getRowCount(sheetName);
		int cols = excel2.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			for (int colNum = 0; colNum < cols; colNum++) {

				data[rowNum - 2][colNum] = excel2.getCellData(sheetName, colNum, rowNum);
			}
		}

		return data;

	}

	@DataProvider
	public static Object[][] getProductData() {

		String sheetName = "AddProductData";

		int rows = excel3.getRowCount(sheetName);
		int cols = excel3.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			for (int colNum = 0; colNum < cols; colNum++) {

				data[rowNum - 2][colNum] = excel3.getCellData(sheetName, colNum, rowNum);
			}
		}

		return data;

	}

}
