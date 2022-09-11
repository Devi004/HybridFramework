package commonFunctions;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import config.AppUtill;

public class FunctionLibrary extends AppUtill {

	public static boolean verifyLogin(String username,String password)
	{

		driver.get(config.getProperty("Url"));

		driver.findElement(By.xpath("ObjUser")).sendKeys(username);
		driver.findElement(By.xpath("ObjPass")).sendKeys(password);
		driver.findElement(By.xpath("ObjLogin")).click();

		String expected="adminflow";
		String actual=driver.getCurrentUrl();

		if(actual.contains(expected.toLowerCase()))
		{

			Reporter.log("Login Sucess"+actual+"  "+expected,true);

			return true;

		}

		else
		{
			Reporter.log("Login failed"+actual+"   "+expected,true);

			return false;
		}


	}
	//method for click branches

	public static void clickBranches()
	{
		driver.findElement(By.xpath(config.getProperty("ObjBranches"))).click();;

	}

	//method for branch creation

	public static boolean verifyBranchCreation(String branchname,String address1,String address2,String address3,String Area,String Zipcode,String country,String state,String city) throws Throwable
	{

		driver.findElement(By.xpath(config.getProperty("ObjNewBranch"))).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(config.getProperty("ObjBranchName"))).sendKeys(branchname);
		driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys(address1);
		driver.findElement(By.xpath(config.getProperty("ObjAddress2"))).sendKeys(address2);
		driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys(address3);
		driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys(Area);
		driver.findElement(By.xpath(config.getProperty("ObjZipcode"))).sendKeys(Zipcode);
		new Select(driver.findElement(By.xpath(config.getProperty("ObjCountry")))).selectByVisibleText(country);
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath(config.getProperty("ObjState")))).selectByVisibleText(state);
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath(config.getProperty("Objcity")))).selectByVisibleText(city);
		Thread.sleep(2000);
		driver.findElement(By.xpath(config.getProperty("Objsubmit"))).click();

		String expectedbranchalert=driver.switchTo().alert().getText();
		Thread.sleep(5000);

		String actualbranchalert="New Branch with";

		if(expectedbranchalert.toLowerCase().contains(actualbranchalert.toLowerCase()))
		{
			Reporter.log(expectedbranchalert,true);

			return true;

		}

		else
		{

			Reporter.log("Branch creation failed",true);

			return false;
		}


	}


	public static boolean verrifyBranchUpdation(String branch,String address,String area,String zipcode) throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjEdit"))).click();
		Thread.sleep(2000);

		WebElement element=driver.findElement(By.xpath(config.getProperty("ObjBranch")));
		element.clear();
		element.sendKeys(branch);

		WebElement element1=driver.findElement(By.xpath(config.getProperty("ObjAddress")));
		element1.clear();
		element1.sendKeys(address);

		WebElement element2=driver.findElement(By.xpath(config.getProperty("ObjAreaName")));
		element2.clear();
		element2.sendKeys(area);

		WebElement element3=driver.findElement(By.xpath(config.getProperty("Objzip")));
		element3.clear();
		element3.sendKeys(zipcode);
		driver.findElement(By.xpath(config.getProperty("ObjUpdate"))).click();
		Thread.sleep(2000);

		String expectedbranchupdatealert=driver.switchTo().alert().getText();
		Thread.sleep(2000);

		driver.switchTo().alert().accept();

		String actualbranchupdatealert="Branch updated";

		if(expectedbranchupdatealert.toLowerCase().equalsIgnoreCase(actualbranchupdatealert.toLowerCase()))
		{

			Reporter.log(expectedbranchupdatealert,true);


			return true;

		}

		else
		{
			Reporter.log("Updation faied",true);


		}	return false;

		







}
	
	public static boolean verifyLogout() throws Throwable
	{
		
		driver.findElement(By.xpath(config.getProperty("ObjLogout"))).click();
		Thread.sleep(2000);
		
		if(driver.findElement(By.xpath(config.getProperty("ObjLogin"))).isDisplayed());
		{
			Reporter.log("Logout sucess",true);
			
			return true;
			
		}
		
		
		
		
			
			
		
		
		
		
	
	
	}







}
