package packageDailyBurn;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
public class TestCaseDailyBurn {

    WebDriver dr;
    ExtentReports extent;
    ExtentTest logger;
    Properties obj;
    FileInputStream fileObj;
   WebDriverWait wait;
   /* WebDriverWait waity;*/
      //Common Method for using List to store web elements
      public void listMethod(String xpathValue,String expectedValue){
        List<WebElement> option = dr.findElements(By.xpath(xpathValue));
        for(WebElement options: option) {
            Assert.assertEquals(options.getText(),expectedValue,"Strings not matched");
         }
      }

      @Parameters("browser")
      @BeforeClass
      public void launchWebsite(String browser)throws Exception{

            //Creation of extent Report and adding additional information into it.
            extent = new ExtentReports("C:\\Users\\nishi.agrwal\\IdeaProjects\\TestDailyBurn\\test-output\\ExtentReport.html", true);
            extent.addSystemInfo("Environment", "Selenium Automation Testing").addSystemInfo("Name", "Nishi Agrwal");
            extent.loadConfig(new File("C:\\Users\\nishi.agrwal\\IdeaProjects\\TestDailyBurn\\extent-config.xml"));


            //Creation for properties file
            obj= new Properties();
            fileObj = new FileInputStream("C:\\Users\\nishi.agrwal\\IdeaProjects\\TestDailyBurn\\importantData.properties");
            obj.load(fileObj);

            //Browser setup (on the basis of parameter passed)
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver",obj.getProperty("chromeDriverPath"));
                dr= new ChromeDriver();
            }else if (browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver",obj.getProperty("firefoxDriverPath"));
                dr = new FirefoxDriver();
                //else part with system error
            }
            dr.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
            wait  = new WebDriverWait(dr,40);

