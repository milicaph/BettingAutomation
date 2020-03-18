package login.tiketi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInSoccerbet {
        private WebDriver driver;
        private WebDriverWait wait;

        @FindBy(id = "UserName")
        private
        WebElement username;
        @FindBy(id = "Password")
        private
        WebElement password;
        @FindBy(css = "button.btn")
        private
        WebElement loginButton;

        public LogInSoccerbet(WebDriver driver, WebDriverWait wait){
            this.driver = driver;
            this.wait = wait;
            PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);

        }

        public void loginToSoccerbet(String strUserName,String strPassword){
            username.sendKeys(strUserName);
            password.sendKeys(strPassword);
            loginButton.click();

        }




}
