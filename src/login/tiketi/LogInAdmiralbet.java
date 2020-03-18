package login.tiketi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInAdmiralbet {
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
        wait.until(ExpectedConditions.elementToBeClickable(By.id("loginContainer")));
        loginButton1.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-input-0")));
        username.sendKeys(strUserName);
        password.sendKeys(strPassword);
        loginButton2.click();


    }
}
