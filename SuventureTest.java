package Demo;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class SuventureTest {	
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android_Emulator");		
		cap.setCapability("appPackage", "com.climate.farmrise");
		cap.setCapability("appActivity", "com.climate.farmrise.SplashScreen");
	
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		
		Thread.sleep(6000);
		driver.findElementByXPath("//android.widget.TextView[@text='English']").click();
		driver.findElementByXPath("//android.widget.Button[@text='Proceed']").click();
		driver.findElementByXPath("//android.widget.Button[@text='Agree and Continue']").click();
		
		Thread.sleep(2000);
		
		for(int i=1;i<=5;i++)
		{				
			AndroidElement Display = driver.findElementById("com.climate.farmrise:id/tv_dismiss");
			TouchAction t = new TouchAction(driver);
			t.tap(tapOptions().withElement(element(Display))).perform();
			Thread.sleep(500);
		}		
		
		Thread.sleep(500);
		testCaseOne(driver);
		Thread.sleep(500);
		testCaseTwo(driver);
	}
	
	
	public static void testCaseOne(AndroidDriver<AndroidElement> wd) throws InterruptedException
	{	
		
		int count = 0;
		for(int j=0;j<=23;j++)
		{
			AndroidElement Time = (AndroidElement) wd.findElementsById("com.climate.farmrise:id/hourlyValue").get(j);
			if(Time.isDisplayed())
			{
				count++;
				System.out.println("Time count :"+count);
				System.out.println("Time value : "+Time.getAttribute("text"));
			}
			else
			{
				System.out.println("Time Index not displayed : " +j);
				Thread.sleep(500);
			}
		}
		wd.findElementById("com.climate.farmrise:id/action_home").click();
		Thread.sleep(500);
	}


	public static void testCaseTwo(AndroidDriver<AndroidElement> wd)
	{
		wd.findElementById("com.climate.farmrise:id/action_more").click();
		wd.findElementById("com.climate.farmrise:id/more_govtSchemes").click();
		
		String endElementText1 = "Atal Pension Yojana ";
		String endElementText2 = "Pradhan Mantri Suraksha Bima Yojana (PMSBY) ";
		String endElementText3 = "Load More schemes";
		
		 wd.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+endElementText1+"\").instance(0))");
		 wd.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+endElementText2+"\").instance(0))");
		 AndroidElement LoadMoreButton =  wd.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+endElementText3+"\").instance(0))");
		 
		if(LoadMoreButton.isDisplayed())
		{
			System.out.println("Load More Schemes button is displayed");	 
	    }
		else
		{
			System.out.println("Load More Schemes button is not displayed");	
		}
		
		wd.findElementById("android:id/search_button").click();
		AndroidElement Search = wd.findElementById("android:id/search_src_text");
		Search.sendKeys("Scheme");
		Search.submit();
			
	    AndroidElement SchemeText = (AndroidElement) wd.findElementsByPartialLinkText("Scheme");
	    System.out.println("Scheme Search result count : "+SchemeText.getSize());
	}
	
	
}
