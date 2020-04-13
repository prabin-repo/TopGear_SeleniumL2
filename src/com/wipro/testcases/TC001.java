package com.wipro.testcases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wipro.testbase.TestBase;
import com.wipro.utilities.TestUtils;

public class TC001 extends TestBase {

	@Test(priority = 1, dataProvider = "getData")
	public void Registration(String firstname, String lastname, String Email, String telephone, String password) throws IOException {

		driver.findElement(By.xpath(OR.getProperty("linkMyAccount"))).click();
		driver.findElement(By.xpath(OR.getProperty("linkRegister"))).click();
		driver.findElement(By.xpath(OR.getProperty("FirstName"))).sendKeys(firstname);
		driver.findElement(By.xpath(OR.getProperty("LastName"))).sendKeys(lastname);
		driver.findElement(By.xpath(OR.getProperty("Email"))).sendKeys(Email);
		driver.findElement(By.xpath(OR.getProperty("telephone"))).sendKeys(telephone);
		driver.findElement(By.xpath(OR.getProperty("Password"))).sendKeys(password);
		driver.findElement(By.xpath(OR.getProperty("ConfirmPassword"))).sendKeys(password);

		WebElement checkbox = driver.findElement(By.xpath(OR.getProperty("chkbox_privacyPolicy")));
		// System.out.println("checkbox selection state is "+ checkbox.isSelected());
		boolean check = checkbox.isSelected();

		if (check == false) {
			String output = "CheckBox is not Selected";
			File DestFile = new File(System.getProperty("user.dir") + "\\textMessages\\PolicyCheckboxMessage");
			try {
				FileUtils.writeStringToFile(DestFile, output);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElement(By.xpath(OR.getProperty("chkbox_privacyPolicy"))).click();
			driver.findElement(By.xpath(OR.getProperty("btnContinue"))).click();

		}

	}

	@Test(priority = 2)
	public void successfullResgisterTest() throws IOException {
		String output = driver.findElement(By.xpath(OR.getProperty("txt_SuccessfullMsg"))).getText();
		File DestFile = new File(System.getProperty("user.dir") + "\\textMessages\\SuccessfullRegisterMessage.txt");
		try {
			FileUtils.writeStringToFile(DestFile, output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TestUtils.captureScreenShot(driver, "RegisterSuccess");

	}

	@Test(priority = 3)
	public void logoutTest() {

		TestUtils.logout();
	}
 
	@DataProvider
	public Object[][] getData() {

		String sheetName = "registration";

		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			for (int colNum = 0; colNum < cols; colNum++) {

				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}

		return data;

	}
}
