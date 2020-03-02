package com.demo.proj.objRepo;

import com.demo.proj.commonUtils.CommonFunctions;
import com.demo.proj.steps.Setup;
import com.demo.proj.steps.WebDriverInstance;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProductOR extends WebDriverInstance {
    @FindBy(name = "search_query")
    private WebElement SEARCHBTN;
    @FindBy(xpath = "//div[@class='product-container']")
    private WebElement PRODHOVER;
    @FindBy(xpath = "//span[text()='More']")
    private WebElement SHOWMORE;
    @FindBy(xpath = "//div//h1[text()='Printed Summer Dress']")
    private WebElement NAMEDRESSAFTER;

    @FindBy(xpath = "//span[text()='Add to cart']")
    private WebElement ADDTOCART;
    @FindBy(xpath = "//div[@class='layer_cart_product_info']//span[text()='Printed Summer Dress']")
    private WebElement SELECTEDPROD;
    @FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")
    private WebElement PROCEEDCHKOUT;
    @FindBy(xpath = "//span[text()='Proceed to checkout']")
    private WebElement PROCEEDCHKOUT2;
    @FindBy(name="cgv")
    private WebElement TERMCOND;
    @FindBy(xpath = "//button[@name='processCarrier']//span")
    private WebElement PROCEEDCHKOUT3;
    @FindBy(xpath = "//a[@class='bankwire']")
    private WebElement BANKPAY;
    @FindBy(xpath = "//button//span[text()='I confirm my order']")
    private WebElement CONFIRMORDER;

    public ProductOR(WebDriver driver)throws Exception{super(driver);}

    public void ProductSelection()throws Exception{

        this.SEARCHBTN.sendKeys("dress", Keys.ENTER);
        CommonFunctions.threadWait();
        CommonFunctions.hoverFunction(PRODHOVER);
        CommonFunctions.implicitWait();
        this.SHOWMORE.click();
        //VERIFYING DETAILS OF PRODUCT AFTER CLICK ON SHOW MORE
        CommonFunctions.AssertFunction(this.NAMEDRESSAFTER,"Printed Summer Dress");
        this.ADDTOCART.click();
    }

    public void AddToCart()throws Exception{
        //ADD TO CART
        this.ADDTOCART.click();
        //TEST CASE 4- VERIFYING SELECTED PRODUCT IN THE CART
        CommonFunctions.AssertFunction(this.SELECTEDPROD,"Printed Summer Dress");
        //PAYMENT ACTIONS
        this.PROCEEDCHKOUT.click();
        this.PROCEEDCHKOUT2.click();
        this.PROCEEDCHKOUT2.click();
        this.TERMCOND.click();
        this.PROCEEDCHKOUT3.click();
        this.BANKPAY.click();
        this.CONFIRMORDER.click();
    }
}
