package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;


	public ExcelFileUtil(String excelpath) throws Throwable

	{
		FileInputStream fi=new FileInputStream(excelpath);
		wb=new XSSFWorkbook(fi);
	}

	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}

	public int cellCount(String sheetname)
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();

	}

	public String getCellData(String sheetname,int row,int column)	

	{
		String data=" ";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)

		{
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();

			data=String.valueOf(celldata);
		}


		else
		{
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;

	}

	public void setCellData(String sheetname,int row,int column,String status,String writeExcel) throws Throwable
	{

		XSSFSheet ws=wb.getSheet(sheetname);
		XSSFRow rowNum=ws.getRow(row);
		XSSFCell cell=rowNum.createCell(column);
		cell.setCellValue(status);

		if(status.equalsIgnoreCase("pass"))
		{
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			wb.getSheet(sheetname).getRow(row).getCell(column).setCellStyle(style);
		}

		if(status.equalsIgnoreCase("fail"))
		{

			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			wb.getSheet(sheetname).getRow(row).getCell(column).setCellStyle(style);

		}


		if(status.equalsIgnoreCase("blocked"))
		{

			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			wb.getSheet(sheetname).getRow(row).getCell(column).setCellStyle(style);

		}

		FileOutputStream fo=new FileOutputStream(writeExcel);

		wb.write(fo);


	}





	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub

		//ExcelFileUtil x1=new ExcelFileUtil("D://Book1.xlsx");

		//int rc=x1.rowCount("Login1");
		//int cc=x1.cellCount("Login1");

		//System.out.println(rc+"      "+cc);

		//for (int i = 1; i <=rc; i++) {

			//String user=x1.getCellData("Login1", i, 0);
			//String pass=x1.getCellData("Login1", i, 1);

			//System.out.println(user+"    "+pass);

			//x1.setCellData("Login1", i, 2, "pass", "D://Results6.xlsx");

			//x1.setCellData("Login1", i, 2, "fail", "D://Results6.xlsx");

			//x1.setCellData("Login1", i, 2, "blocked", "D://Results6.xlsx");





		}


	}


