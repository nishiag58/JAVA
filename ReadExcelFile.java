package com.diaspark;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	public void readExcel(String filePath,String fileName,String sheetName)throws IOException{
		
		//ArrayList for storing names of employees
		List<String> name=new ArrayList<String>();
		
		//ArrayList for storing bloodGroup of employees
		List<String> bloodGroup=new ArrayList<String>();
		
		//ArrayList for storing details of employees(comprises names and bloodGroup)
		List<String> details=new ArrayList<String>();
		
		//object of File class to open excel file
		File file= new File(filePath+"\\"+fileName);
		
		//object of FileInputStream class to read excel file
		FileInputStream inputStream =new FileInputStream(file);
		
		
		Workbook dataSetWorkbook= null;
		
		//creating workBook 
		dataSetWorkbook= new XSSFWorkbook(inputStream);
		
		//Read sheet inside the workbook by its name
		Sheet datasetSheet= dataSetWorkbook.getSheet(sheetName);
		
		//find no. of rows in the excel file
		int rowCount= datasetSheet.getLastRowNum()-datasetSheet.getFirstRowNum();
		  
		//Loop for read data 
		for(int row=0 ; row< rowCount+1 ; row++) {
			 
			Row dataSetRow= datasetSheet.getRow(row);
			 
			 for(int cell=0; cell< dataSetRow.getLastCellNum();cell++) {
				
				// System.out.println(dataSetRow.getCell(cell).getStringCellValue()+":");
				 
				 if(cell%2==0)
					 name.add(dataSetRow.getCell(cell).getStringCellValue());
				 else
					 bloodGroup.add(dataSetRow.getCell(cell).getStringCellValue());
			   }

		}
		
		//display excel sheet data in ArrayList
		for(int listData=0; listData<name.size(); listData++)
			details.add(name.get( listData)+" : "+bloodGroup.get( listData));
		    System.out.println("Details are: "+details);
		
		//sort the data values alphabetically
		   Collections.sort(details);
		   System.out.println("Sorted Details are: "+details);
	}
	
//	<dependencies>
//	   <dependency>
//	     <groupId>org.apache.poi</groupId>
//	     <artifactId>poi</artifactId>
//	     <version>3.9</version>
//	     </dependency>
//	     
//	     <dependency>
//	    <groupId>org.apache.poi</groupId>
//	    <artifactId>poi-ooxml</artifactId>
//	    <version>3.9</version>
//	    </dependency>
//	  </dependencies>

	//Main Method
	public static void main(String[] args)throws IOException {

		ReadExcelFile objExcelFile =new ReadExcelFile();

		//Path of excel file
		String filePath= System.getProperty("user.dir")+"\\src\\main\\java\\com\\diaspark";
		
		//call method readExcel for read the file 
		objExcelFile.readExcel(filePath, "Exceldata.xlsx", "Employeedata");

	}

}
