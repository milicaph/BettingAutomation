package login.tiketi;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

public class LogInBalkanbet {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(className = "accept-cookies-button")
    WebElement acceptCookies;
    @FindBy(css = "button[class*=button-login]")
    private
    WebElement loginButton1;
    @FindBy(css = "input[name=email]")
    WebElement username;
    @FindBy(css = "input[name=password]")
    WebElement password;
    @FindBy(css = "button[type=submit]")
    WebElement loginButton2;

    public LogInBalkanbet(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);

    }


    public void loginToBalkanbet(String strUserName,String strPassword){


        try {


            // js visibility
            /*JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.visibility='hidden'", driver.findElement(By.cssSelector("div.loader")));*/
            //
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.n-preboot-loader.fade-out"))));


            acceptCookies.click();
            loginButton1.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]")));
            username.sendKeys(strUserName);
            password.sendKeys(strPassword);
            loginButton2.click();
           /* int k = 10;
            int m = k/0;*/
        }
        catch(Exception e) {
            System.out.println(e);

            if(e.getMessage().contains("NoSuchElementException")) {
                acceptCookies.click();
            }  else    { throw e; }

        }






    }
}
