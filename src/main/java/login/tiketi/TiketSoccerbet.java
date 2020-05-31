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
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TiketSoccerbet {
    private Logger logger = LogManager.getLogger(TiketSoccerbet.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "a[class*=btn-primary]")
    List<WebElement> tiketi;
    @FindBy(css = "a[class*=nav-expander-button]")
    WebElement tiketSidebar;
    private List<WebElement> hosts;
    private List<WebElement> guests;
    private List<WebElement> bets;
    @FindBy(css = "a[class*=next]")
    private
    WebElement nextPage;

    public TiketSoccerbet(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public void waitUntilElementNotDisplayed(final WebElement webElement) {
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
    private List<WebElement> getHosts(){
        hosts = driver.findElements(By.cssSelector("td[data-bind*=HomeCompetitorName]"));
        return hosts;
    }

    private List<WebElement> getGuests(){
        guests = driver.findElements(By.cssSelector("td[data-bind*=AwayCompetitorName]"));
        return hosts;
    }

    private List<WebElement> getBets(){
        bets = driver.findElements(By.cssSelector("a.odds-number"));
        return bets;
    }

    public void placeTiket(String domacinIzBaze, String tipIzBaze) {
      try {

          outerloop:
          while (nextPage.isEnabled()) {
              int page = 0;
              page++;

              wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td[data-bind*=HomeCompetitorName]")));
              this.hosts = getHosts();
              this.bets = getBets();
              WebElement referenceBet = bets.get(0);

              for (WebElement domacin : hosts) {
                  int i = hosts.indexOf(domacin);
                  System.out.println(domacin.getAttribute("innerHTML"));


                  if (domacin.getText().contains(domacinIzBaze) && i == 0) {
                      if (tipIzBaze.equals("1")) {
                          bets.get(i).click();

                      } else if (tipIzBaze.equals("X")) {
                          bets.get(i + 1).click();

                      } else if (tipIzBaze.equals("2")) {
                          bets.get(i + 2).click();

                      }

                      break outerloop;

                  } else if (domacin.getText().contains(domacinIzBaze)) {

                      if (tipIzBaze.equals("1")) {
                          bets.get(i * 11).click();

                      } else if (tipIzBaze.equals("X")) {
                          bets.get(i * 11 + 1).click();

                      } else if (tipIzBaze.equals("2")) {
                          System.out.println(i * 11 + 2);
                          bets.get(i * 11 + 2).click();

                      }

                      break outerloop;
                  }
              }

              wait.until(ExpectedConditions.elementToBeClickable(nextPage));
              nextPage.click();
              waitUntilElementNotDisplayed(referenceBet);
              driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

          }

          sendTiket();
          //confirmTiket();
      } catch(Exception e) { logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
              .stream()
              .map(Objects::toString)
              .collect(Collectors.joining("\n"))); }
    }

    public void sendTiket(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("match-teams")));
        tiketi.get(1).click();

    }

    public void confirmTiket(){
        Actions builder = new Actions(driver);
        Action goToConfirmButton = builder.moveToElement(driver.findElement(By.xpath("//button[contains(@class,'accept-ticket')]"))).click().build();
        goToConfirmButton.perform();
        driver.findElement(By.xpath("//button[contains(@class,'accept-ticket')]")).click();


    }
}

