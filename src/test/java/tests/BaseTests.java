package tests;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import core.utils.Browser;
import core.utils.ConfigMan;

import pages.BasePage;


public class BaseTests {

    public Browser browser = new Browser();
    public static ConfigMan config = new ConfigMan();


    // web app under test
    public BasePage app;


    @BeforeClass
    public void setUpClass() {
        // Initialize app.
        app = getApp();
    }

    @BeforeMethod
    public void beforeMethod() {
        app.openBaseUrl();
    }

    @AfterMethod
    public void afterMethod() {

    }

    @AfterClass
    public void tearDownClass() {
        app.quit();
    }


    public BasePage getApp() {

        WebDriver driver = browser.getDriver("chrome");
        BasePage app = new BasePage(driver, config.getProp("TestBaseURL"));
        return app;

    }


}
