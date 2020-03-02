package com.demo.proj.Controls;

import com.demo.proj.objRepo.ProductOR;
import com.demo.proj.objRepo.RegisterOR;
import com.demo.proj.steps.Setup;
import org.testng.annotations.Test;

public class TestClass extends Setup{

    @Test(priority = 1)
    public void TestCase1()throws Exception{
        RegisterOR objOr=new RegisterOR(driver);
        objOr.Registration();
        objOr.csvReader();
    }
    @Test(priority = 2)
    public void TestCase2()throws Exception{
        ProductOR objOr2=new ProductOR(driver);
        objOr2.ProductSelection();
        objOr2.AddToCart();
    }

}
