package HybridFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginObjects {

    static WebDriver driver;


    //Declaring instance variables

    @FindBy(linkText = "Log In")
    WebElement loginBtn;

    @FindBy(id = "inputName")
    WebElement emailId;

    @FindBy(id = "pwd1")
    WebElement pwd;

    @FindBy(xpath = "//button[contains(text(),'LOGIN')]")
    WebElement submitBtn;


    //constructor to instantiate the instance variables using Page Factory
    public LoginObjects(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //clicking on login button
    public void clkLoginBtn() {

        loginBtn.click();

    }

    //providing email id
    public void setEmailId(String email) {

        emailId.sendKeys(email);

    }

    //providing password
    public void setPwd(String password) {

        pwd.sendKeys(password);

    }

    //clicking on submit button
    public void clickSubmit() {

        submitBtn.click();
    }


    public static void main(String[] args){

    }


}
