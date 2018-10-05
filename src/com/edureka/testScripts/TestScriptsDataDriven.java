package com.edureka.testScripts;

import com.edureka.utility.ReadExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestScriptsDataDriven {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void launchEdureka() {

        //Launching chrome browser
        System.setProperty("webdriver.chrome.driver", ".//driver_executables/chromedriver");
        driver = new ChromeDriver();

        //pageLoadTimeout and  implicit wait
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);


        //initializing explicit wait
        wait = new WebDriverWait(driver, 10);


        //Navigating to Edureka website
        driver.get("https://www.edureka.co");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    //test to perform the login test and getting username and password from the utility package
    @Test(priority = 0)

    public void loginCheck() throws IOException {

            //passing parameters as filename and sheetname to the static method getData in ReadExcel class
            String[][] data = ReadExcel.getData("TestExcel.xlsx", "Sheet1");

            for (int i=1; i<data.length; i++)
            {
                String username = data[i][0];
                String password = data[i][1];

                driver.findElement(By.linkText("Log In")).click();

                wait.until(ExpectedConditions.elementToBeClickable(By.id("inputName"))).clear();

                driver.findElement(By.id("inputName")).sendKeys(username); // getting username from excel

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                driver.findElement(By.id("pwd1")).sendKeys(password);      // getting password from excel

                driver.findElement(By.xpath("//button[contains(text(),'LOGIN')]")).click();

                //checking validation
               String actualResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(text(),'Prashant')])[1]"))).getText();
               if (actualResult.contains("Prashant")) {

                   Reporter.log("Test case passed");

               }
               else
                   Reporter.log("Unable to execute the script");

            }

    }

    //test to check the blogs on the page
    @Test(dependsOnMethods = {"loginCheck"})
    public void blogsCheck() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Blog"))).click();

    }

    //test to check the selenium blogs
    @Test(dependsOnMethods = {"blogsCheck"})
    public void seleniumBlogsCheck() {

      //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[1]"))).sendKeys("selenium blogs");

        driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys("selenium blogs");
        driver.findElement(By.xpath("(//*[@id=\"remote\"]/div/span[2]/i[1])[1]")).click();

    }

    //test to check the interview questions on the page
    @Test(dependsOnMethods = {"seleniumBlogsCheck"})
    public void interviewQuestionsCheck() {

        driver.findElement(By.xpath("(//a[contains(text(),'Interview Questions')])[1]")).click();

        String actualText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Interview Questions And Answers')])[4]"))).getText();
        //driver.findElement(By.xpath("(//a[contains(text(),'Interview Questions And Answers')])[4]")).getText();
        if (actualText.contains("Interview Questions And Answers")) {
            System.out.println("Validated successfully");
        }
        else {
            System.out.println("Validation failed");
        }
    }


    @AfterTest                        //   closing the browser
    public void closeBrowser() {

        driver.close();
    }



}
