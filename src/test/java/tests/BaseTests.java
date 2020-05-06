package tests;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import core.utils.Browser;
import core.utils.ConfigMan;

import pages.BasePage;
import pages.ChatPage;
import pages.LoginPage;


public class BaseTests {

    public Browser browser = new Browser();
    public static ConfigMan config = new ConfigMan();


    // web app under test
    public App app;


    @BeforeClass
    public void setUpClass() {
        // Initialize app.
        app = getApp();
    }

    @BeforeMethod
    public void beforeMethod() {
        app.basePage.openBaseUrl();
    }

    @AfterMethod
    public void afterMethod() {

    }

    @AfterClass
    public void tearDownClass() {
        app.basePage.quit();
    }


    public App getApp() {

        App app = new App();
        return app;

    }


    public class App{
        public BasePage basePage;
        public LoginPage loginPage;
        public ChatPage chatPage;

        public App(){
            WebDriver driver = browser.getDriver("chrome");
//            WebDriver driver = browser.getDriver("remotechrome");

            basePage = new BasePage(driver);
            loginPage = new LoginPage(driver);
            chatPage = new ChatPage(driver);
        }
    }
}
