package com.wipro.testcases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wipro.testbase.TestBase;
import com.wipro.utilities.TestUtils;

public class TC005 extends TestBase {

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
	public void getFavBrands() {
		driver.findElement(By.xpath(OR.getProperty("linkYourStore"))).click();
		driver.findElement(By.xpath(OR.getProperty("linkBrand"))).click();
		String lblFavBrand = driver.findElement(By.xpath(OR.getProperty("labelFavouriteBrand"))).getText();
		String ExpLblFavBrand = "Find Your Favorite Brand";
		Assert.assertEquals(ExpLblFavBrand, lblFavBrand);

	}

	@Test(priority = 3)
	public void getBrandSnaps() {

		driver.findElement(By.xpath(OR.getProperty("linkApple"))).click();
		TestUtils.captureScreenShot(driver, "AppleBrand");
		driver.navigate().back();

		driver.findElement(By.xpath(OR.getProperty("linkCanon"))).click();
		TestUtils.captureScreenShot(driver, "CanonBrand");
		driver.navigate().back();

		driver.findElement(By.xpath(OR.getProperty("linkHewlettPackard"))).click();
		TestUtils.captureScreenShot(driver, "HPBrand");
		driver.navigate().back();

		driver.findElement(By.xpath(OR.getProperty("linkHTC"))).click();
		TestUtils.captureScreenShot(driver, "HTCBrand");
		driver.navigate().back();

		driver.findElement(By.xpath(OR.getProperty("linkPalm"))).click();
		TestUtils.captureScreenShot(driver, "PalmBrand");
		driver.navigate().back();

		driver.findElement(By.xpath(OR.getProperty("linkPalm"))).click();
		TestUtils.captureScreenShot(driver, "PalmBrand");
		driver.navigate().back();

		driver.findElement(By.xpath(OR.getProperty("linkSony"))).click();
		TestUtils.captureScreenShot(driver, "SonyBrand");
		driver.navigate().back();

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
