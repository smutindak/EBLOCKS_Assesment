package onlinecalculator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

public class DivideByZero {

	@Test
	void DivideByZero() throws InterruptedException, IOException {

		// Setup webdriver
		WebDriverManager.chromedriver().setup();

		// Initialize Driver
		WebDriver driver = new ChromeDriver();

		// Invoke browser
		driver.get(Config.website_url);

		// Switch to iFrame
		driver.switchTo().frame("fullframe");

		// Divide two Numbers
		JavascriptExecutor js = (JavascriptExecutor) driver;

		Object firstValue = js.executeScript(
				"document.getElementById('canvas').dispatchEvent(new KeyboardEvent('keypress',{which:56,keyCode:56,bubbles:true}))");
		Thread.sleep(600);
		Object operator = js.executeScript(
				"document.getElementById('canvas').dispatchEvent(new KeyboardEvent('keypress',{which:47,keyCode:47,bubbles:true}))");
		Thread.sleep(500);
		Object secondValue = js.executeScript(
				"document.getElementById('canvas').dispatchEvent(new KeyboardEvent('keypress',{which:48,keyCode:48,bubbles:true}))");
		Thread.sleep(800);
		Object equals = js.executeScript(
				"document.getElementById('canvas').dispatchEvent(new KeyboardEvent('keypress',{which:61,keyCode:61,bubbles:true}))");
		Thread.sleep(800);

		// Fetch Results
		Object result = js.executeScript("return exportRoot.showscreen_txt.text;");

		// TakeScreenShot
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File(Config.current_work_directory + File.separator + "/ScreenShots/DivideByZero.png"));

		// Assert expectation
		Assert.assertEquals(result, ExpectedResults.DivideByZero);

		// Quit Browser

		driver.quit();
	}
}
