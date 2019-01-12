package julien.selenium;


import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
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
			driver = new RemoteWebDriver(new URL(sRemoteAddress),  new FirefoxOptions());
		}
		else if (sBrowser.equalsIgnoreCase("chrome")) {
			
			//The chromedriver is in the same folder than the selenium-server-standalone 
			driver = new RemoteWebDriver(new URL(sRemoteAddress),  new ChromeOptions());
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
    
    @Test
    public void sampleTest() {
        
    	driver.get("https://www.google.fr");    	
    	Assert.assertEquals(driver.getTitle(), "Google");
    }
}