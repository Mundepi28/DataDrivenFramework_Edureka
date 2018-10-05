package HybridFramework;

import com.edureka.utility.ReadExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestScriptsHybrid {

    static WebDriver driver;

    //before suit to launch the browser, providing synchronisation commands and navigate to the edureka
    @BeforeSuite
    public void launchEdureka() {

        //Launching chrome browser
        System.setProperty("webdriver.chrome.driver", ".//driver_executables/chromedriver");
        driver = new ChromeDriver();

        //pageLoadTimeout and  implicit wait
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        //Navigating to edureka
        driver.get("https://www.edureka.co");
        driver.manage().window().maximize();
    }

    //test to perform login to the edureka account successfully with data driven concept
    @Test(priority = 0)
    public void logIn() throws IOException {

        LoginObjects lp = new LoginObjects(driver);

        lp.clkLoginBtn();  //click on login button to provide login credentials


        //passing parameters as filename and sheetname to the static method getData in ReadExcel class
        String[][] data = ReadExcel.getData("TestExcel.xlsx", "Sheet1");

        for (int i = 1; i < data.length; i++) {
            String username = data[i][0];
            String password = data[i][1];

            lp.setEmailId(username); // getting username from excel

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lp.setPwd(password); // getting password from excel

            lp.clickSubmit();  //submit to login

        }//for loop closed
    }//method closed


    //test to search for the course
    @Test(dependsOnMethods = {"logIn"})
        public void seachCourseTest()  {

        SearchCourseObjects sp = new SearchCourseObjects(driver);

        sp.searchCourse();
        sp.searchClick();
        sp.clickDevOps();
    }

    //test to enroll for the course
    @Test(dependsOnMethods = {"seachCourseTest"})
        public void enrollTest() {

        EnrollCourseObjects ep = new EnrollCourseObjects(driver);

        ep.enrollClick();
        ep.visaCardClick();
    }

    //test to do payment for the course
    @Test(dependsOnMethods = {"enrollTest"})
        public void paymentTest() {

        PaymentObjects po = new PaymentObjects(driver);
        po.selectCountry();

    }

    @AfterSuite                      //   quiting the parent browser
    public void closeBrowser() {

        driver.quit();
    }




}//class closed
