package login.tiketi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TiketAdmiralbet {
    private Logger logger = LogManager.getLogger(TiketAdmiralbet.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "label[for=all]")
    private
    WebElement filterResults;
    @FindBy(id = "searchEvent")
    private
    WebElement sportSearch;
    @FindBy(css = ".search > button:nth-child(2)")
    private
    WebElement searchButton;
    @FindBy(className = "stake-input")
    private
    WebElement bettingAmount;
    @FindBy(className = "empty-ticket")
    private
    WebElement emptyTicket;
    @FindBy(xpath = "//html/body/main-app/div/div[1]/div[2]/ticket/div[1]/div[2]/div/div[2]/div[1]/ticket-single/div/div[3]/button")
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
    private List<WebElement> ticketElements;


    public TiketAdmiralbet(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public List<WebElement> getHosts() {
        hosts = driver.findElements(By.cssSelector("td[class*=col-event]"));
        return hosts;
    }

    public List<WebElement> getGuests() {
        guests = driver.findElements(By.cssSelector("td[class*=col-event]"));
        return guests;
    }

    public List<WebElement> getBets() {
        bets = driver.findElements(By.cssSelector("td[class*=col-odd]"));
        return bets;
    }

   /* public void waitUntilElementNotDisplayed(final WebElement webElement, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    webElement.isDisplayed();
                    return false;
                } catch (NoSuchElementException e) {
                    return true;
                } catch (StaleElementReferenceException f) {
                    return true;
                }
            }
        };
        wait.until(elementIsDisplayed);
    }
*/

    public void selectAllDates() {
       /* try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            System.out.println("Exception");
        }*/
        filterResults.click();


    }


    public void findPair(String host) {
        sportSearch.sendKeys(host);
        sportSearch.sendKeys(Keys.ENTER);
    }

    public void placeBet(String domacinIzBaze, String gostIzBaze, String tipIzBaze) {

     try {

         wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sportIframe")));
         driver.switchTo().frame("sportIframe");
         wait.until(ExpectedConditions.elementToBeClickable(By.id("searchEvent")));
         wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.loader-wrap.loading.main-loader"))));

         try {
             selectAllDates();

         } catch (Exception e) {
             System.out.println(e);
             if (e.getMessage().contains("<div class=\"loader-wrap loading main-loader\"> obscures it")) {
                 selectAllDates();
             } else {
                 throw e;
             }

         }
         wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"events-main-container\"]/div[2]/div[1]/div[2]/table/tbody/tr[3]/td[3]/span/span[2]")));

         findPair(domacinIzBaze);
        /*try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            System.out.println("Still loading.");
        }*/
         if (getBets().size() == 0) {
             int i = 0;
             while (getBets().size() == 0 && i < 100) {
                 i++;
                 System.out.println("loading...");
             }
         }

         this.hosts = getHosts();
         this.bets = getBets();
         System.out.print(hosts);
         System.out.print(this.bets);


         for (WebElement admhost : hosts) {
             String guestName = admhost.getAttribute("innerHTML");
             int i = this.hosts.indexOf(admhost);
             if (guestName.contains(gostIzBaze) && tipIzBaze.equals("1")) {
                 this.bets.get(i).click();
                 System.out.println("kec" + i);
                 System.out.println(guestName);
                 break;

             } else if (guestName.contains(gostIzBaze) && tipIzBaze.equals("X")) {
                 this.bets.get(i + 1).click();
                 System.out.println("iks" + i);
                 System.out.println(guestName);
                 break;

             } else if (guestName.contains(gostIzBaze) && tipIzBaze.equals("2")) {
                 this.bets.get(i + 2).click();
                 System.out.println("dvojka" + i);
                 System.out.println(guestName);
                 break;

             }


         }
         wait.until(ExpectedConditions.visibilityOf(this.payTicket));
         //this.payTicket.click();
     } catch(Exception e) { logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                 .stream()
                 .map(Objects::toString)
                 .collect(Collectors.joining("\n"))); }

    }

}