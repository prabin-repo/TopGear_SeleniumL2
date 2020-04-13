package com.wipro.testcases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wipro.testbase.TestBase;
import com.wipro.utilities.TestUtils;

public class TC004 extends TestBase {

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
	public void countMenuList() throws IOException {

		List<WebElement> main_menu = driver.findElements(By.xpath(OR.getProperty("mainMenu")));

		FileWriter write = new FileWriter(System.getProperty("user.dir") + "\\textMessages\\MainMenu.txt");
		String lineSeparator = System.getProperty("line.separator");

		for (int i = 0; i < main_menu.size(); i++) {
			String menu = main_menu.get(i).getText();
			System.out.println(menu);

			BufferedWriter out = new BufferedWriter(write);
			out.write("Total number of menu are " + main_menu.size());
			out.write(lineSeparator);
			out.write("Main menu are:" + menu + lineSeparator);
			// out.write(lineSeparator);

			out.flush();

		}

	}

	@Test(priority = 3)
	public void mainMenuSnaps() {
		driver.findElement(By.xpath(OR.getProperty("linkDesktop"))).click();
		TestUtils.captureScreenShot(driver, "Desktop");
		driver.findElement(By.xpath(OR.getProperty("linkLaptopNotebooks"))).click();
		TestUtils.captureScreenShot(driver, "LaptopAndNotebooks");
		driver.findElement(By.xpath(OR.getProperty("linkComponent"))).click();
		TestUtils.captureScreenShot(driver, "Components");
		driver.findElement(By.xpath(OR.getProperty("linkTablets"))).click();
		TestUtils.captureScreenShot(driver, "Tablets");
		driver.findElement(By.xpath(OR.getProperty("linkSoftware"))).click();
		TestUtils.captureScreenShot(driver, "Software");
		driver.findElement(By.xpath(OR.getProperty("linkPhone"))).click();
		TestUtils.captureScreenShot(driver, "Phone");
		driver.findElement(By.xpath(OR.getProperty("linkCameras"))).click();
		TestUtils.captureScreenShot(driver, "Cameras");
		driver.findElement(By.xpath(OR.getProperty("linkMp3Players"))).click();
		TestUtils.captureScreenShot(driver, "Mp3Players");

	}

	@Test(priority = 4)
	public void logoutTest() {

		TestUtils.logout();
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

}
