package julien.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import tools.ScreenshotTestRule;
import tools.Browser;

@Listeners({ScreenshotTestRule.class})
public class SeleniumTest extends Browser{

	@Test(invocationCount = 1)
	public void sampleTest() {

		driver.get("https://www.google.fr");
		Assert.assertEquals(driver.getTitle(), "Googlei");
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
