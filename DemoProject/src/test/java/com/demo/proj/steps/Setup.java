package com.demo.proj.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class Setup {

    public static WebDriver driver;

    public static final String CHROME_PROP_KEY ="webdriver.chrome.driver";
    public static final String CHROME_PROP_VALUE="D:\\selenium test clone\\DemoProject\\src\\main\\java\\com\\demo\\proj" +
                                                    "\\chromedriver.exe";

    public static final String FIREFOX_PROP_KEY ="webdriver.gecko.driver";
    public static final String FIREFOX_PROP_VALUE="D:\\selenium test clone\\DemoProject\\src\\main\\java\\com\\demo\\proj" +
                                                    "\\geckodriver.exe";

    @BeforeTest
    @Parameters({"browser"})
    public static void SetupDriver(String browser)throws Exception{

       /* FileInputStream file=new FileInputStream("D:\\selenium test clone\\DemoProject\\src\\test\\java\\com\\demo\\proj\\files\\data.properties");
        Properties prop=new Properties();
        prop.load(file);*/

        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty(CHROME_PROP_KEY,CHROME_PROP_VALUE);
            driver=new ChromeDriver();
            driver.manage().deleteAllCookies();
        }
        else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty(FIREFOX_PROP_KEY,FIREFOX_PROP_VALUE);
            driver=new FirefoxDriver();
            driver.manage().deleteAllCookies();
        }
    }

   /* @AfterTest
    public void CloseDriver()throws Exception{
        driver.close();
    }*/
}
