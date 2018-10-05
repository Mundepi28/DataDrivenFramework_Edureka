package HybridFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;

import java.util.Set;

public class PaymentObjects {

    static WebDriver driver;

    //Declaring instance variables
    @FindBy(xpath = "//select[@id='countrySelector']")
    WebElement ctry;


    //constructor to instantiate the instance variables using Page Factory
    public PaymentObjects(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //selecting country of payment as Sweden
    public void selectCountry() {

        String parentHandle = driver.getWindowHandle();
        System.out.println("********* "+parentHandle);

        Set<String> handles = driver.getWindowHandles();
        System.out.println("********* "+handles);

        for (String winHandle : handles) {
            if (!winHandle.equalsIgnoreCase(parentHandle)) {
            driver.switchTo().window(winHandle);

            Select country = new Select(ctry);
            country.selectByVisibleText("Sweden");
            }//if loop closed

        }//for loop closed

        //closing the new window
        driver.close();

        //switching back to the parent window
        driver.switchTo().window(parentHandle);
    }


}
