package com.wipro.testcases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.wipro.testbase.TestBase;
import com.wipro.utilities.TestUtils;

public class TC002 extends TestBase {

	@Test(priority = 1, dataProvider = "getData")
	public void loginTest(String Email, String password) {

		driver.findElement(By.xpath(OR.getProperty("linkMyAccount"))).click();
		driver.findElement(By.xpath(OR.getProperty("linkLogin"))).click();
		driver.findElement(By.name(OR.getProperty("txtEmail"))).sendKeys(Email);
		driver.findElement(By.name(OR.getProperty("txtPassword"))).sendKeys(password);
		driver.findElement(By.xpath(OR.getProperty("btnLogin"))).click();
		TestUtils.captureScreenShot(driver, "LoginSuccess");
	}

	@Test(priority = 2)
	public void editInformation() throws IOException {
		driver.findElement(By.xpath(OR.getProperty("EditAccount"))).click();
		driver.findElement(By.xpath(OR.getProperty("textTelephone"))).clear();
		driver.findElement(By.xpath(OR.getProperty("textTelephone"))).sendKeys(config.getProperty("phone"));
		driver.findElement(By.xpath(OR.getProperty("btnContinue"))).click();
		String updateMsg = driver.findElement(By.xpath(OR.getProperty("MsgUpdate"))).getText();
		String ExpectedMsg = "Success: Your account has been successfully updated.";
		Assert.assertEquals(updateMsg, ExpectedMsg);

		WebElement msgUpdate = driver.findElement(By.xpath(OR.getProperty("MsgUpdate")));
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(screenshot);

		Point point = msgUpdate.getLocation();
		int elementWidth = msgUpdate.getSize().getWidth();
		int elementHeight = msgUpdate.getSize().getHeight();
		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), elementWidth, elementHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);

		File screenshotLocation = new File(
				"C:\\Users\\man2\\Workspace\\QET_CoE_SelniumL2\\screenshot\\Accountupdate.png");
		FileUtils.copyFile(screenshot, screenshotLocation);

	}

	@Test(priority = 3)
	public void logoutTest() {

		TestUtils.logout();
	}

	@DataProvider
	public Object[][] getData() {

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
