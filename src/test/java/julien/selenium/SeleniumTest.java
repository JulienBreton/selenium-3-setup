package julien.selenium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import org.testng.ITestContext;

public class SeleniumTest {

	WebDriver driver;

	@BeforeTest
	public void setUp(ITestContext context) throws MalformedURLException {

		String sBrowser = context.getCurrentXmlTest().getParameter("selenium.browser");
		String sRemoteAddress = context.getCurrentXmlTest().getParameter("selenium.remoteAddress");

		if (sBrowser.equalsIgnoreCase("firefox")) {

			//The geckodriver is in the same folder than the selenium-server-standalone 
			driver = new RemoteWebDriver(new URL(sRemoteAddress), new FirefoxOptions());
		}
		else if (sBrowser.equalsIgnoreCase("chrome")) {

			//The chromedriver is in the same folder than the selenium-server-standalone 
			driver = new RemoteWebDriver(new URL(sRemoteAddress), new ChromeOptions());
		}
		else
		{
			throw new IllegalArgumentException("[ERROR] The browser is not defined or unknowed. Actual browser : -"+sBrowser+"-");
		}

	}

	@AfterTest
	public void afterTest() {

		driver.quit();
	}

	@Test(invocationCount = 1)
	public void sampleTest() {

		driver.get("https://www.google.fr");
		Assert.assertEquals(driver.getTitle(), "Google");
	}
	
	@Test
	public void uploadFile() {
		
		//In this test we check if we can access to the file shared between the host and the container.
		//See the volume with /home/julien/test:/home/selenium/upload in the docker-compose.yaml.

		driver.get("http://demo.guru99.com/test/upload/");
		
		//Upload the file
		WebElement uploadElement = driver.findElement(By.id("uploadfile_0"));
		uploadElement.sendKeys("/home/selenium/upload/test.txt");
	    driver.findElement(By.id("terms")).click();
	    driver.findElement(By.name("send")).click();
	    
	    //Check if the file is uploaded
	    WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#res > center"), "1 file\nhas been successfully uploaded.")); 
	}
}
