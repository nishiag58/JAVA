package packageSlimware;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriverService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.winium.WiniumDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestCaseSlimware {

    Properties file;
    FileInputStream fileInpObj;
    WebDriver webDriver;
    ChromeOptions cWebOptions;
    ChromeOptions cWebOptions2;
    WiniumDriver winDriver;
    DesktopOptions winOptions;
    WiniumDriverService service;
    ExtentReports extent;
    ExtentTest logger;
    WebDriver webDriver2;
    Robot ro;

    @Parameters("browser")
    @BeforeClass
    public void winDriverSetup(String browser) throws InterruptedException,IOException {

        //Creation of extent Report and adding additional information into it.
        extent = new ExtentReports("C:\\Users\\nishi.agrwal\\IdeaProjects\\TestSlimware\\test-output\\ExtentReport.html", true);
        extent.addSystemInfo("Environment", "Selenium & Winium Automation Testing").addSystemInfo("Name", "Nishi Agrwal");
        extent.loadConfig(new File("C:\\Users\\nishi.agrwal\\IdeaProjects\\TestSlimware\\extent-config.xml"));

        //Initializing the File where the field data placed
        file=new Properties();
        fileInpObj=new FileInputStream("C:\\Users\\nishi.agrwal\\IdeaProjects\\TestSlimware\\dataFile.properties");
        file.load(fileInpObj);

        if(browser.equalsIgnoreCase("chrome")) {
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("download.default_directory", file.getProperty("downloadedFilePath"));
            prefs.put("safebrowsing.enabled", "false");
            prefs.put("download.prompt_for_download", "false");

            // Adding capabilities to ChromeOptions
            cWebOptions = new ChromeOptions();
            cWebOptions.setExperimentalOption("prefs", prefs);

            //Initialize WebDriver
            System.setProperty("webdriver.chrome.driver", file.getProperty("chromeDriverPath"));
            webDriver = new ChromeDriver(cWebOptions);
        }

        //Perform task over web browser
        webDriver.manage().window().maximize();
        webDriver.get(file.getProperty("websiteLink"));

        //Assertion applied for checking the headline of the web site
        WebElement actualWebsiteHeadline= webDriver.findElement(By.xpath(file.getProperty("actualWebsiteHeadlineXpath")));
        Assert.assertEquals(actualWebsiteHeadline.getText(),file.getProperty("expectedWebsiteHeadline"),"Headline for the website found wrong");
        if(browser.equalsIgnoreCase("chrome")) {
            webDriver.findElement(By.xpath(file.getProperty("downloadButtonXpath"))).click();
            Thread.sleep(5000);
        }

        //Initialize Windows driver
        winOptions = new DesktopOptions();
        winOptions.setDebugConnectToRunningApp(true);
        File winDriverPath = new File(file.getProperty("winiumDriverPath"));
        service = new WiniumDriverService.Builder().usingDriverExecutable(winDriverPath).usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
        try {
            service.start();
        } catch (IOException e) {
            System.out.println("Hi EXCEPTION IS " + e.getMessage());
        }
        winDriver = new WiniumDriver(service, winOptions);
    }

     @Test(priority = 1)
     public void winDriverInstallation()throws InterruptedException,AWTException{

        //Report start for test case 1
        logger=extent.startTest("winDriverInstallation");

        //Installation procedure of driver gets start
        boolean s= winDriver.findElementByName("Downloads bar").isEnabled();
        System.out.println(s);

        //Assertions in windows driver
        Assert.assertNotEquals(winDriver.findElementByName("Downloads bar"),"Downloads bar");
        winDriver.findElementByName("Options menu").click();
        winDriver.findElementByName("Open").click();
        Thread.sleep(5000);

        //Close the web driver
        webDriver.close();

        //Minimize the Driver installer dialog box
        winDriver.findElementByName("Minimize").click();

        //Opening the command prompt
        winDriver.findElementByName("Command Prompt").click();
        ro =new Robot();
        winDriver.findElementById("Text Area").sendKeys("chrome.exe --remote-debugging-port=9222");
        ro.keyPress(KeyEvent.VK_ENTER);

        //Installation starts from here
        winDriver.findElementByName("DriverUpdate Setup Wizard – 1 running window").click();
        winDriver.findElementByName("I Agree").click();
        Thread.sleep(1000);
        winDriver.findElementByName("Accept & Inst&all").click();
        Thread.sleep(35000);

        //Close McaFee dialog box
        winDriver.findElement(By.name("Close")).click();
        Thread.sleep(2000);

        winDriver.findElementByName("DriverUpdate Help – 1 running window").click();
        Thread.sleep(2000);
        winDriver.findElementByName("Register Now").click();
        Thread.sleep(2000);
        winDriver.findElementByName("Get Registration Key").click();
        Thread.sleep(2000);

        logger.log(LogStatus.PASS,"Download bar check | Opening the command prompt and launch chrome at 9222 port | " +
                "Accept and install the driver | Get Registration key");
        //Report end for test case 1
         extent.endTest(logger);

    }

    @Test(priority = 2)
    public void winDriverRegistration()throws InterruptedException{

        //Report start for test case 2
        logger=extent.startTest("winDriverRegistration");

        //Again initialise the web driver
        System.setProperty("webdriver.chrome.driver", file.getProperty("chromeDriverPath"));
        cWebOptions2 = new ChromeOptions();
        //Look for chrome launched at localhost with 9222 port
        cWebOptions2.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        webDriver2 = new ChromeDriver(cWebOptions2);
        System.out.println(webDriver2.getTitle());

        //Insert the data required
        webDriver2.findElement(By.xpath(file.getProperty("emailFieldXpath"))).sendKeys("nishii.agarwal@diaspark.com");
        Thread.sleep(1000);
        webDriver2.findElement(By.xpath(file.getProperty("continueButtonXpath"))).click();

        //Close chrome browser window
        winDriver.findElementByName("Maximize").click();
        winDriver.findElementByName("Close").click();

        //Close the already opened winDriver tabs
        winDriver.findElementByName("DriverUpdate Help – 1 running window").click();
        winDriver.findElementById(file.getProperty("registrationKeyCloseIdentifier")).click();
        winDriver.findElementById(file.getProperty("driverCloseIdentifier")).click();
        //Close CMD
        winDriver.findElementByName("Close").click();
        Thread.sleep(2000);

        logger.log(LogStatus.PASS,"Again initialise the web driver at 9222 port | Fill the registration form via link provided by the installation of driver" +
                "| Close all the unused application tabs");
        //Report end for test case 2
        extent.endTest(logger);
    }

    @Test(priority = 3)
    public void unInstallApplication()throws InterruptedException{

        //Start the report for test case 3
        logger=extent.startTest("unInstallApplication");

        //unInstallation of driver update
        winDriver.findElementByName("Control Panel").click();
        Thread.sleep(2000);
        winDriver.findElementByName("Uninstall a program").click();
        Thread.sleep(6000);

        boolean app = winDriver.findElementByName("DriverUpdate").isDisplayed();

        if(app!=false) {
            winDriver.findElementByName("DriverUpdate").click();
            //ro.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            winDriver.findElementByName("Uninstall").click();
            Thread.sleep(7000);
            winDriver.findElementByName("Close").click();
        }
        logger.log(LogStatus.PASS,"Successfully uninstall the application via control panel");
        //Report end for test case 3
        extent.endTest(logger);
    }

    @AfterClass
    public void closeApplication(){
        service.stop();
        extent.flush();
        extent.close();
    }
}
