package auto.betting;


import login.tiketi.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import parameters.db.DBconnection;
import stacktrace.StackTrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(Main.class);
        System.setProperty("webdriver.gecko.driver", "E:\\ckola\\geckodriver\\geckodriver.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        //firefoxOptions.setHeadless(true);


        WebDriver driver = (WebDriver) new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();

        DBconnection dbc = new DBconnection();

        dbc.readDBCredentionals();
        dbc.readDBTickets();

        System.out.println(dbc.urls);
        System.out.println(dbc.usernames);
        System.out.println(dbc.passwords);

        System.out.println(dbc.betsDB);
        System.out.println(dbc.guestsDB);
        System.out.println(dbc.hostsDB);

        try {
            //int i = 10 / 0;
            driver.get(dbc.urls.get(0));
            LogInSoccerbet logInSoccerbet = new LogInSoccerbet(driver, wait);
            logInSoccerbet.loginToSoccerbet(dbc.usernames.get(0), dbc.passwords.get(0));
            TiketSoccerbet tiketSoccerbet = new TiketSoccerbet(driver, wait);
            tiketSoccerbet.placeTiket(dbc.hostsDB.get(0), dbc.betsDB.get(0));

        } catch (Exception e) {
            logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                    .stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining("\n")));
        }

        try {

            driver.get(dbc.urls.get(1));
            LogInMozzartbet logInMozzartbet = new LogInMozzartbet(driver, wait);
            logInMozzartbet.loginToMozzartbet(dbc.usernames.get(1), dbc.passwords.get(1));
            TiketMozzartbet tiketMozzartbet = new TiketMozzartbet(driver, wait);
            tiketMozzartbet.placeTiket(dbc.hostsDB.get(1), dbc.guestsDB.get(1), dbc.betsDB.get(0));

        } catch(Exception e) {
            logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                    .stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining("\n")));
        }


       try {

            ((JavascriptExecutor) driver).executeScript("window.open()");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            driver.get(dbc.urls.get(2));
            LogInBalkanbet logInBalkanbet = new LogInBalkanbet(driver, wait);
            logInBalkanbet.loginToBalkanbet(dbc.usernames.get(2), dbc.passwords.get(2));
            TiketBalkanbet tiketBalkanbet = new TiketBalkanbet(driver, wait);
            tiketBalkanbet.placeBet(dbc.hostsDB.get(2), dbc.guestsDB.get(2), dbc.betsDB.get(0));

       } catch(Exception e) {
           logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                   .stream()
                   .map(Objects::toString)
                   .collect(Collectors.joining("\n")));

        }

        try {

            driver.get(dbc.urls.get(3));
            LogInAdmiralbet logInAdmiralbet = new LogInAdmiralbet(driver, wait);
            logInAdmiralbet.loginToAdmiralbet(dbc.usernames.get(3), dbc.passwords.get(3));
            TiketAdmiralbet tiketAdmiralbet = new TiketAdmiralbet(driver, wait);
            tiketAdmiralbet.placeBet(dbc.hostsDB.get(0), dbc.guestsDB.get(0), dbc.betsDB.get(0));

        } catch(Exception e) {
            logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                    .stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining("\n")));
        }

        try {

            ((JavascriptExecutor) driver).executeScript("window.open()");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(2));
            driver.get(dbc.urls.get(4));
            LogInMaxbet logInMaxbet = new LogInMaxbet(driver, wait);
            logInMaxbet.loginToMaxbet(dbc.usernames.get(4), dbc.passwords.get(4));
            TiketMaxbet tiketMaxbet = new TiketMaxbet(driver, wait);
            tiketMaxbet.placeTiket(dbc.hostsDB.get(0), dbc.guestsDB.get(0), dbc.betsDB.get(0));

        } catch(Exception e) {
            logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                    .stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining("\n")));
        }

        try {

            driver.get(dbc.urls.get(5));
            LogInMelbet logInMelbet = new LogInMelbet(driver, wait);
            logInMelbet.loginToMelbet(dbc.usernames.get(5), dbc.passwords.get(5));
            TiketMelbet tiketMelbet = new TiketMelbet(driver, wait);
            tiketMelbet.placeTiket(dbc.hostsDB.get(0), dbc.guestsDB.get(0), dbc.betsDB.get(0));

        } catch(Exception e) {
            logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                    .stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining("\n")));
        }

        try {

            driver.get(dbc.urls.get(8));
            LogIn365bet logIn365bet = new LogIn365bet(driver, wait);
            logIn365bet.loginTo365bet(dbc.usernames.get(8), dbc.passwords.get(8));
            Tiket365bet tiket365bet = new Tiket365bet(driver, wait);
            tiket365bet.placeTiket(dbc.hostsDB.get(0), dbc.guestsDB.get(0), dbc.betsDB.get(0));

        } catch(Exception e) {
            logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                    .stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining("\n")));
        }

        try {

            ((JavascriptExecutor) driver).executeScript("window.open()");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(3));
            driver.get(dbc.urls.get(9));
            LogInCircuscasino logInCircuscasino = new LogInCircuscasino(driver, wait);
            logInCircuscasino.loginToCircuscasino(dbc.usernames.get(9), dbc.passwords.get(9));
            TiketCircuscasino tiketCircuscasino = new TiketCircuscasino(driver, wait);
            tiketCircuscasino.placeTiket(dbc.hostsDB.get(2), dbc.guestsDB.get(2), dbc.betsDB.get(0));

        } catch(Exception e) {
            logger.error(e.fillInStackTrace() + " caught at:  " + Arrays.asList(e.getStackTrace())
                    .stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining("\n")));
        }

        driver.quit();



        }
    }


