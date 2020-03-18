package login.tiketi;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TiketMozzartbet {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "sportSearch")
    private
    WebElement sportSearch;
    @FindBy(id = "bettingAmount")
    private
    WebElement bettingAmount;
    @FindBy(id = "pay-ticket")
    private
    WebElement payTicket;
    @FindBy(xpath = "/html/body/div[1]/div[1]/section/section[3]/div/div[1]/section/section/section[2]/section/section/div/article/div/div[4]/button[1]")
    private
    WebElement confirmPay;
    private List<WebElement> hosts;
    private List<WebElement> bets;
    private List<WebElement> guests;

    public TiketMozzartbet(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    private void waitUntilElementNotDisplayed(final WebElement webElement) {
        ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    webElement.isDisplayed();
                    return false;
                }
                catch (NoSuchElementException | StaleElementReferenceException e ) {
                    return true;
                }
            }
        };
        wait.until(elementIsDisplayed);
    }

    private void findPair(String host){
        sportSearch.sendKeys(host);
        sportSearch.sendKeys(Keys.ENTER);

    }

    private void setbettingAmount(String amount){ bettingAmount.sendKeys(amount); }

    private List<WebElement> getHosts(){
        hosts = driver.findElements(By.cssSelector("a.pairs"));
        return hosts;
    }

    private List<WebElement> getGuests(){
        guests = driver.findElements(By.cssSelector("a.pairs"));
        return guests;
    }

    private List<WebElement> getBets(){
        bets = driver.findElements(By.cssSelector("span.odd-font.betting-regular-match"));
        return bets;
    }

    public void placeTiket(String domacinIzBaze, String gostIzBaze, String tipIzBaze) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.odd-font.betting-regular-match")));
        findPair(domacinIzBaze);
        try{ Thread.sleep(1500); } catch (Exception e) { System.out.println("Exception"); }
        this.hosts = getHosts();
        this.bets = getBets();


        for(WebElement mozhost : this.hosts) {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.pairs")));
            String guestName = mozhost.getAttribute("innerHTML");
            int i = this.hosts.indexOf(mozhost);
            System.out.println(i);


            if(guestName.contains(gostIzBaze) && tipIzBaze.equals("1")){
                this.bets.get(i).click();
                break;

            } else if(guestName.contains(gostIzBaze) && tipIzBaze.equals("X")){
                this.bets.get(i+1).click();
                break;

            } else if(guestName.contains(gostIzBaze) && tipIzBaze.equals("2")){
                this.bets.get(i+2).click();
                break;

            }
        }
        setbettingAmount("100");
        payTicket.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/section/section[3]/div/div[1]/section/section/section[2]/section/section/div/article/div/div[4]/button[1]")));
        confirmPay.click();

    }












}
