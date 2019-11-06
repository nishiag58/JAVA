import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class TestContactus {

    String driverPathSet= "C:\\driver\\chromedriver.exe";
    public String webUrl="https://www.diaspark.com/contact-us/";
    public WebDriver driver;

       @BeforeClass
       public void StartWeb(){

        System.setProperty("webdriver.chrome.driver",driverPathSet);
        driver =new ChromeDriver();
        driver.get(webUrl);
       }

       @Test(priority = 1)
        public  void NameAdd(){
       //locate the name field in webelement via name tag
        WebElement cname = driver.findElement(By.name("text-109"));

       //enter text into cname via sendKeys
        cname.sendKeys("dummydataset");
       }

       @Test(priority = 2)
       public  void CompanyAdd(){
         //locate the company name field in webelement via name tag
         WebElement com_name = driver.findElement(By.name("text-539"));

         //enter text into company name via sendKeys
         com_name.sendKeys("testingpurposeonly");

       }
       @Test(priority = 3)
       public  void PhoneAdd(){
         //locate the phone number field via Xpath selector and also allocate value into it
          driver.findElement(By.xpath("//input[@name='tel-314']")).sendKeys("1234567899");


       }
        @Test(priority = 4)
        public  void EmailAdd(){
            //locate the email field via Xpath selector and also allocate value into it
            driver.findElement(By.xpath("//input[@type='email']")).sendKeys("nishi.agarwal@diaspark.com");
        }
        @Test(priority = 5)
        public  void MessageAdd(){
            //locate the message field via tag selector (placeholder) and also allocate value into it
            driver.findElement(By.name("textarea-116")).sendKeys("it is just use for testing task");
        }
        @Test(priority = 6)
        public  void SendClick() throws  InterruptedException{
            //locating the send button in webElement via xpath
            WebElement sendButton = driver.findElement(By.xpath("//input[@value='Send']"));

            //click event on send button
            sendButton.click();

            //waiting for website to be closed

            Thread.sleep(20000);
        }


       @AfterClass
         public void CloseWeb(){
         driver.close();
       }
}
