package com.wipro.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import com.wipro.testbase.TestBase;

public class TestUtils extends TestBase {

	public static void captureScreenShot(WebDriver driver, String screenshotName) {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source,
					new File(System.getProperty("user.dir") + "\\screenshot\\" + screenshotName + ".png"));

		} catch (Exception e) {
			System.out.println("Exception while taking screenshot" + e.getMessage());
		}

	}
	
	public static void logout() {
		driver.findElement(By.xpath(OR.getProperty("linkMyAccount"))).click();
		driver.findElement(By.xpath(OR.getProperty("Logout"))).click();

		String output = driver.findElement(By.xpath(OR.getProperty("MsgLogout"))).getText();
		File DestFile = new File(System.getProperty("user.dir") + "\\textMessages\\LogOutMessage.txt");
		try {
			FileUtils.writeStringToFile(DestFile, output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
