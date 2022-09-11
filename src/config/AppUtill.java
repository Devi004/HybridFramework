package config;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtill {
	
	public static WebDriver driver;
	public static Properties config;
	
	@BeforeSuite
	public static void setup()throws Throwable
	{
		config=new Properties();
		config.load(new FileInputStream("D:\\Selenium_11clock_Practice\\Hybrid_FrameWork\\PropertyFiles\\Environment.properties"));
		
		if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		
		else if(config.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		else
		{
			Reporter.log("Browser not found",true);
		}
		
	}
	
	@AfterSuite
	public static void tearDown()
	{
		driver.close();
		
	}

}
 