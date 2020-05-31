package login.tiketi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stacktrace.StackTrace;

import javax.swing.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class LogInBalkanbet {
    private Logger logger = LogManager.getLogger(LogInAdmiralbet.class);
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

            //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.n-preboot-loader.fade-out"))));
            acceptCookies.click();
            loginButton1.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]")));
            username.sendKeys(strUserName);
            password.sendKeys(strPassword);
            loginButton2.click();

            } catch(Exception e) {

                  if(e.getMessage().contains("NoSuchElementException")) {
                         acceptCookies.click();
                      }  else  { throw e;  }

            logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                    .stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining("\n"))); }

        }






    }

