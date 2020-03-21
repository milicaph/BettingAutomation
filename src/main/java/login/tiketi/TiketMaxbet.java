package login.tiketi;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TiketMaxbet {
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
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("input[class*=login-button]"))));
        wait.until(ExpectedConditions.attributeContains(By.cssSelector("div[class*=popup-profile-button]"), "innerHTML", "Oliver"));

       // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[href*=casino]"))));
        findPair(domacinIzBaze);
        try{ Thread.sleep(1000); } catch (Exception e) { System.out.println(e); }
        System.out.println(driver.findElement(By.cssSelector("div[class*=search-result]")).getAttribute("innerHTML"));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class*=search-result]"))));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.cssSelector("div[class*=search-result]")), "innerHTML", gostIzBaze));
        //try{ Thread.sleep(1500); } catch (Exception e) { System.out.println(e); }
        this.hosts = getHosts();
        System.out.print(hosts + " ----> domacini");
        //this.bets = getBets();


        for(WebElement mozhost : this.hosts) {
            wait.until(ExpectedConditions.visibilityOf(mozhost));
            //driver.get("https://www.maxbet.rs/bet/" + mozhost.getAttribute("href"));
            String guestName = mozhost.getAttribute("innerHTML");
           // System.out.println(guestName);

            if(!guestName.contains(gostIzBaze)) continue;

            mozhost.click();
            for(WebElement element : driver.findElements(By.cssSelector("div[class*=w-26]"))){
                System.out.print(driver.findElements(By.cssSelector("div[class*=w-26]")));

                if(element.getAttribute("innerHTML").contains(domacinIzBaze)){
                    Actions builder = new Actions(driver);
                    Action moveToPair = builder.moveToElement(element).doubleClick(element).build();
                    moveToPair.perform();
                    //element.click();
                    break;
                }

            }

            //try{ Thread.sleep(5000); } catch (Exception e) { System.out.println(e); }
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[class*=empty-msg]"))));

            this.bets = getBets();

            if(guestName.contains(gostIzBaze) && tipIzBaze.equals("1")){
                this.bets.get(0).click();
                break;

            } else if(guestName.contains(gostIzBaze) && tipIzBaze.equals("X")){
                this.bets.get(1).click();
                break;

            } else if(guestName.contains(gostIzBaze) && tipIzBaze.equals("2")){
                this.bets.get(2).click();
                break;

            }
        }
        bettingAmount.sendKeys("100");

        //payTicket.click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/section/section[3]/div/div[1]/section/section/section[2]/section/section/div/article/div/div[4]/button[1]")));
        //confirmPay.click();

    }

}
