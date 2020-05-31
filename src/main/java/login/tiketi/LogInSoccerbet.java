package login.tiketi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import stacktrace.StackTrace;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class LogInSoccerbet {
        private final Logger logger = LogManager.getLogger(LogInSoccerbet.class);
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
            //this.logger = logger;
            PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);

        }

        public void loginToSoccerbet(String strUserName,String strPassword){

            try {
                /*int i[] = null;
                int ia = i[0];*/
                username.sendKeys(strUserName);
                password.sendKeys(strPassword);
                loginButton.click();

            } catch(Exception e) { logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                    .stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining("\n"))); }

        }




}
