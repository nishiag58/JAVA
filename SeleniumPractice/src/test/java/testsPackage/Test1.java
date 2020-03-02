package testsPackage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Test1 {

    /**
     * @author Nishi.Agrwal
     *This test case is for ToolSqa website modules automation in which i performed practice
     * tests related to windows navigation handling, complete form filling automation,
     * alert handling in selenium and IFrames handling.
     * */

    WebDriver driver;
    JavascriptExecutor js;
    Actions act;
    Properties prop;
    FileInputStream obj;


    @BeforeTest
     public void websiteStart()throws IOException {
        prop=new Properties();
        obj=new FileInputStream("C:\\Users\\nishi.agrwal\\IdeaProjects\\SeleniumPractice\\dataFile.properties");
        prop.load(obj);
        System.setProperty("webdriver.chrome.driver",prop.getProperty("chromeDriverPath"));
        driver = new ChromeDriver();
    }

    @Test(priority = 1)
    public void actionsOnWebsite()throws InterruptedException{
        driver.manage().window().maximize();
        driver.get(prop.getProperty("webLink"));
        Thread.sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("acceptButton"))).click();
        driver.findElement(By.xpath(prop.getProperty("homeButton"))).click();
        js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,300)");
        Thread.sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("seLogo"))).click();
    }

    @Test(priority = 2)
    public void handlingCheckBoxRadioButton()throws InterruptedException{
        js.executeScript("window.scrollBy(0,400)");
        driver.findElement(By.xpath(prop.getProperty("linkCheckboxRadioButton"))).click();
        Thread.sleep(2000);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assert.assertEquals(driver.getCurrentUrl(),prop.getProperty("expectedFirstUrl"),"not found");
        js.executeScript("window.scrollBy(0,640)");
        driver.findElement(By.linkText(prop.getProperty("practicePageLink"))).click();
        driver.close();

        ArrayList<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs1.get(1));
        Assert.assertEquals(driver.getCurrentUrl(),prop.getProperty("expectedSecondUrl"),"Not found");
        js.executeScript("window.scrollBy(0,250)");
    }

    @Test(priority = 3)
    public void practiceAutomationForm()throws InterruptedException{
        //Input name data
        System.out.println(driver.findElement(By.xpath(prop.getProperty("tagLineXpath"))).getText());

        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys("Test dummy fname");
        driver.findElement(By.xpath(prop.getProperty("lastNameXpath"))).sendKeys("Test dummy lname");
        js.executeScript("window.scrollBy(0,100)");
        //Radio Buttons selector
        driver.findElement(By.xpath(prop.getProperty("genderRadioButton"))).click();
        driver.findElement(By.xpath(prop.getProperty("experienceRadioButton"))).click();
        //Input date
        driver.findElement(By.xpath(prop.getProperty("dateXpath"))).sendKeys("05-08-1997");
        js.executeScript("window.scrollBy(0,70)");
        //Select automation Tester
        driver.findElement(By.xpath(prop.getProperty("professionXpath"))).click();
        //Upload file
        driver.findElement(By.xpath(prop.getProperty("uploadFileButton"))).sendKeys("C:\\Users\\nishi.agrwal\\Documents\\db.txt");
        //Download file
        driver.findElement(By.xpath(prop.getProperty("downloadFileButton"))).click();
        js.executeScript("window.scrollBy(0,70)");
        driver.findElement(By.xpath(prop.getProperty("technologyXpath"))).click();
        //Dropdown Selection
        js.executeScript("window.scrollBy(0,200)");
        driver.findElement(By.xpath(prop.getProperty("continentsXpath"))).click();
        driver.findElement(By.xpath(prop.getProperty("countryXpath"))).click();
        driver.findElement(By.xpath(prop.getProperty("mulContinentsXpath"))).click();
        driver.findElement(By.xpath(prop.getProperty("australiaXpath"))).click();
        js.executeScript("window.scrollBy(0,100)");
        driver.findElement(By.xpath(prop.getProperty("buttonXpath"))).click();
        Thread.sleep(3000);
        System.out.println( driver.getCurrentUrl().toUpperCase());
    }

    @Test(priority = 4)
    public  void handlingAlert()throws InterruptedException{
        //Hover action over elements
        act=new Actions(driver);
        WebElement demo= driver.findElement(By.xpath(prop.getProperty("demoButton")));
        act.moveToElement(demo).moveToElement(driver.findElement(By.xpath(prop.getProperty("optionHandlingAlertXpath")))).click().build().perform();
        Thread.sleep(3000);
        //Simple Alert
        driver.findElement(By.xpath(prop.getProperty("simpleAlertXpath"))).click();
        Alert alert1=driver.switchTo().alert();
        System.out.println("alert1 message is "+ alert1.getText());
        alert1.accept();
        js.executeScript("window.scrollBy(0,250)");

        //Confirmation Alert
        driver.findElement(By.xpath(prop.getProperty("confirmationAlertXpath"))).click();
        Alert alert2=driver.switchTo().alert();
        System.out.println("alert2 message is "+ alert2.getText());
        alert2.accept();
        Thread.sleep(2000);
        //Prompt Alert
        driver.findElement(By.xpath(prop.getProperty("popUpAlertXpath"))).click();
        Alert alert3=driver.switchTo().alert();
        System.out.println("alert3 message is "+alert3.getText());
        alert3.sendKeys("yes");
        alert3.accept();
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void handlingIFrames()throws InterruptedException{
        WebElement butDemo= driver.findElement(By.xpath(prop.getProperty("demoButtonTwo")));
        act.moveToElement(butDemo).moveToElement(driver.findElement(By.xpath(prop.getProperty("optionIFrameXpath")))).click().build().perform();
        js.executeScript("window.scrollBy(0,250)");

        //Switch to first frame in the web page using WEB ELEMENT
        WebElement frame1Ele=driver.findElement(By.id("IF1"));
        driver.switchTo().frame(frame1Ele);
        Thread.sleep(4000);
        System.out.println(driver.findElement(By.xpath(prop.getProperty("iFrameFirstContainerXpath"))).getText());
        driver.findElement(By.xpath(prop.getProperty("iFrameFirstLink"))).click();
        Thread.sleep(3000);
        //Switch to main frame
        driver.switchTo().defaultContent();
        js.executeScript("window.scrollBy(0,600)");
        //Switch to second frame in the web page using NAME attribute
       // driver.switchTo().frame("iframe2");
        //Thread.sleep(2000);
        //driver.findElement(By.xpath("//div[@id='logo-events']//a[@href='https://www.toolsqa.com/']//img")).click();
        //WebElement headerCheck=driver.findElement(By.xpath("//h3[@class='widget-title']"));
        //Assert.assertEquals(headerCheck.getText(),"Interactions","Not found");
        //js.executeScript("window.scrollBy(0,200)");
        //Working on toolSqa web automation-
        //increase the code readilibity by adding user repository into it.
        //More bug fixing
        Thread.sleep(3000);

    }
    @AfterTest
    public void websiteClose(){
        driver.close();
        driver.quit();
    }
}
