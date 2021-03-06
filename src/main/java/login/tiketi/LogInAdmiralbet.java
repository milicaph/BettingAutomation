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

public class LogInAdmiralbet {
    private Logger logger = LogManager.getLogger(LogInAdmiralbet.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "loginContainer")
    WebElement loginButton1;
    @FindBy(id = "mat-input-0")
    WebElement username;
    @FindBy(id = "mat-input-1")
    WebElement password;
    @FindBy(css = "button[type=submit]")
    WebElement loginButton2;

    public LogInAdmiralbet(WebDriver driver, WebDriverWait wait){
            this.driver = driver;
            this.wait = wait;
            PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);

         }

    public void loginToAdmiralbet(String strUserName,String strPassword){

        try {

            wait.until(ExpectedConditions.elementToBeClickable(By.id("loginContainer")));
            loginButton1.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-input-0")));
            username.sendKeys(strUserName);
            password.sendKeys(strPassword);
            loginButton2.click();

            } catch(Exception e) { logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                .stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"))); }

    }
}
