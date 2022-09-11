package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Deviprasad {

	ExtentReports report;
	WebDriver driver;
	ExtentTest test;

	@BeforeTest
	public void setup()
	{
		report=new ExtentReports("D://dggds",true);
	}

	public void setup1()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://google.com");
	}


	public void passtest()
	{
		test=report.startTest("My pass tset case");
		test.assignAuthor("Devi");

		String expected="google";
		String actual=driver.getTitle();

		if(expected.equalsIgnoreCase(actual))
		{
			test.log(LogStatus.PASS, "My test case passed");

		}

		else

		{
			{
				test.log(LogStatus.FAIL, "My test case passed");


			}


		}


	}


}

