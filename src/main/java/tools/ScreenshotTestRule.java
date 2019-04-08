package tools;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import tools.Browser;

public class ScreenshotTestRule extends TestListenerAdapter{
	
	WebDriver driver;

	@Override
	public void onTestFailure(ITestResult testResult){
		
        String className = testResult.getTestClass().getName();
        String methodName = testResult.getMethod().getMethodName();
        captureScreenshot(className, methodName);
    }  
 
	public void captureScreenshot(String className, String methodName) {
        	
		try {
                String filename = Browser.sBrowser+"_"+className +"_"+ methodName + ".png";
                File screenshot = ((TakesScreenshot)Browser.driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("screenshots/"+filename));
                System.out.printf("Taking screenshot: " + "screenshots/"+filename);                
               
            } catch (Exception e) {
             
                System.out.println(e.toString());
            }
        }
}
