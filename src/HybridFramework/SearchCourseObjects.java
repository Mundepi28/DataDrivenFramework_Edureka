package HybridFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SearchCourseObjects {

    static WebDriver driver;

    //Declaring instance variables
    @FindBy(id = "homeSearchBar")
    WebElement searchBar;

    @FindBy(id = "homeSearchBarIcon")
    WebElement searchIcon;

    @FindBy(linkText = "DevOps Certification Training")
    WebElement DevOpsTraining;

    //constructor to instantiate the instance variables using Page Factory
    public SearchCourseObjects(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //searching for the course DevOps
    public void searchCourse() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        searchBar.sendKeys("DevOps");
   }

   public void searchClick() {

       try {
           Thread.sleep(1000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       searchIcon.click();
   }

   public void clickDevOps() {

        DevOpsTraining.click();
   }


}
