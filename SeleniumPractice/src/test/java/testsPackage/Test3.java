package testsPackage;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

import java.util.List;

@Listeners(testsPackage.Test4.class)
public class Test3 {

    /**
    * @author Nishi.Agrwal
    * This test is for calender handling of the website seleniumpractise.
    * In this test case ,log files are also generated using log4J properties.
    * */

    WebDriver driver;
    @Test
    public void calenderHandling(){
        try {
            Logger log = Logger.getLogger("Test3");
            PropertyConfigurator.configure("log4j.properties");
            System.setProperty("webdriver.chrome.driver","D:\\driver\\chromedriver.exe");
            driver=new ChromeDriver();
            driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-handle-calendar-in-selenium.html");
            log.info("website Opened");
            Assert.assertEquals(driver.getTitle(),"Selenium Practise: How to handle calendar in Selenium Webdriver","found");

            //DIRECT METHOD FOR ENTERING DATE
            WebElement box= driver.findElement(By.id("datepicker"));
            box.sendKeys("05/08/1997");
            box.clear();
            Thread.sleep(2000);

            //SECOND METHOD FOR SELECTING DATE FROM CALENDER
            box.click();
            log.info("textbox selected");
            driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e'][text()='Next']")).click();
            List<WebElement> listOfDates=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody//tr/td[@data-year='1997']/a"));
            for (WebElement dateRequired:listOfDates){
                String dateText= dateRequired.getText();
                if(dateText.equalsIgnoreCase("5")){
                dateRequired.click();
                System.out.println("Choosed 5th date of the calender 1997");
                }
            }
            log.info("Choosed 5th date of the calender year 1997");
            driver.close();
            driver.quit();
        }
        catch (InterruptedException ex){
                System.out.println(ex.getMessage());
        }
    }

}
