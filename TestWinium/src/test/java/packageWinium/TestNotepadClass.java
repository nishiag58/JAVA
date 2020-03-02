package packageWinium;

import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TestNotepadClass {

     DesktopOptions options;
     WiniumDriver driver;
     WiniumDriverService service;

    @BeforeClass
    public  void setupBasics(){
        options =new DesktopOptions();
        options.setApplicationPath("C:\\Windows\\System32\\notepad.exe");
        File winDriverPath= new File("D:\\Winium\\Winium.Desktop.Driver.exe");
        service=new WiniumDriverService.Builder().usingDriverExecutable(winDriverPath).usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
        try{
            service.start();
        }
        catch (IOException e){
            e.getMessage();
            System.out.println("Exception while starting winium driver service");
            e.printStackTrace();
        }
        driver=new WiniumDriver(service,options);
    }

    @Test
    public void notepadTest(){
        driver.findElementByClassName("Edit").sendKeys("This is my first test case check fow Winium Desktop Automation tool");
    }

    @AfterClass
    public void closeBasics(){
        service.stop();
    }
}
