package login.tiketi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stacktrace.StackTrace;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class LogInCircuscasino {
    private Logger logger = LogManager.getLogger(LogInCircuscasino.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = ".cc-cookie-accept")
    WebElement acceptCookies;
    @FindBy(xpath = "//*[contains(text(),'Prijava')]")
    private
    WebElement loginButton1;
    private
    @FindBy(xpath = "//input[contains(@id, 'usernameInput')]")
    WebElement username;
    private
    @FindBy(xpath = "//input[contains(@id, 'passwordInput')]")
    WebElement password;
    private
    @FindBy(xpath = "//button[@type='submit'][@tabindex='5']")
    WebElement loginButton2;


    public LogInCircuscasino(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);

    }


    public void loginToCircuscasino(String strUserName,String strPassword){

        try {

            wait.until(ExpectedConditions.visibilityOf(acceptCookies));
            acceptCookies.click();
            try { Thread.sleep(8000); } catch (InterruptedException e) { e.printStackTrace(); }
            loginButton1.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id, 'passwordInput')]")));
            username.sendKeys(strUserName);
            password.sendKeys(strPassword);
            loginButton2.click();

            } catch(Exception e) { logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                .stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"))); }






    }
}

