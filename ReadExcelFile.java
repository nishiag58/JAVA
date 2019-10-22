package com.diaspark;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	Sheet datasetSheet;
	static Workbook dataSetWorkbook;

	//ArrayList for storing bloodGroup of employees
	List<String> bloodGroup=new ArrayList<String>();

	//ArrayList for storing names of employees
	List<String> name=new ArrayList<String>();

	//ArrayList for storing details of employees(comprises names and bloodGroup)
	static List<String> details=new ArrayList<String>();
	
	public void readExcel(String filePath,String fileName,String sheetName)throws IOException{
		
		//object of File class to open excel file
		File file= new File(filePath+"\\"+fileName);
		
		//object of FileInputStream class to read excel file
		FileInputStream inputStream =new FileInputStream(file);
		
		
		dataSetWorkbook= null;
		
		//creating workBook 
		dataSetWorkbook= new XSSFWorkbook(inputStream);
		
		//Read sheet inside the workbook by its name
		datasetSheet= dataSetWorkbook.getSheet(sheetName);
		
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
	
	
	public static void ConvertJavaToExcel(List<String> details)
    {
        try
        {
        	//file path specify
            String excelPath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\diaspark\\Exceldata.xlsx";
           
            FileOutputStream fileOutputStream = new FileOutputStream(new File(excelPath));

            // Create Workbook instance holding file
            XSSFWorkbook workbook = new XSSFWorkbook();

            // Create a new sheet
            XSSFSheet sheet = workbook.createSheet("New");
            
            int rownum = 0;
            
            System.out.println(details.size());
            
            for(int data=0;data<details.size();data++)
            {
                Row row = sheet.createRow(rownum++);
                
                int cellnum = 0;
                
                //split the data stored in arrayList as a single word
                String[] words=details.get(data).split(":");
                
                //loop for storing data into cells ,run upto total no. of words
                for(int columns=0;columns<words.length;columns++)
                	
                	row.createCell(cellnum++).setCellValue(words[columns]);
                    
                 System.out.println(rownum);
                
              
            }
            //Write workbook into the excel
            workbook.write(fileOutputStream);
            
            //Close the workbook
            fileOutputStream.close();
            
        } catch (IOException ie)
           {
            ie.printStackTrace();
           }
    }	
	
	
	//Main Method
	public static void main(String[] args)throws IOException {
		
		//object create
		ReadExcelFile objExcelFile =new ReadExcelFile();

		//Path of excel file
		String filePath= System.getProperty("user.dir")+"\\src\\main\\java\\com\\diaspark";
		
		//call method readExcel for read the file 
		objExcelFile.readExcel(filePath, "Exceldata.xlsx", "Newsheet");

        System.out.println(details.size());

        //object create
		ReadExcelFile objExcelWFile =new ReadExcelFile();
		
		//call method ConvertJavaToExcel for write the file 
		objExcelWFile.ConvertJavaToExcel(details);
	}

}
