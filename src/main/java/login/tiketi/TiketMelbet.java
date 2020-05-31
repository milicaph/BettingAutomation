package login.tiketi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
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

public class TiketMelbet {
    private Logger logger = LogManager.getLogger(TiketMelbet.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "search")
    private
    WebElement sportSearch;
    @FindBy(id = "rezS")
    private
    WebElement searchResults;
    @FindBy(id = "bet_input")
    private
    WebElement bettingAmount;
    @FindBy(id = "goPutBetButton")
    private
    WebElement payTicket;

    //private List<WebElement> hosts;
    private List<WebElement> bets;
    private String guests;

    public TiketMelbet(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }


    private void findPair(String host){
        sportSearch.sendKeys(host);
        sportSearch.sendKeys(Keys.ENTER);
        driver.get(searchResults.getAttribute("href"));

    }

    private void setbettingAmount(String amount){ bettingAmount.sendKeys(amount); }

    /*private List<WebElement> getHosts(){
        hosts = driver.findElements(By.cssSelector("a.pairs"));
        return hosts;
    }*/

    private String getGuests(){
        guests = searchResults.getAttribute("href");
        return guests;
    }

   private List<WebElement> getBets(){
        bets = driver.findElements(By.cssSelector("span[class=betBut]"));
        return bets;
    }

    public void placeTiket(String domacinIzBaze, String gostIzBaze, String tipIzBaze) {

     try {

         wait.until(ExpectedConditions.elementToBeClickable(sportSearch));
         findPair(domacinIzBaze);
         String guestName = gostIzBaze.substring(0, 7);

         System.out.println(driver.getCurrentUrl() + 1);
         wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("betBut")));
         System.out.println(driver.getCurrentUrl() + 2);
         this.bets = getBets();
         System.out.println(bets.get(0).getText());

         if (tipIzBaze.equals("1")) {
             System.out.println(guestName);
             bets.get(0).click();
         } else if (tipIzBaze.equals("X")) {
             System.out.println(guestName);
             bets.get(1).click();
         } else if (tipIzBaze.equals("2")) {
             System.out.println(guestName);
             bets.get(2).click();
         }

         wait.until(ExpectedConditions.visibilityOf(bettingAmount));
         bettingAmount.sendKeys("100");
         //payTicket.click();
         //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/section/section[3]/div/div[1]/section/section/section[2]/section/section/div/article/div/div[4]/button[1]")));
         //confirmPay.click();

     } catch(Exception e) { logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
             .stream()
             .map(Objects::toString)
             .collect(Collectors.joining("\n"))); }
    }








}
