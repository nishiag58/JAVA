package testsPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class Test2 {

    /**
     * @author Nishi.Agrwal
     *This test case class is for handling table's rows and columns using selenium.
     *In this test case, i fetched data at a specific location in the row and columns of the table.
     * */

    WebDriver driver;
    @Test
    public void tableLocator(){
        System.setProperty("webdriver.chrome.driver","D:\\driver\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/challenging_dom");

        //FIRST METHOD
        WebElement s1= driver.findElement(By.xpath("//*[@id='content']/div/div/div/div[2]/table/tbody/tr[3]"));
        System.out.println(s1.getText());
        WebElement s2= driver.findElement(By.xpath("//*[@id='content']/div/div/div/div[2]/table/tbody/tr[6]"));
        System.out.println(s2.getText());

        //SECOND METHOD
        List<WebElement> rows=driver.findElements(By.xpath("//*[@id='content']/div/div/div/div[2]/table/tbody/tr"));
        System.out.println(rows.size());
        int index3=3;
        System.out.println(rows.get(index3).getText());
        int index6=6;
        System.out.println(rows.get(index6).getText());
        driver.close();
        driver.quit();
    }
}
