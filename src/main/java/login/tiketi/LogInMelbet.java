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

public class LogInMelbet {
    private Logger logger = LogManager.getLogger(LogInMelbet.class);
    private WebDriver driver;
    private WebDriverWait wait;

   // @FindBy(css = "a[class*=cookie]")
    //WebElement acceptCookies;
    @FindBy(css = "div[class=selectedLang]")
    private
    WebElement langChooser;
    @FindBy(css = "a[title=English]")
    private
    WebElement englishLang;
    @FindBy(css = "a[class*=submit]")
    private
    WebElement loginButton1;
    @FindBy(id = "userLogin")
    WebElement username;
    @FindBy(id = "userPassword")
    WebElement password;
    @FindBy(css = "a[class*=enter]")
    WebElement loginButton2;

    public LogInMelbet(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);

    }


    public void loginToMelbet(String strUserName,String strPassword){

        try {

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class=selectedLang]")));
            langChooser.click();
            englishLang.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class*=submit]")));
            //acceptCookies.click();
            loginButton1.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userPassword")));
            username.sendKeys(strUserName);
            password.sendKeys(strPassword);
            loginButton2.click();

           } catch(Exception e) { logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                .stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"))); }


    }


}