            //Maximize the browser window
            dr.manage().window().maximize();
            //Hit web site
            dr.get(obj.getProperty("websiteUrl"));
        }

      @Test(priority = 1)
      public void loginToWebsite() throws InterruptedException {

            logger = extent.startTest("loginToWebsite");

            //find username & password field and fill the values in it
            dr.findElement(By.xpath(obj.getProperty("usernameXpath"))).sendKeys(obj.getProperty("username"));
            dr.findElement(By.xpath(obj.getProperty("passwordXpath"))).sendKeys(obj.getProperty("password"));

/*boolean y= dr.findElement(By.id("loading")).isDisplayed();
if (y==true) {
    System.out.println("Loader is displayed on login:" + dr.findElement(By.id("loading")).isDisplayed());
}
else*/
            dr.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
            //Find the sigIn button and click it for the user to be logged in
            dr.findElement(By.xpath(obj.getProperty("buttonXpath"))).click();

            //Find the value of currently logged in username & Applying assert for checking the unique user login or not
            WebElement actualLoggedInUsername= dr.findElement(By.className("username"));
            //TO DO work on loader to check the visiblity and clickability
            Assert.assertEquals(actualLoggedInUsername.getText(), obj.getProperty("expectedLoggedInUserName"), "not unique user log in");
            logger.log(LogStatus.PASS, "Successfully logged in | Assert check True for the unique user login");

            extent.endTest(logger);
            //diff betwen thread.sleep and implicit wait


            //Thread.sleep(7000);
          //dr.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
       }

      @Test(priority = 2)
      public void workoutFilterFirst() throws InterruptedException{

            logger= extent.startTest("workoutFilterFirst");
            //find workout filter
            //Thread.sleep(7000);
           System.out.println("Loader is displayed "+ dr.findElement(By.id("loading")).isDisplayed());
            wait.until(ExpectedConditions.invisibilityOf(dr.findElement(By.id("loading"))));
          //System.out.println("Loader is displayed"+ dr.findElement(By.id("loading")).isDisplayed());
          WebElement x=wait.until(ExpectedConditions.elementToBeClickable(dr.findElement(By.xpath(obj.getProperty("workoutPath")))));
            x.click();

            //Thread.sleep(5000);
          System.out.println("Loader is displayed "+ dr.findElement(By.id("loading")).isDisplayed());
          wait.until(ExpectedConditions.invisibilityOf(dr.findElement(By.id("loading"))));

            //Find Filter element with Difficulty Medium and click it
            WebElement y= wait.until(ExpectedConditions.elementToBeClickable(dr.findElement(By.xpath(obj.getProperty("mediumPath")))));
            y.click();
            //Thread.sleep(2000);
          System.out.println("Loader is displayed "+ dr.findElement(By.id("loading")).isDisplayed());
          wait.until(ExpectedConditions.invisibilityOf(dr.findElement(By.id("loading"))));
          //Assert the type difficulty Medium of all the videos displayed
            listMethod(obj.getProperty("mediumVideosXpath"),obj.getProperty("expectedFilterFirst"));

            logger.log(LogStatus.PASS,"Workout option clicked from sidebar | Clicked Filter element with Difficulty Medium | Assert true for Workout of Difficulty Medium");
            extent.endTest(logger);
        }

      @Test(priority = 3)
      public void workoutFilterSecond() throws InterruptedException{

            logger= extent.startTest("workoutFilterSecond");

            dr.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
            //For scroll down the web page
            JavascriptExecutor downUp= (JavascriptExecutor)dr;
            downUp.executeScript("scroll(0,250)");
            //Find clear filter and click it
            dr.findElement(By.xpath(obj.getProperty("clearFilterXpath"))).click();
            //scroll up the web page
            downUp.executeScript("scroll(0,-250)");
            Thread.sleep(2000);
            dr.findElement(By.xpath(obj.getProperty("dropdownProgramXpath"))).click();
            Thread.sleep(2000);
            dr.manage().timeouts().setScriptTimeout(4,TimeUnit.SECONDS);
            dr.findElement(By.xpath(obj.getProperty("10MinProgramXpath"))).click();
            Thread.sleep(2000);
/*            //Find the dropdown element Program and click it & Select the 10-Minute Tone program and click it
            WebElement z= wait.until(ExpectedConditions.elementToBeClickable(dr.findElement(By.xpath(obj.getProperty("dropdownProgramXpath")))));
            z.click();
            System.out.println("successfully clicked dropdown");
           // Thread.sleep(2000);
          //dr.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
          System.out.println("10 min tone option from list is displayed "+ dr.findElement(By.xpath(obj.getProperty("10MinProgramXpath"))).isDisplayed());
          //WebElement ten= wait.until(ExpectedConditions.elementToBeClickable(dr.findElement(By.xpath(obj.getProperty("10MinProgramXpath")))));
          Thread.sleep(2000);
          //ten.click();
          //dr.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
          dr.findElement(By.xpath(obj.getProperty("10MinProgramXpath"))).click();*/

/*
          System.out.println("Loader is displayed "+ dr.findElement(By.id("loading")).isDisplayed());
          wait.until(ExpectedConditions.invisibilityOf(dr.findElement(By.id("loading"))));*/

            //Verifying the Program type 10-Minute Tone of all the videos displayed
            listMethod(obj.getProperty("10MinVideosXpath"),obj.getProperty("expectedFilterSecond"));

            //Click on a video displayed under the category of 10-Minute Tone program & now Verifying that the type of clicked video should be 10-Minute Tone program
            dr.findElement(By.xpath(obj.getProperty("clicked10MinVideoXpath"))).click();
            //Thread.sleep(2000);
            Assert.assertEquals(dr.findElement(By.xpath(obj.getProperty("clickedVideoTypeXpath"))).getText(),obj.getProperty("expectedClickedFilter"),"Expected clicked video with program type 10-Minute Tone not found");

            logger.log(LogStatus.PASS," scroll down | Clear Filter | scroll up | select dropdown | Assert the type of all videos | click single video and Assert its type");
            extent.endTest(logger);
          }

      @Test(priority = 4)
      public void logoutToWebsite() throws InterruptedException{

            logger =extent.startTest("logoutToWebsite");
            //Thread.sleep(2000);

            //Click the dropdown for profile section and Click the Sign out section from it using Relative xpath
            WebElement dropdown = dr.findElement(By.xpath(obj.getProperty("nameSectionXpath")));
            dropdown.click();
            Thread.sleep(2000);
  /*        dr.manage().timeouts().setScriptTimeout(6,TimeUnit.SECONDS);
 *//*         waity= new WebDriverWait(dr,40);
          waity.until(ExpectedConditions.elementToBeClickable(dropdown.findElement(By.xpath(obj.getProperty("signOutXpath")))));*//**//*
            WebDriverWait hi= new WebDriverWait(dr,20);
            hi.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("signOutXpath"))));*/
           dropdown.findElement(By.xpath(obj.getProperty("signOutXpath"))).click();
 /*         WebDriverWait as=new WebDriverWait(dr,20);
          By lo=By.xpath("//div[@class='profile-section']//section[@class='dbwf-nav-menu sub-menu']//ul//li[@class='top-link menu-item-sign-out']//a");
          List<WebElement> log =as.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(lo));
          System.out.println("Hi");
          for(WebElement options: log) {
              if(options.getText()=="Sign Out")
                  options.click();
              System.out.println("Hi");
          }*/
            logger.log(LogStatus.PASS,"User profile Dropdown clicked | Click on sign-out button | User successfully logged out");
            extent.endTest(logger);
        }

      @AfterClass
      public void closeWebsite(){
            dr.close();
            extent.flush();
            extent.close();
      }
}
