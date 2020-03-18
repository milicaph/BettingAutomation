package auto.betting;

import login.tiketi.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import parameters.db.DBconnection;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "E:\\ckola\\geckodriver\\geckodriver.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        //firefoxOptions.setHeadless(true);


        WebDriver driver = (WebDriver) new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
        WebDriverWait wait = new WebDriverWait(driver, 60);
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



        driver.get(dbc.urls.get(0));
        LogInSoccerbet logInSoccerbet = new LogInSoccerbet(driver, wait);
        logInSoccerbet.loginToSoccerbet(dbc.usernames.get(0), dbc.passwords.get(0));
        TiketSoccerbet tiketSoccerbet = new TiketSoccerbet(driver, wait);
        tiketSoccerbet.placeTiket(dbc.hostsDB.get(0), dbc.betsDB.get(0));


/*
        driver.get(dbc.urls.get(1));
        LogInMozzartbet logInMozzartbet = new LogInMozzartbet(driver, wait);
        logInMozzartbet.loginToMozzartbet(dbc.usernames.get(1), dbc.passwords.get(1));
        TiketMozzartbet tiketMozzartbet = new TiketMozzartbet(driver, wait);
        tiketMozzartbet.placeTiket(dbc.hostsDB.get(0), dbc.guestsDB.get(0), dbc.betsDB.get(0));
*/
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));


        try {
            //  Block of code to try
            driver.get(dbc.urls.get(2));
            LogInBalkanbet logInBalkanbet = new LogInBalkanbet(driver, wait);
            logInBalkanbet.loginToBalkanbet(dbc.usernames.get(2), dbc.passwords.get(2));
            //int k = 10;
            //int m = k/0;
            TiketBalkanbet tiketBalkanbet = new TiketBalkanbet(driver, wait);
            tiketBalkanbet.placeBet(dbc.hostsDB.get(0), dbc.guestsDB.get(0), dbc.betsDB.get(0));
        }
        catch(Exception e) {
            //  Block of code to handle errors
            System.out.println(e);
            //load error in db or file or whatever
        }





        driver.get(dbc.urls.get(3));
        LogInAdmiralbet logInAdmiralbet = new LogInAdmiralbet(driver, wait);
        logInAdmiralbet.loginToAdmiralbet(dbc.usernames.get(3), dbc.passwords.get(3));
        TiketAdmiralbet tiketAdmiralbet = new TiketAdmiralbet(driver, wait);
        tiketAdmiralbet.placeBet(dbc.hostsDB.get(0), dbc.guestsDB.get(0), dbc.betsDB.get(0));

        driver.quit();



    }
}
