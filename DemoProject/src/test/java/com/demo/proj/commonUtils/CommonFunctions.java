package com.demo.proj.commonUtils;

import com.demo.proj.steps.Setup;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class CommonFunctions extends Setup {

    static Faker fakeData=new Faker();

    public static void implicitWait()throws Exception{
        Setup.driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }
    public static void threadWait()throws Exception{
        Thread.sleep(2000);
    }
    public static String rndEmail()throws Exception{
        String newEmail= "email" +Math.round(Math.random()*186)+"@test.com";
        return newEmail;
    }
    public static String rndFName()throws Exception{
        String fakeFName = fakeData.name().firstName();
        return fakeFName;
    }
    public static String rndLName()throws Exception{
        String fakeLName = fakeData.name().lastName();
        return fakeLName;
    }
    public static String rndPassword()throws Exception{
        String fakeLName = fakeData.letterify("adhelnsfj",true);
        return fakeLName;
    }
    public static String rndMobile()throws Exception{
        String rndMobNo=Math.round(Math.random()*100000)+"23789";
        return rndMobNo;
    }
    public static String rndCompany()throws Exception{
        String rndComp=fakeData.company().name();
        return rndComp;
    }
    public static String rndAddress1()throws Exception{
        String rndAdd1=fakeData.address().streetAddress();
        return rndAdd1;
    }
    public static String rndAddress2()throws Exception{
        String rndAdd2=fakeData.address().buildingNumber();
        return rndAdd2;
    }
    public static String rndCity()throws Exception{
        String rndCity=fakeData.address().city();
        return rndCity;
    }
    public static void dropDown(WebElement listEle)throws Exception{
        Select selectDropDown=new Select(listEle);
        selectDropDown.selectByIndex(2);
    }
    public static void AssertFunction(WebElement anyEle,String expectedValue)throws Exception{
        String actualValue= anyEle.getText();
        Assert.assertEquals(actualValue,expectedValue,"Not found");
    }
    public static void hoverFunction(WebElement actEle)throws Exception{
        Actions act = new Actions(Setup.driver);
        act.moveToElement(actEle).build().perform();
    }
}
