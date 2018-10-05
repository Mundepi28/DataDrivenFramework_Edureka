package HybridFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnrollCourseObjects {

    static WebDriver driver;

    //Declaring instance variables
    @FindBy(className = "enr_btn")
    WebElement enrollDevops;

    @FindBy(xpath = "//li[contains(text(),'EMI option available via')]")
    WebElement validationText;

    @FindBy(xpath = "//iframe[@class='xcomponent-component-frame xcomponent-visible']")
    WebElement paymentFrame;

    @FindBy(xpath = "//img[@alt='visa']")
    WebElement visaCard;

    //constructor to instantiate the instance variables using Page Factory
    public EnrollCourseObjects(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //enrolling for the devOps course
    public void enrollClick() {

        enrollDevops.click();

        if (validationText.getText().contains("EMI option available via"))
        {
            System.out.println("Passed, proceed with payment");
        }
        else
        {
            System.out.println("Failed, Try again");
        }
    }

    //cliking on payment with visa card
    public void visaCardClick() {

        driver.switchTo().frame(paymentFrame);
        visaCard.click();

    }


}
