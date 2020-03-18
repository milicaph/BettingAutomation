package login.tiketi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInMozzartbet {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "accept-button")
    WebElement cookies;
    @FindBy(css = "input[placeholder*=ime]")
    WebElement username;
    @FindBy(css = "input[type*=password]")
    WebElement password;
    @FindBy(className = "login-btn")
    WebElement loginButton;

    public LogInMozzartbet(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);

    }

    public void loginToMozzartbet(String strUserName,String strPassword){
        cookies.click();
        username.sendKeys(strUserName);
        password.sendKeys(strPassword);
        loginButton.click();

    }


}
