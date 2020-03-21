package login.tiketi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInMaxbet {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "a[class*=cookie]")
    WebElement acceptCookies;
    @FindBy(css = "input[class*=login-button]")
    private
    WebElement loginButton1;
    @FindBy(css = "input[name=username]")
    WebElement username;
    @FindBy(css = "input[name=password]")
    WebElement password;
    @FindBy(css = "input[type=submit]")
    WebElement loginButton2;

    public LogInMaxbet(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);

    }


    public void loginToMaxbet(String strUserName,String strPassword){
       // try {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[class*=login-button]")));
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[class*=cookie]")));
            acceptCookies.click();
            loginButton1.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=password]")));
            username.sendKeys(strUserName);
            password.sendKeys(strPassword);
            loginButton2.click();
     //   } catch(Exception e) {
    //        System.out.println(e.fillInStackTrace());


     //   }






    }
}
