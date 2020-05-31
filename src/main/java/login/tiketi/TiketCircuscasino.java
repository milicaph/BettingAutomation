package login.tiketi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TiketCircuscasino {
    private Logger logger = LogManager.getLogger(TiketCircuscasino.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "button[data-bind*=onShowSearchInputClicked]")
    private
    WebElement sportSearchButton;
    @FindBy(css = "input[class*=term__input]")
    private
    WebElement sportSearch;
    @FindBy(css = "div[class*=bss-StakeBox_PermCount]")
    private
    WebElement bettingAmountBox;
    @FindBy(css = "span[data-dropdown-toggler=dropdown-bet-amount-single]")
    private
    WebElement bettingAmountArrow;
    @FindBy(xpath = "//*[contains(text(),'20,00')]")
    private
    WebElement firstBettingAmount;
    @FindBy(xpath = "//*[contains(text(),'100,00')]")
    private
    WebElement secondBettingAmount;
    @FindBy(xpath = "//*[contains(text(),'250,00')]")
    private
    WebElement thirdBettingAmount;
    @FindBy(xpath = "//*[contains(text(),'500,00')]")
    private
    WebElement fourthBettingAmount;
    @FindBy(id = "goPutBetButton")
    private
    WebElement payTicket;

    private List<WebElement> pairs;
    private List<WebElement> bets;
    private String guests;

    public TiketCircuscasino(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }


    private void findPair(String host) {
        sportSearch.sendKeys(host);
        sportSearch.sendKeys(Keys.ENTER);

    }

    private void setbettingAmount(String amount) {

        bettingAmountArrow.click();
        if (amount.equals("20")) { firstBettingAmount.click(); }
        else if (amount.equals("100")) { secondBettingAmount.click(); }
        else if (amount.equals("250")) { thirdBettingAmount.click(); }
        else if (amount.equals("500")) { fourthBettingAmount.click(); }
        //bettingAmount.sendKeys();
        //bettingAmount.sendKeys(amount);
    }

    private List<WebElement> getPairs() {
        pairs = driver.findElements(By.cssSelector("div[class*=bet-event-name]"));
        return pairs;
    }

    /*private String getGuests(){
        guests = searchResults.getAttribute("href");
        return guests;
    }*/

    private List<WebElement> getBets() {
        bets = driver.findElements(By.cssSelector("div[class*=bet-outcome-column]"));
        return bets;
    }

    public void placeTiket(String domacinIzBaze, String gostIzBaze, String tipIzBaze) {

    try {

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        WebElement obscure = driver.findElement(By.xpath("//*[contains(text(),'Registruj se')]"));
        wait.until(ExpectedConditions.invisibilityOf(obscure));
        sportSearchButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.clear")));
        findPair(domacinIzBaze);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        this.pairs = getPairs();
        for (WebElement pair : pairs) {
            if (pair.getAttribute("innerHTML").contains(domacinIzBaze)) {
                pair.click();
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.go-back")));
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        this.bets = getBets();

        if (tipIzBaze.equals("1")) {
            bets.get(0).click();

        } else if (tipIzBaze.equals("X")) {
            bets.get(1).click();

        } else if (tipIzBaze.equals("2")) {
            bets.get(2).click();

        }

        setbettingAmount("500");

        try {
            Thread.sleep(60000);
        } catch (Exception e) {
        }
/*
        wait.until(ExpectedConditions.visibilityOf(bettingAmountBox));
        setbettingAmount("100");
        //payTicket.click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/section/section[3]/div/div[1]/section/section/section[2]/section/section/div/article/div/div[4]/button[1]")));
        //confirmPay.click();*/
    } catch(Exception e) { logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
            .stream()
            .map(Objects::toString)
            .collect(Collectors.joining("\n"))); }
    }
}
