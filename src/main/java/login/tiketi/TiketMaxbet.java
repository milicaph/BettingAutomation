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

public class TiketMaxbet {
    private Logger logger = LogManager.getLogger(TiketMaxbet.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "input[placeholder=Pretraga]")
    private
    WebElement sportSearch;
    @FindBy(css = "div[href*=search-result]")
    private
    WebElement pairToPlay;
    @FindBy(xpath = "//div[text()='Fudbal']")
    private
    WebElement soccer;
    @FindBy(css = "div[href*=search-result]")
    private
    WebElement allSoccerMatches;
    @FindBy(css = "input[placeholder=Ulog]")
    private
    WebElement bettingAmount;
    @FindBy(css = "div[class*=payin-button]")
    private
    WebElement payTicket;
    @FindBy(css = "button.button-style.ticket-confirmation-ok.ng-binding")
    private
    WebElement confirmPay;
    private List<WebElement> hosts;
    private List<WebElement> bets;
    private List<WebElement> guests;

    public TiketMaxbet(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    private void findPair(String host){
        try{ Thread.sleep(1000); } catch (Exception e) { System.out.println(e); }
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[placeholder=Pretraga]"))));
        Actions builder = new Actions(driver);
        Action moveToSearch = builder.moveToElement(sportSearch).click().sendKeys(host).sendKeys(Keys.ENTER).build();
        moveToSearch.perform();


    }

    //private void setbettingAmount(String amount){ bettingAmount.sendKeys(amount); }

    private List<WebElement> getHosts(){
        hosts = driver.findElements(By.cssSelector("div[class*=search-result]"));
        return hosts;
    }

    private List<WebElement> getGuests(){
        guests = driver.findElements(By.cssSelector("div[class*=search-result]"));
        return guests;
    }

    private List<WebElement> getBets(){
        bets = driver.findElements(By.cssSelector("div[match-code=matchCode]"));
        return bets;
    }

    public void placeTiket(String domacinIzBaze, String gostIzBaze, String tipIzBaze) {

        try {
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("input[class*=login-button]"))));
            wait.until(ExpectedConditions.attributeContains(By.cssSelector("div[class*=popup-profile-button]"), "innerHTML", "Oliver"));


            findPair(domacinIzBaze);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class*=search-result]"))));
            wait.until(ExpectedConditions.attributeContains(driver.findElement(By.cssSelector("div[class*=search-result]")), "innerHTML", gostIzBaze));
            //try{ Thread.sleep(1500); } catch (Exception e) { System.out.println(e); }
            this.hosts = getHosts();
            //this.bets = getBets();


            for (WebElement mozhost : this.hosts) {
                //wait.until(ExpectedConditions.visibilityOf(mozhost));
                //driver.get("https://www.maxbet.rs/bet/" + mozhost.getAttribute("href"));
                String guestName = mozhost.getAttribute("innerHTML");
                // System.out.println(guestName);

                if (!guestName.contains(gostIzBaze)) {
                    continue;
                } else if (mozhost.getAttribute("innerHTML").contains(domacinIzBaze)) {
                    //WebElement klikni = driver.findElement(By.cssSelector("div[class*=search-result]"));
                    Actions action = new Actions(driver);
                    action.moveToElement(mozhost).perform();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    action.doubleClick(mozhost).perform();
                    mozhost.click();

                }


                //try{ Thread.sleep(5000); } catch (Exception e) { System.out.println(e); }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("div[match-code=matchCode]"))));
                this.bets = getBets();

                if (guestName.contains(gostIzBaze) && tipIzBaze.equals("1")) {
                    this.bets.get(0).click();
                } else if (guestName.contains(gostIzBaze) && tipIzBaze.equals("X")) {
                    this.bets.get(1).click();
                } else if (guestName.contains(gostIzBaze) && tipIzBaze.equals("2")) {
                    this.bets.get(2).click();
                }

            }

            bettingAmount.clear();
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
