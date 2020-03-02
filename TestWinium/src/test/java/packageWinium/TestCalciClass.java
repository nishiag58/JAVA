package packageWinium;

import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TestCalciClass {

     WiniumDriverService service;
     WiniumDriver driver;
     DesktopOptions option;

    @BeforeTest
    public void setupWinium(){
        option=new DesktopOptions();
        option.setApplicationPath("C:\\Windows\\System32\\calc.exe");
        File driverPath=new File("D:\\Winium\\Winium.Desktop.Driver.exe");
        service=new WiniumDriverService.Builder().usingDriverExecutable(driverPath).usingPort(9999).withSilent(false).withVerbose(true).buildDesktopService();
        try {
            service.start();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        driver=new WiniumDriver(service,option);
    }
    @Test
    public void calculatorTest()throws InterruptedException{
        driver.findElementById("num1Button").click();
        driver.findElementById("num1Button").click();
        driver.findElementById("plusButton").click();
        driver.findElementById("num2Button").click();
        driver.findElementById("equalButton").click();
        Thread.sleep(4000);
        driver.findElementById("Close").click();
    }
    @AfterTest
    public  void endWinium(){
        service.stop();
    }
}
