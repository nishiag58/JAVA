package com.demo.proj.objRepo;

import com.demo.proj.commonUtils.CommonFunctions;
import com.demo.proj.steps.Setup;
import com.demo.proj.steps.WebDriverInstance;
import com.opencsv.CSVReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.FileReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegisterOR extends WebDriverInstance {

    @FindBy(xpath = "//a[@class=\"login\"]")
    private WebElement SIGNIN;
    @FindBy(id = "email_create")
    private WebElement EMAIL_TXTBOX;
    @FindBy(id = "SubmitCreate")
    private WebElement SUBMIT_BTN;
    @FindBy(id="id_gender2")
    private WebElement GNDER_RADIOBTN;
    @FindBy(id = "customer_firstname")
    private WebElement FNAME;
    @FindBy(id = "customer_lastname")
    private WebElement LNAME;
    @FindBy(name = "passwd")
    private WebElement PASSWORD;
    @FindBy(id = "days")
    private WebElement DAYPICKER ;
    @FindBy(id ="months")
    private WebElement MONTHPICKER;
    @FindBy(id = "years")
    private WebElement YEARPICKER;
    @FindBy(id = "company")
    private WebElement CMPNY_NAME;
    @FindBy(id = "address1")
    private WebElement ADD1;
    @FindBy(id="address2")
    private WebElement ADD2;
    @FindBy(id="city")
    private WebElement CITY;
    @FindBy(id = "id_state")
    private WebElement STATE;
    @FindBy(id="postcode")
    private WebElement POSTCODE;
    @FindBy(name="phone_mobile")
    private WebElement MOBNUM;
    @FindBy(xpath = "//input[@id='alias']")
    private WebElement ADDLINE;
    @FindBy(name = "submitAccount")
    private WebElement REGISTERBTN;

  public RegisterOR(WebDriver driver)throws Exception{super(driver);}

  public void Registration()throws Exception{
      this.SIGNIN.click();
      CommonFunctions.implicitWait();
      this.EMAIL_TXTBOX.sendKeys(CommonFunctions.rndEmail());
      this.SUBMIT_BTN.click();
      CommonFunctions.implicitWait();
      this.GNDER_RADIOBTN.click();
      this.FNAME.sendKeys(CommonFunctions.rndFName());
      this.LNAME.sendKeys(CommonFunctions.rndLName());
      this.PASSWORD.sendKeys(CommonFunctions.rndPassword());
      CommonFunctions.dropDown(this.DAYPICKER);
      CommonFunctions.dropDown(this.MONTHPICKER);
      CommonFunctions.dropDown(this.YEARPICKER);
      this.CMPNY_NAME.sendKeys(CommonFunctions.rndCompany());
      this.ADD1.sendKeys(CommonFunctions.rndAddress1());
      this.ADD2.sendKeys(CommonFunctions.rndAddress2());
      this.CITY.sendKeys(CommonFunctions.rndCity());
      CommonFunctions.dropDown(this.STATE);
      this.POSTCODE.sendKeys("90002");
      this.MOBNUM.sendKeys(CommonFunctions.rndMobile());
      this.ADDLINE.sendKeys(CommonFunctions.rndAddress1());
      this.REGISTERBTN.click();
      CommonFunctions.threadWait();
  }
  public void csvReader()throws Exception{
      int lineNum = 1;
      CSVReader read =new CSVReader(new FileReader("D:\\selenium test clone\\DemoProject\\src\\test\\java\\com\\demo\\proj\\files\\testing.csv"));
      List<String[]> listCsv= read.readAll();
      System.out.println("GOING TO DISPLAY CSV FILE DATA");
      for (String[] csvDataSet:listCsv) {
          System.out.print("Data line " + lineNum++);
          for (String csvData: csvDataSet ) {
              System.out.print(" "+ csvData+" , ");
          }
          System.out.println();
      }
  }

}
