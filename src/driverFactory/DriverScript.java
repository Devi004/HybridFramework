package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtill;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtill {

	String inputpath="D:\\Selenium_11clock_Practice\\Hybrid_FrameWork\\DataTables\\DataEngine.xlsx";
	String outputpath="D:\\Selenium_11clock_Practice\\Hybrid_FrameWork\\TestResults\\HybridResults.xlsx";

	String TCSheet="MasterTestCases";
	String TSsheet="TestSteps";
	ExtentReports report;
	ExtentTest test;
	int a=20;

	@Test
	public void startTest()throws Throwable
	{
		
		report=new ExtentReports("./Reports/Hybrid.html");
		boolean res=false;
		String tcres="";

		//Excess excelfileutill

		ExcelFileUtil xl=new ExcelFileUtil(inputpath);

		//count number of rows in master test cases sheet
		int TCCount=xl.rowCount(TCSheet);

		//count number of rows in Test Steps cases sheet

		int TSCount=xl.rowCount(TSsheet);


		Reporter.log("Number of Rows in master TCS sheet::"+TCCount+"     "+"Number of Rows in test steps sheet"+TSCount);

		//Iterate all the rows from master test case sheet

		for (int i = 1; i<=TCCount ; i++) {
			
			//read module name cell data
			
			String ModuleName=xl.getCellData(TCSheet, i, 1);
			
			test=report.startTest(ModuleName);
			//read execution mode cell
			
			String executionmode=xl.getCellData(TCSheet, i, 2);

			if(executionmode.equalsIgnoreCase("Y"))

			{

				//read tc id cell from tc sheet

				String tcid=xl.getCellData(TCSheet, i, 0);
				//iterate all the rows from ts sheet

				for (int j = 1; j <=TSCount; j++) {

					//read descriptioncell
					
					String description=xl.getCellData(TSsheet, j, 2);
					
					//read tsid from tssheet

					String tsid=xl.getCellData(TSsheet, j,0);

					if(tcid.equalsIgnoreCase(tsid))
					{

						String keyword=xl.getCellData(TSsheet, j, 3);

						if(keyword.equalsIgnoreCase("AdminLogin"))
							
							
						{
							
							String para1=xl.getCellData(TSsheet, j, 5);
							String para2=xl.getCellData(TSsheet, j, 6);
							
							res=FunctionLibrary.verifyLogin(para1, para2);
							
							test.log(LogStatus.INFO, description);
							
						}
						
						else if(keyword.equalsIgnoreCase("NewBranch"))
							
						{
									
							String para1=xl.getCellData(TSsheet, j, 5);	
							String para2=xl.getCellData(TSsheet, j, 6);
							String para3=xl.getCellData(TSsheet, j, 7);
							String para4=xl.getCellData(TSsheet, j, 8);
							String para5=xl.getCellData(TSsheet, j, 9);
							String para6=xl.getCellData(TSsheet, j, 10);
							String para7=xl.getCellData(TSsheet, j, 11);
							String para8=xl.getCellData(TSsheet, j, 12);
							String para9=xl.getCellData(TSsheet, j, 13);

							FunctionLibrary.clickBranches();
							res=FunctionLibrary.verifyBranchCreation(para1, para2, para3, para4, para5, para6, para7, para8, para9);
							test.log(LogStatus.INFO, description);

							
							}
						
						else if(keyword.equalsIgnoreCase("Branch Update"))
						{
							String para1=xl.getCellData(TSsheet, j, 5);
							String para2=xl.getCellData(TSsheet, j, 6);

							String para5=xl.getCellData(TSsheet, j, 9);

							String para6=xl.getCellData(TSsheet, j, 10);
							
							FunctionLibrary.clickBranches();
							
							res=FunctionLibrary.verrifyBranchUpdation(para1, para2, para5, para6);
							test.log(LogStatus.INFO, description);

						}
						
						else if(keyword.equalsIgnoreCase("Admin Logout"))
							
							
						{
							
							 res=FunctionLibrary.verifyLogout();
								test.log(LogStatus.INFO, description);

						}
						
						String tsres="";
						
						if(res)
						{
							//if res is true write the results in status cell
							
							 tsres="Pass";
							
							xl.setCellData(TSsheet, j, 4, tsres, outputpath);
							test.log(LogStatus.PASS,"My test case passed");

							
							
						}
						else
						{
							//if res is false write the results in status cell
							
							 tsres="Fail";
							
							xl.setCellData(TSsheet, j, 4, tsres, outputpath);
							
							test.log(LogStatus.FAIL,"My test case failed");
							
						}
						
						tcres=tsres;

						
						


					}
					
					report.endTest(test);
					report.flush();

				}
				
				//write tcres into sheet
				
				
					xl.setCellData(TCSheet, i, 3, tcres, outputpath);
					
				

			}
			else {

				//write blocked which tc flag to n

				xl.setCellData(TCSheet, i, 3, "blocked", outputpath);

			}

		}




	}


}
