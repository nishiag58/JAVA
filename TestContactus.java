import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestContactus {

    public String baseUrl = "https://www.diaspark.com/contact-us/";
    String driverPath = "C:\\driver\\chromedriver.exe";
    public WebDriver driver;

    @BeforeTest
    public void launchBrowserAndWeb() {
        //set the property for webdriver consideration
        //System.setProperty("webdriver.gecko.driver", "C:\\driver\\geckodriver.exe");

        //set the property for webdriver consideration
        System.setProperty("webdriver.chrome.driver", driverPath);

        //open chrome browser
        driver = new ChromeDriver();

        //open website
        driver.get(baseUrl);
    }


    @Test
    public void MyFirstTest() throws InterruptedException {

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
        WebElement sendButton = driver.findElement(By.xpath("//input[@value='Send']"));

        //click event on send button
        sendButton.click();

        //waiting for website to be closed

        Thread.sleep(20000);
    }

    @AfterTest
    public  void CloseBrowser() {
        //close the webdriver
        driver.close();
    }
}


