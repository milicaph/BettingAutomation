package login.tiketi;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stacktrace.StackTrace;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class LogIn365bet {
    private Logger logger = LogManager.getLogger(LogIn365bet.class);
    private WebDriver driver;
    private WebDriverWait wait;


    @FindBy(css = "div[class*=hm-MainHeaderRHSLoggedOutWide_Login]")
    private
    WebElement loginButton1;
    private
    @FindBy(css = "input[type=text]")
    WebElement username;
    private
    @FindBy(css = "input[type=password]")
    WebElement password;
    private
    @FindBy(css = "div[class*=lms-StandardLogin_LoginButtonText]")
    WebElement loginButton2;
    private
    @FindBy(css = "iframe[class*=UserNotificationsPopup]")
    WebElement iframePopUp;
    private
    @FindBy(id = "remindLater")
    WebElement remindMeLater;

    public LogIn365bet(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);

    }


    public void loginTo365bet(String strUserName,String strPassword){

        try {

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class*=hm-MainHeaderRHSLoggedOutWide_Login]")));
            loginButton1.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type=password]")));
            username.sendKeys(strUserName);
            password.sendKeys(strPassword);
            loginButton2.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe[class*=UserNotificationsPopup]")));
            driver.switchTo().frame(iframePopUp);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remindLater")));
            remindMeLater.click();
            driver.switchTo().defaultContent();

            } catch(Exception e) { logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                .stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"))); }



    }
}
