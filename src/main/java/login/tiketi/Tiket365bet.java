package login.tiketi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class Tiket365bet {
        private Logger logger = LogManager.getLogger(Tiket365bet.class);
        private WebDriver driver;
        private WebDriverWait wait;

        @FindBy(css = "div[class*=hm-MainHeaderRHSLoggedInWide_SiteSearchButton]")
        private
        WebElement sportSearchButton;
        @FindBy(css = "input[class*=sml-SearchTextInput ]")
        private
        WebElement sportSearch;
        /*@FindBy(id = "rezS")
        private
        WebElement searchResults;*/
        @FindBy(css = "div[class*=bss-StakeBox_PermCount]")
        private
        WebElement bettingAmountBox;
        @FindBy(css = "input[class*=bss-StakeBox_StakeValueInput]")
        private
        WebElement bettingAmount;
        @FindBy(id = "goPutBetButton")
        private
        WebElement payTicket;

        private List<WebElement> pairs;
        private List<WebElement> bets;
        private String guests;

        public Tiket365bet(WebDriver driver, WebDriverWait wait) {
            this.driver = driver;
            this.wait = wait;
            PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
        }


        private void findPair(String host, String guest) {
            try {

                sportSearch.sendKeys(host + " " + guest);
                sportSearch.sendKeys(Keys.ENTER);

            } catch (Exception e) {
                logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                        .stream()
                        .map(Objects::toString)
                        .collect(Collectors.joining("\n")));
            }

        }

        private void setbettingAmount(String amount) {
            try {
                WebElement box = driver.findElement(By.cssSelector("div[class*=bss-StakeBox_StakeInput]"));
                Actions action = new Actions(driver);
                action.moveToElement(box).perform();
                box.click();
                bettingAmount.sendKeys(amount);
            } catch (Exception e) {
                logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                        .stream()
                        .map(Objects::toString)
                        .collect(Collectors.joining("\n")));
            }
        }

        private List<WebElement> getPairs() {
            pairs = driver.findElements(By.cssSelector("span[class*=SiteSearchLabelOnlyParticipant_Name]"));
            return pairs;
        }

        /*private String getGuests(){
            guests = searchResults.getAttribute("href");
            return guests;
        }*/

        private List<WebElement> getBets() {
            bets = driver.findElements(By.cssSelector("div.gl-Participant.gl-Participant_General"));
            return bets;
        }

        public void placeTiket(String domacinIzBaze, String gostIzBaze, String tipIzBaze) {

            try{

                //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("iframe[class*=lp-UserNotificationsPopup_Frame"))));
                try{ Thread.sleep(1000); } catch (Exception e){  }
                wait.until(ExpectedConditions.elementToBeClickable(sportSearchButton));
                sportSearchButton.click();
                wait.until(ExpectedConditions.elementToBeClickable(sportSearch));
                findPair(domacinIzBaze, gostIzBaze);

                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class*=sml-SearchHeader_CloseButton]"))));
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span[class*=SiteSearchLabelOnlyParticipant_Name]")));
                this.pairs = getPairs();
                pairs.get(0).click();

                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.gl-Participant.gl-Participant_General")));

                this.bets = getBets();

                if (tipIzBaze.equals("1")) {
                    bets.get(0).click();

                } else if (tipIzBaze.equals("X")) {
                    bets.get(1).click();

                } else if (tipIzBaze.equals("2")) {
                    bets.get(2).click();

                }


                wait.until(ExpectedConditions.visibilityOf(bettingAmountBox));
                setbettingAmount("100");
                //payTicket.click();
                //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/section/section[3]/div/div[1]/section/section/section[2]/section/section/div/article/div/div[4]/button[1]")));
                //confirmPay.click();


            } catch(Exception e) { logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                        .stream()
                        .map(Objects::toString)
                        .collect(Collectors.joining("\n"))); }

            }

}
