package login.tiketi;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.*;

import java.util.List;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.Duration.*;

public class TiketBalkanbet {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "div.time-tab")
    private
    List<WebElement> filterResults;
    @FindBy(css = "input[placeholder=Search]")
    private
    WebElement sportSearch;
    @FindBy(className = "stake-input")
    private
    WebElement bettingAmount;
    @FindBy(css = "input[class*=buttons-payin]")
    private
    WebElement payTicket;
    @FindBy(xpath = "/html/body/div[1]/div[1]/section/section[3]/div/div[1]/section/section/section[2]/section/section/div/article/div/div[4]/button[1]")
    private
    WebElement confirmPay;
    @FindBy(className = "loading-outer-wrapper")
    private WebElement loadingMatches;
    private List<WebElement> bets;
    private List<WebElement> hosts;
    private List<WebElement> guests;


    public TiketBalkanbet(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public List<WebElement> getHosts(){
        this.hosts = driver.findElements(By.cssSelector("div[class*=event-wrap-name]"));
        return hosts;
    }

    public List<WebElement> getGuests(){
        this.guests = driver.findElements(By.cssSelector("div[class*=event-wrap-name]"));
        return guests;
    }

    public List<WebElement> getBets(){
        this.bets = driver.findElements(By.cssSelector("div[class*=event-outcomes-odd]"));
        return bets;
    }
/*
    public void waitUntilElementNotDisplayed(final WebElement webElement, WebDriver driver) {
        ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    webElement.isDisplayed();
                    return false;
                }
                catch (NoSuchElementException e ) {
                    return true;
                }
                catch (StaleElementReferenceException f) {
                    return true;
                }
            }
        };
        wait.until(elementIsDisplayed);
    }

    public void waitUntilElementIsDisplayed(final WebElement webElement, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    webElement.isDisplayed();
                    return true;
                }
                catch (NoSuchElementException e ) {
                    return false;
                }
                catch (StaleElementReferenceException f) {
                    return false;
                }
            }
        };
        wait.until(elementIsDisplayed);
    }
*/
    public void selectAllDates(){
        /*try{
            Thread.sleep(4000);
        } catch (Exception e){
            System.out.println("Nije odabralo sve");
        }*/
        filterResults.get(0).click();


    }


    public void findPair(String host){
        sportSearch.sendKeys(host);
        sportSearch.sendKeys(Keys.ENTER);
    }

    public void placeBet(String domacinIzBaze, String gostIzBaze, String tipIzBaze){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("plugin-sm-prematch")));
        driver.switchTo().frame("plugin-sm-prematch");
        wait.until(ExpectedConditions.visibilityOfAllElements(filterResults.get(0)));
        selectAllDates();
        findPair(domacinIzBaze);

       /* try{
            Thread.sleep(3000);
        } catch (Exception e){
            System.out.println("Nije odabralo sve");
        }*/

        this.hosts = this.getHosts();
        this.bets = this.getBets();
        System.out.print(hosts);
        System.out.print(this.bets);

        for(WebElement balhost : hosts){
            String guestName = balhost.getAttribute("innerHTML");
            int i = hosts.indexOf(balhost);
            if(guestName.contains(gostIzBaze) && tipIzBaze.equals("1")){
                this.bets.get(i).click();
                System.out.println("kec" + i);
                System.out.println(guestName);
                break;

            } else if(guestName.contains(gostIzBaze) && tipIzBaze.equals("X")){
                this.bets.get(i+1).click();
                System.out.println("iks" + i);
                System.out.println(guestName);
                break;

            } else if(guestName.contains(gostIzBaze) && tipIzBaze.equals("2")){
                this.bets.get(i+2).click();
                System.out.println("dvojka" + i);
                System.out.println(guestName);
                break;

            }


        }





    }
}
