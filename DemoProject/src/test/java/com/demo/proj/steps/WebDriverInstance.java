package com.demo.proj.steps;

import com.demo.proj.commonUtils.CommonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WebDriverInstance {

    WebDriver driver;
    public String BASE_URL= "http://automationpractice.com/index.php";

  public WebDriverInstance(WebDriver driver)throws Exception{
      driver.get(BASE_URL);
      CommonFunctions.implicitWait();
      driver.manage().window().maximize();
      this.driver=driver;
      PageFactory.initElements(driver,this);
  }
}
