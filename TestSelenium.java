import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSelenium {

    public static void  main(String args[]){

         //set the property for webdriver consideration
         System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");

         //open chrome browser
         WebDriver driver= new ChromeDriver();

         //open website
        driver.get("https://www.diaspark.com/contact-us/");

        //locate the name field in webelement via name tag
        WebElement cname = driver.findElement(By.name("text-109"));

        //enter text into cname via sendKeys
        cname.sendKeys("dummydataset");

        //locate the company name field in webelement via name tag
        WebElement com_name = driver.findElement(By.name("text-539"));

        //enter text into company name via sendKeys
        com_name.sendKeys("testingpurposeonly");

        //locate the phone number field via Xpath selector and also allocate value into it
        driver.findElement(By.xpath("//input[@name='tel-314']")).sendKeys("1234567899");

        //locate the email field via Xpath selector and also allocate value into it
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("nishi.agarwal@diaspark.com");

        //locate the message field via tag selector (placeholder) and also allocate value into it
        driver.findElement(By.name("textarea-116")).sendKeys("it is just use for testing task");

        //locating the send button in webElement via xpath
         WebElement sendButton= driver.findElement(By.xpath("//input[@value='Send']"));

         //click event on send button
        sendButton.click();

        //waiting for website to be closed
        try {
            Thread.sleep(5000);
         }
         catch(InterruptedException e) {
            System.out.println("got interrupted!");
         }

         //close the webdriver
         driver.close();

    }
}
